# Prometheus Alertmanager Telegram 

This Spring Boot application helps to transfer alerts 
from [Prometheus Alertmanager](https://prometheus.io/docs/alerting/alertmanager/)
 to Telegram chat and channels using webhook. 

## Getting started 

### Build

You can build application using following command:

    ./gradlew clean build

### Configuration

In **application.properties** you can specify:

1. application port (server.port)
2. telegram chat id 
3. telegram auth-token
4. telegram proxy settings

### Running

You can run application using following command:

    java -jar prometheus-telegram-alert.jar

You can test application by sending POST request with request body 
from **test alerts/simple.json** file.

### Alertmanager configuration

```
route:
  receiver: 'telegram'
receivers:
- name: 'telegram'
  webhook_configs:
    - url: http://<application-host>:<application-port>/alert
```

## Message format

You can create your own message format by implementing **MessageConverter.java**.