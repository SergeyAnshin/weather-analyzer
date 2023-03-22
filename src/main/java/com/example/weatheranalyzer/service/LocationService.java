package com.example.weatheranalyzer.service;

import com.example.weatheranalyzer.entity.Location;
import com.example.weatheranalyzer.repository.LocationRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    @Cacheable(value = "locations")
    public Optional<Location> findByName(String name) {
        return locationRepository.findByCity(name);
    }

    @CachePut(value = "locations", key = "#location.city")
    public Location save(Location location) {
        return locationRepository.save(location);
    }
}
