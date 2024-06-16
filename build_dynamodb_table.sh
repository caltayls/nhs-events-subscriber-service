#!/bin/bash
aws dynamodb create-table \
  --table-name \
      users \
  --attribute-definitions \
      AttributeName=email,\
      AttributeType=S \
  --key-schema\
      AttributeName=email,\
      KeyType=HASH \
  --provisioned-throughput \
      ReadCapacityUnits=5,WriteCapacityUnits=5 \
  --endpoint-url\
      http://localhost:8000