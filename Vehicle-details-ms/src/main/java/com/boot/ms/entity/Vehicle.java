package com.boot.ms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicle_details")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

	@Id
	@Column(name = "vehicle_id")
	private int vehicle_id;
	@Column(name = "Vechile_Model")
	private String Vechile_Model;
	@Column(name = "Vechile_no")
	private String Vechile_no;
	
}
