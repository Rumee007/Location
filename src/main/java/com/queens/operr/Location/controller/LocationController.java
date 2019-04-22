package com.queens.operr.Location.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.queens.operr.Location.models.Location;
import com.queens.operr.Location.service.LocationService;

@RestController
@RequestMapping("/location")
public class LocationController {

	@Autowired
	LocationService locationService;

	/**
	 * POST /add --> Add a new location and save it in the database.
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Map<String, Object> create(@RequestParam String lat, @RequestParam String lng, @RequestParam String locationName) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			String formattedCurrentDate = new SimpleDateFormat("hh:mm:ss a").format(new Date());
			dataMap.put("Time", formattedCurrentDate);
			// Check if any field is empty
			if (lat.isEmpty() || lng.isEmpty() || locationName.isEmpty()) {
				dataMap.put("message", "Location does not created successfully");
				dataMap.put("error", "Please field all params");
				dataMap.put("status", "0");
				return dataMap;
			}
			// Save Location Data
			Location location = new Location();
			location.setLat(lat);
			location.setLng(lng);
			location.setLocationName(locationName);
			location = locationService.saveLocation(location);
			dataMap.put("message", "Location created successfully");
			dataMap.put("status", "1");
			dataMap.put("Location", location);
			return dataMap;
		} catch (Exception ex) {
			dataMap.put("message", "Exception found :" + ex);
			dataMap.put("status", "0");
			return dataMap;
		}
	}

	/**
	 * Delete /delete --> Delete a location from the database.
	 */
	@RequestMapping(value = "/{locationId}", method = RequestMethod.DELETE)
	public Map<String, Object> delete(@PathVariable String locationId) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			if (!locationService.findByLocationId(locationId)) {
				dataMap.put("message", "Location does not exist.");
				dataMap.put("status", "0");
				return dataMap;
			}
			locationService.deleteByLocationId(locationId);
			dataMap.put("message", "Location deleted successfully");
			dataMap.put("status", "1");
			return dataMap;
		} catch (Exception ex) {
			dataMap.put("message", "Exception found :" + ex);
			dataMap.put("status", "0");
			return dataMap;
		}
	}

	/**
	 * PUT /update --> Update a location record and save it in the database.
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Map<String, Object> update(@RequestParam String locationId, @RequestParam String lat,
			@RequestParam String lng, @RequestParam String locationName) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			if (locationId.isEmpty() || lat.isEmpty() || lng.isEmpty() || locationName.isEmpty()) {
				dataMap.put("message", "Location all field does not exist!");
				dataMap.put("status", "1");
				return dataMap;
			}
			Location location = locationService.findById(locationId);
			location.setLocationName(locationName);
			location.setLat(lat);
			location.setLng(lng);
			locationService.saveLocation(location);
			dataMap.put("message", "Location updated successfully");
			dataMap.put("status", "1");
			dataMap.put("location", location);
			return dataMap;
		} catch (Exception ex) {
			dataMap.put("message", "Location Id is invalid!");
			dataMap.put("locationId", locationId);
			dataMap.put("status", "0");
		}
		return dataMap;
	}

	/**
	 * GET /getAll --> Get all Locaiton from the database.
	 */
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public Map<String, Object> getAllLocation() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			List<Location> location = locationService.getAllLocationData();
			if (location.isEmpty()) {
				dataMap.put("message", "Location not found");
			} else {
				dataMap.put("message", "Location found successfully");
			}
			dataMap.put("totalLocation", location.size());
			dataMap.put("status", "1");
			dataMap.put("Location", location);
			return dataMap;
		} catch (Exception ex) {
			dataMap.put("message", "Exception found :" + ex);
			dataMap.put("status", "0");
			return dataMap;
		}
	}

	/**
	 * GET /getSpecific --> Get Specific a location information by location name
	 * from the database.
	 */
	@RequestMapping(value = "/getSpecific/{locationName}", method = RequestMethod.GET)
	public Map<String, Object> getSpecific(@PathVariable String locationName) {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		try {
			List<Location> location = locationService.findByLocationName(locationName);
			dataMap.put("message", "Location found successfully");
			dataMap.put("status", "1");
			dataMap.put("location", location);
			return dataMap;
		} catch (Exception ex) {
			dataMap.put("message", "Exception found :" + ex);
			dataMap.put("status", "0");
			return dataMap;
		}
	}
}