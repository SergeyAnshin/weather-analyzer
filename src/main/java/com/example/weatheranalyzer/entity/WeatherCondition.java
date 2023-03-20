package com.example.weatheranalyzer.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.EnumType.STRING;

@SuperBuilder
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "weather_condition_id"))
})
public class WeatherCondition extends GeneralEntity {
    @Enumerated(STRING)
    private WeatherType weatherType;
}
