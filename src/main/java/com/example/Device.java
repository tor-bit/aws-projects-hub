import java.time.Instant;

public class Device {
    private int deviceID;
    private float temperature;
    private Instant timestamp; // more suitable for "point in time"

    // Constructor
    public Device(int deviceID, float temperature) {
        this.deviceID = deviceID;
        this.temperature = temperature;
        this.timestamp = Instant.now(); // sets the timestamp at creation
    } 

    // Getters
    public int getDeviceID() {
        return deviceID;
    }

    public float getTemperature() {
        return temperature;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    // Setters
    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    // Serialize to JSON
    public String toJson() {
        return String.format(
            "{\"deviceID\": %d, \"temperature\": %.2f, \"timestamp\": \"%s\"}",
            deviceID, temperature, timestamp.toString()
        );
    }

    // // Test run
    // public static void main(String[] args) {
    //     Device myDevice = new Device(23, 68.2f);
    //     System.out.println(myDevice.toJson());
    // }
}
