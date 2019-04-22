package com.queens.operr.Location.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.queens.operr.Location.Repository.LocationRepository;
import com.queens.operr.Location.models.Location;

@Service("locationService")
public class LocationServiceImpl implements LocationService {

	@Autowired
	LocationRepository locationRepository;

	@Override
	public Boolean findByLocationId(String locationId) {
		return locationRepository.findById(locationId).isPresent();
	}

	@Override
	public List<Location> findByLocationName(String locationName) {
		return locationRepository.findByLocationName(locationName);
	}

	@Override
	public Location saveLocation(Location location) {
		return locationRepository.save(location);
	}

	@Override
	public void deleteByLocationId(String locationId) {
		locationRepository.deleteById(locationId);
	}

	@Override
	public List<Location> getAllLocationData() {
		return locationRepository.findAll();
	}

	@Override
	public Location findById(String locationId) {
		return locationRepository.findByLocationId(locationId);
	}
}