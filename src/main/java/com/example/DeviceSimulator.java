import software.amazon.awssdk.regions.Region;
import java.util.Random;
import java.util.HashMap;
import java.util.Map;

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
        for (int x = 0; x < deviceCount; x++) {
            for (int y = 0; y < eventsPerDevice; y++) {
                Device device = new Device(x, generateRandomTemperature(x));
                System.out.println(device.toJson());
                try {
                    Thread.sleep(500); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        //local testing:
        // simulateDevices(5, 10);
        String streamName = "iot-devices";
        Region region = Region.US_EAST_1;

        KinesisSender sender = new KinesisSender(streamName, region);

        simulateDevices(5,10);
    }
}
