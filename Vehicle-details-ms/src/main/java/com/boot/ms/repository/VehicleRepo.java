package com.boot.ms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.ms.entity.Vehicle;


public interface VehicleRepo extends JpaRepository<Vehicle, Integer> {

}
