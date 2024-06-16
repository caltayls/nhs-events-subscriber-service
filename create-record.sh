#!bin/bash
aws dynamodb put-item \
  --table-name users \
    --item '{
        "email": {"S": "example@example.com"},
        "location": {"L": [
            {"S": "New York"},
            {"S": "Los Angeles"},
            {"S": "Chicago"}
        ]},
        "emailFreq": {"S": "daily"},
        "weeklyUpdate": {"BOOL": true}
    }' \
  --endpoint-url\
    http://localhost:8000

    aws dynamodb put-item \
  --table-name users \
    --item '{
        "email": {"S": "example1@example.com"},
        "location": {"L": [
            {"S": "New York"},
            {"S": "Los Angeles"},
            {"S": "Chicago"}
        ]},
        "emailFreq": {"S": "daily"},
        "weeklyUpdate": {"BOOL": true}
    }' \
  --endpoint-url\
    http://localhost:8000