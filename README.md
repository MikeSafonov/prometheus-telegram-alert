# Prometheus Alertmanager Telegram 

This Spring Boot application helps to transfer alerts 
from [Prometheus Alertmanager](https://prometheus.io/docs/alerting/alertmanager/)
 to Telegram chat and channels using webhooks and [spring-boot-starter-prometheus-alerts](https://github.com/MikeSafonov/spring-boot-starter-prometheus-alerts)
 
 [![codecov](https://codecov.io/gh/MikeSafonov/prometheus-telegram-alert/branch/master/graph/badge.svg)](https://codecov.io/gh/MikeSafonov/prometheus-telegram-alert)
 ![Travis-CI](https://travis-ci.com/MikeSafonov/prometheus-telegram-alert.svg?branch=master)
 [![Conventional Commits](https://img.shields.io/badge/Conventional%20Commits-1.0.0-yellow.svg)](https://conventionalcommits.org)
 
 [![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_prometheus-telegram-alert&metric=alert_status)](https://sonarcloud.io/dashboard?id=MikeSafonov_prometheus-telegram-alert)
 [![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_prometheus-telegram-alert&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=MikeSafonov_prometheus-telegram-alert)
 [![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_prometheus-telegram-alert&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=MikeSafonov_prometheus-telegram-alert)
 [![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_prometheus-telegram-alert&metric=security_rating)](https://sonarcloud.io/dashboard?id=MikeSafonov_prometheus-telegram-alert)
 
 [![Bugs](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_prometheus-telegram-alert&metric=bugs)](https://sonarcloud.io/dashboard?id=MikeSafonov_prometheus-telegram-alert)
 [![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_prometheus-telegram-alert&metric=code_smells)](https://sonarcloud.io/dashboard?id=MikeSafonov_prometheus-telegram-alert)
 [![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_prometheus-telegram-alert&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=MikeSafonov_prometheus-telegram-alert)
 
 [![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_prometheus-telegram-alert&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=MikeSafonov_prometheus-telegram-alert)
 [![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_prometheus-telegram-alert&metric=ncloc)](https://sonarcloud.io/dashboard?id=MikeSafonov_prometheus-telegram-alert)
 [![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=MikeSafonov_prometheus-telegram-alert&metric=sqale_index)](https://sonarcloud.io/dashboard?id=MikeSafonov_prometheus-telegram-alert)


## Getting started 

### Build from source

You can build application using following command:

    ./gradlew clean build

### Testing

You can run unit test using following command:

    ./gradlew test

### Configuration

According to [Spring Docs](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-external-config.html#boot-features-external-config-application-property-files)
you can override default application properties by put custom **application.properties** file in one of the following
locations:

- a `/config` subdirectory of the current directory
- the current directory

The following properties can be configured in **application.properties**:

| property | description |
| -------- | ----------- |
| prometheus.alertmanager.endpoint.base | base url for POST request mapping for prometheus alert (default `/alert`) |
| server.port | application port (default `8080`) |
| prometheus.telegram.auth-token | telegram bot auth token |
| prometheus.telegram.chat-id | telegram chat id |
| prometheus.telegram.proxy.enable | is use proxy (default `false`) |
| prometheus.telegram.proxy.host | proxy host |
| prometheus.telegram.proxy.port | proxy port |

### Running jar

You can run application using following command:

    java -jar prometheus-telegram-alert.jar

You can test application by sending POST request with request body 
from **test alerts/simple.json** file.

### Building and running app with Docker and docker-compose

 - For building the application and creation Docker image run
 
        docker-compose build

 - Customise configs with you preferred editor

   place configs in ./config directory   

 - Run the docker image  

        docker-compose up -d

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

## Contributing

Feel free to contribute. 
New feature proposals and bug fixes should be submitted as GitHub pull requests. 
Fork the repository on GitHub, prepare your change on your forked copy, and submit a pull request.

**IMPORTANT!**
>Before contributing please read about [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0-beta.2/) / [Conventional Commits RU](https://www.conventionalcommits.org/ru/v1.0.0-beta.2/)