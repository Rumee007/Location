package com.queens.operr.Location.service;

import java.util.List;
import com.queens.operr.Location.models.Location;

public interface LocationService {

	Boolean findByLocationId(String locationId);

	Location findById(String locationId);
	
	List<Location> findByLocationName(String locationName);

	Location saveLocation(Location location);

	void deleteByLocationId(String locationId);

	List<Location> getAllLocationData();

}
