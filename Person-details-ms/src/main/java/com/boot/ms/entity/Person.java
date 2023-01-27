package com.boot.ms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Owner_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

	@Column(name = "vehicle_id")
	private int vehicle_id;
	@Column(name = "owner_name")
	private String owner_name;
	@Column(name = "owner_id")
	@Id
	private int owner_id;

}
