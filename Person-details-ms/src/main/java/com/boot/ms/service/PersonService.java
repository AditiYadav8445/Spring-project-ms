package com.boot.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.ms.entity.Person;
import com.boot.ms.respository.PersonRepo;

@Service
public class PersonService {
	@Autowired
	PersonRepo repository;

	public List<Person> getPersons() {
		return repository.findAll();
	}

	public Person getPerson(int owner_id) {
		return repository.findById(owner_id).orElse(null);
	}

	public List<Person> getPersonByVehicleId(int vehicle_id) {
		return repository.findPersonByVehicleId(vehicle_id);
	}

	public Person updatePerson(Person person) {
		Person personData = repository.findById(person.getOwner_id()).get();
		personData.setOwner_name(person.getOwner_name());
		return repository.save(personData);
	}

	public void deletePerson(int owner_id) {
		repository.deleteById(owner_id);
	}

}
