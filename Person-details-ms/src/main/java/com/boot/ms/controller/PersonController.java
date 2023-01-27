package com.boot.ms.controller;

import java.util.List;

import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
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

import com.boot.ms.entity.Person;
import com.boot.ms.model.FailureResponse;
import com.boot.ms.model.PersonVehicleResponse;
import com.boot.ms.model.Vehicle;
import com.boot.ms.service.PersonService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/person")
@RibbonClient(name = "VEHICLE-MS")
public class PersonController {
	@Autowired
	PersonService service;
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/getall")
	public ResponseEntity<List<Person>> getPersons(){
		return new ResponseEntity<List<Person>>(service.getPersons(),HttpStatus.OK);
	}	
	@GetMapping("/getperson/{owner_id}")
	public ResponseEntity<?>getPerson(@PathVariable int owner_id){
		Person person = service.getPerson(owner_id);
		ResponseEntity<?>responseEntity = null;
		
		if(person==null) {
			responseEntity= new ResponseEntity<String>("No Person present with the given Vehicle id : " + owner_id, HttpStatus.NOT_FOUND);
			}
		else {
			responseEntity = new ResponseEntity<Person>(person,HttpStatus.OK);
		}
		return responseEntity;
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/getPersonAndVehicle/{vehicle_id}")
	@HystrixCommand(fallbackMethod = "myFallBackMethod")
	public ResponseEntity<?> getPersAndVeh(@PathVariable int vehicle_id ) {

		List<Person> personList = service.getPersonByVehicleId(vehicle_id);
		ResponseEntity<?> responseEntity = null;

		if (personList.isEmpty()) {
			responseEntity = new ResponseEntity<String>("No person present with the given vehicle id: " + vehicle_id,
					HttpStatus.NOT_FOUND);
		} else {
			Vehicle vehicle = restTemplate
					.getForObject("http://localhost:8000/vehicle/getvehicle/" + vehicle_id, Vehicle.class);

			PersonVehicleResponse response = new PersonVehicleResponse();
			response.setPersonList(personList);
			response.setVehicle(vehicle);
			
			responseEntity = new ResponseEntity<PersonVehicleResponse>(response, HttpStatus.OK);
		}
		return responseEntity;
	}

	public ResponseEntity<?> myFallBackMethod(@PathVariable int vehicle_id) {
		List<Person> personList = service.getPersonByVehicleId(vehicle_id);
		ResponseEntity<?> responseEntity = null;

		if (personList.isEmpty()) {
			responseEntity = new ResponseEntity<String>("No person present with the given vehicle id: " + vehicle_id,
					HttpStatus.NOT_FOUND);
		} else {
			FailureResponse response = new FailureResponse();
			response.setPersonList(personList);
			response.setMessage("Vehicle service is down due to maintainance");
			responseEntity = new ResponseEntity<FailureResponse>(response, HttpStatus.SERVICE_UNAVAILABLE);
		}
		
		return responseEntity;
	}
	
	
	@PutMapping("/update-person")
	public ResponseEntity<Person> updatePerson(@RequestBody Person person) {
		return new ResponseEntity<Person>(service.updatePerson(person), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete-person/{owner_id}")
	public ResponseEntity<String> deletePerson(@PathVariable int owner_id){
		service.deletePerson(owner_id);
		return new ResponseEntity<String>("Person deleted with id: "+owner_id, HttpStatus.OK);
	}
}
	

