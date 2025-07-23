# IoT Device Simulator with AWS Kinesis & S3 Integration

This project simulates IoT device telemetry, streams the data to AWS Kinesis, and processes it using a Lambda function that writes the data to an S3 bucket.

---

## 📁 Project Structure

```
.
├── pom.xml                            # Maven build configuration
├── README.md
├── src/
│   └── main/java/com/example/
│       ├── Device.java               # POJO representing an IoT device
│       ├── DeviceSimulator.java      # Main class that generates telemetry
│       └── KinesisSender.java        # Sends data to AWS Kinesis stream
│
├── target/                           # Compiled classes
│   └── classes/
│       └── *.class
│
├── lambda-consumer-code/
│   └── iot-device-lambda/
│       ├── src/
│       │   ├── config.yml            # SAM CLI config (optional)
│       │   ├── lambda_function.py    # Lambda function that reads from Kinesis and writes to S3
│       │   └── template.yml          # AWS SAM template to deploy resources
```

---

## ⚙️ Requirements

- Java 8+
- Python 3.9+
- AWS CLI configured (`aws configure`)
- AWS SAM CLI (for deployment)
- Maven

---

## Running the Java Simulator

### 1. Build the project

```bash
mvn clean package
```

### 2. Run the simulator

```bash
java -cp target/classes com.example.DeviceSimulator
```

This sends random IoT data to the configured AWS Kinesis stream.

---

## 🧪 Sample Output Format

```json
{
  "deviceId": "sensor-001",
  "timestamp": "2025-07-23T21:00:00Z",
  "temperature": 24.8,
  "status": "ACTIVE"
}
```

---

## Lambda Function – Kinesis to S3

The Lambda (`lambda_function.py`) is triggered by the Kinesis stream and writes each record to S3. Complied on console. Need to make this deployable.

### Example Code:

```python
import json, boto3, base64, uuid
from datetime import datetime

s3 = boto3.client('s3')
bucket_name = 'your-target-s3-bucket'

def lambda_handler(event, context):
    for record in event['Records']:
        payload = base64.b64decode(record['kinesis']['data']).decode('utf-8')
        filename = f"iot-data/{datetime.utcnow().isoformat()}_{uuid.uuid4()}.json"
        s3.put_object(Body=payload, Bucket=bucket_name, Key=filename)
    return {"statusCode": 200}
```

## Configuration Points

- `KinesisSender.java`: Update region and stream name
- `lambda_function.py`: Update `bucket_name` with your target S3 bucket
- `template.yml`: Customize AWS resources if needed

---

## Enhancements

- Add CloudFormation/SAM support for Java simulator deployment
- Introduce DynamoDB for analytics
- Write unit/integration tests for Java and Lambda components
- Add alerts/CloudWatch metrics

---
