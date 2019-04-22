package com.queens.operr.Location.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import com.queens.operr.Location.models.Location;

@Transactional
public interface LocationRepository extends MongoRepository<Location, String> {

	public List<Location> findByLocationName(String locationName);

	public Location findByLocationId(String locationId);

}