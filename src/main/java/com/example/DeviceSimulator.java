package com.example;

import software.amazon.awssdk.regions.Region;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DeviceSimulator {

    private static Map<Integer, Float> lastTemps = new HashMap<>();
    private static Random rand = new Random();

    private static float generateRandomTemperature(int deviceId) {
        float lastTemp = lastTemps.getOrDefault(deviceId, 25.0f);
        float delta = rand.nextFloat() * 2 - 1;
        float newTemp = lastTemp + delta;
        lastTemps.put(deviceId, newTemp);
        return newTemp;
    }

    public static void simulateDevices(int deviceCount, int eventsPerDevice) {
        String streamName = "iot-devices";
        Region region = Region.US_EAST_1;
        KinesisSender kinesis = new KinesisSender(streamName, region);

        try {
            for (int x = 0; x < deviceCount; x++) {
                for (int y = 0; y < eventsPerDevice; y++) {
                    Device device = new Device(x, generateRandomTemperature(x));
                    String partitionKey = UUID.randomUUID().toString();

                    kinesis.send(partitionKey, device.toJson());   // ← semicolon added
                    System.out.println("Sent: " + device.toJson());

                    Thread.sleep(500);
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            kinesis.close();  // clean shutdown
        }
    }

    public static void main(String[] args) {
        simulateDevices(5, 10);
    }
}