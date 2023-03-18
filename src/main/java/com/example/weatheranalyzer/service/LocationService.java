package com.example.weatheranalyzer.service;

import com.example.weatheranalyzer.entity.Location;
import com.example.weatheranalyzer.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationService {
    private final LocationRepository locationRepository;

    public Optional<Location> findByName(String name) {
        return locationRepository.findByCity(name);
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }
}
