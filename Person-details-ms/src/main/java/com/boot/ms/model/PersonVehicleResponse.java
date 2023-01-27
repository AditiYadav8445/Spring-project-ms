package com.boot.ms.model;

import java.util.List;

import com.boot.ms.entity.Person;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonVehicleResponse {
	private List<Person> personList;
	private Vehicle vehicle;
}
