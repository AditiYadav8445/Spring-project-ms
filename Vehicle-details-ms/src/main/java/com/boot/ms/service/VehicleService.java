package com.boot.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.ms.entity.Vehicle;
import com.boot.ms.repository.VehicleRepo;



@Service
public class VehicleService {

	@Autowired
	VehicleRepo repository;

	public List<Vehicle> getVehicles() {
		return repository.findAll();

	}

	public Vehicle getVehicle(int owner_id) {
		return repository.findById(owner_id).orElse(null);
	}
	public Vehicle updateVehicle(Vehicle vehicle) {
		Vehicle vehicleData = repository.findById(vehicle.getVehicle_id()).get();
		vehicleData.setVechile_Model(vehicle.getVechile_Model());
		return repository.save(vehicleData);
	}

	public void deleteVehicle(int vehicle_id ) {
		repository.deleteById(vehicle_id);
	}
}
