package com.example.weatheranalyzer.repository;

import com.example.weatheranalyzer.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {

    Optional<Location> findByCity(String city);
}
