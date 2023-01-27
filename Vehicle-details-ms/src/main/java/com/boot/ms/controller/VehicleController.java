package com.boot.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.boot.ms.entity.Vehicle;
import com.boot.ms.service.VehicleService;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

		@Autowired
		VehicleService service;
		@Autowired
		RestTemplate restTemplate;
		
		@GetMapping("/getall")
		public ResponseEntity<List<Vehicle>>getVehicles(){
			return new ResponseEntity<List<Vehicle>>(service.getVehicles(),HttpStatus.OK);
		}
		@GetMapping("/getvehicle/{vehicle_id}")
		public ResponseEntity<?>getVehicle(@PathVariable int vehicle_id){
			Vehicle vehicle = service.getVehicle(vehicle_id);
			ResponseEntity<?> responseEntity = null;
			
			if(vehicle == null) {
				responseEntity = new ResponseEntity<String>("No Vehicle present with given owner_id  : " + vehicle_id,
						HttpStatus.NOT_FOUND);

			} else {
				responseEntity = new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
			}
			return responseEntity;
		
			}
		@PutMapping("/update-vehicle")
		public ResponseEntity<Vehicle> updateVehicle(@RequestBody Vehicle vehicle) {
			return new ResponseEntity<Vehicle>(service.updateVehicle(vehicle), HttpStatus.OK);
		}
		
		@DeleteMapping("/delete-vehicle/{vehicle_id}")
		public ResponseEntity<String> deleteVehicle(@PathVariable int vehicle_id){
			service.deleteVehicle(vehicle_id);
			return new ResponseEntity<String>("Vehicle deleted with id: "+vehicle_id, HttpStatus.OK);
		}
	}
		
		

