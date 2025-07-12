import software.amazon.awssdk.services.kinesis.KinesisClient;
import software.amazon.awssdk.services.kinesis.model.PutRecordRequest;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.core.SdkBytes;

public class KinesisSender {

    private final KinesisClient client;
    private final String streamName;

    public KinesisSender(String streamName, Region region) {
        this.client = KinesisClient.builder()
                .region(region)
                .build();
        this.streamName = streamName;
    }

    public void send(String partitionKey, String jsonPayload) {
        PutRecordRequest request = PutRecordRequest.builder()
                .streamName(streamName)
                .partitionKey(partitionKey)
                .data(SdkBytes.fromUtf8String(jsonPayload))
                .build();

        client.putRecord(request);
    }
}
