package com.boot.ms.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.boot.ms.entity.Person;
import com.boot.ms.model.Vehicle;

@Repository
public interface PersonRepo extends JpaRepository<Person, Integer> {

	@Query(name = "findByVehicleId", value = "select a from Person a where a.vehicle_id = vehicle_id")
	public List<Person> findPersonByVehicleId(int vehicle_id);
	
}
