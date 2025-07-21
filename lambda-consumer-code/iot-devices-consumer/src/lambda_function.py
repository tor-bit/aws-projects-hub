import json
import boto3
import base64
import uuid
import os
from datetime import datetime

def lambda_handler(event, context):
    for record in event['Records']:
        # Fetch bucket name
        BUCKET_NAME = os.environ['BUCKET_NAME']
        # Decode base64 Kinesis data
        payload = base64.b64decode(record["kinesis"]["data"]).decode("utf-8")
        try:
            # Parse JSON string into Python dictionary
            data = json.loads(payload)
            print(f"Processed payload: {data}")
        except json.JSONDecodeError as e:
            print(f"Skipping invalid JSON: {payload}")
            continue

        # Create a unique s3 object key
        timestamp = datetime.now().strftime("%Y-%m-%d-%H-%M-%S")
        object_key = f"{timestamp}-{str(uuid.uuid4())}.json"

        # Upload to S3
        try:
            s3 = boto3.client('s3')
            s3.put_object(
                Bucket=BUCKET_NAME,
                Key=object_key,
                Body=json.dumps(data),
                ContentType='application/json'
            )
        except Exception as e:
            print(f"Error uploading to S3: {str(e)}")
            continue

    return {
        'statusCode': 200,
        'body': json.dumps('All good!')
    }
