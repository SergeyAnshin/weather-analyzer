# Name

Weather analyzer

# Description

The REST web application receives weather information from the third-party API, saves it to the database and 
provides the interface for analysis

## Stack

* Spring Boot / Web / Data / Cache
* H2
* Liquibase
* Lombok
* Mapstruct

## Features

* Getting current weather information
* Getting the average daily temperature for a specified period

# Installation

## Requirements

* Java 17

## Specific steps

To work with Weather API the application needs an API key that you can get by sign up on the [site](https://www.weatherapi.com/).
So far there is no script for automatically placing the key in the environment, so set the key in the properties

```bash
webclient.weather-api.api-key=<key>
```

The city for which the app receives and processes information can be changed in properties

```bash
webclient.weather-api.realtime-api.query-param.city=<city>
```

If you want You can change the path to the log file

```bash
logging.file.name=<path-to-log-file>
```
