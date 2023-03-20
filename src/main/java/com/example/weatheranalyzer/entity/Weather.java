package com.example.weatheranalyzer.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.CascadeType.MERGE;
import static jakarta.persistence.CascadeType.PERSIST;

@SuperBuilder
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "weather_id"))
})
public class Weather extends GeneralEntity {
    private float temperature;
    private float windVelocity;
    private float atmosphericPressure;
    private byte humidity;
    @ManyToOne(cascade = {PERSIST})
    @JoinColumn(name = "weather_condition_id",
            nullable = false,
            updatable = false,
            foreignKey = @ForeignKey(name = "fk_weather_weather_condition_id"))
    private WeatherCondition condition;
    @ManyToOne(cascade = {MERGE})
    @JoinColumn(name = "location_id",
            nullable = false,
            updatable = false,
            foreignKey = @ForeignKey(name = "fk_weather_location_id"))
    private Location location;
}
