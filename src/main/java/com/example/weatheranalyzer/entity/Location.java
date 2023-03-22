package com.example.weatheranalyzer.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "location_id"))
})
public class Location extends GeneralEntity {
    private String city;
}
