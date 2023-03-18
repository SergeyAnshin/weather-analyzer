--liquibase formatted sql

--changeset SergeyAnshin:create-location-table
CREATE TABLE location (
    location_id BIGINT AUTO_INCREMENT,
    city VARCHAR,
    CONSTRAINT pk_location_id PRIMARY KEY (location_id)
);

--changeset SergeyAnshin:create-weather_condition-table
CREATE TABLE weather_condition (
    weather_condition_id BIGINT AUTO_INCREMENT,
    weather_type VARCHAR,
    CONSTRAINT pk_weather_condition_id PRIMARY KEY (weather_condition_id)
);

--changeset SergeyAnshin:create-weather-table
CREATE TABLE weather (
    weather_id BIGINT AUTO_INCREMENT,
    temperature DECIMAL(3,1),
    wind_velocity DECIMAL(3,1) CHECK (wind_velocity >= 0),
    atmospheric_pressure DECIMAL(6,2) CHECK (atmospheric_pressure >= 0),
    humidity TINYINT CHECK (humidity >= 0 AND humidity <= 100),
    location_id BIGINT,
    weather_condition_id BIGINT,
    CONSTRAINT pk_weather_id PRIMARY KEY (weather_id)
);

--changeset SergeyAnshin:add-fk-weather-location-id
ALTER TABLE weather ADD CONSTRAINT fk_weather_location_id FOREIGN KEY (location_id)
    REFERENCES location (location_id);

--changeset SergeyAnshin:add-fk-weather-weather-condition-id
ALTER TABLE weather ADD CONSTRAINT fk_weather_weather_condition_id FOREIGN KEY (weather_condition_id)
    REFERENCES weather_condition (weather_condition_id);
