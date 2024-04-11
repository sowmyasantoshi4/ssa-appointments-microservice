package com.doc.ssa.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name="appointments")
@Data
public class Appointment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appointment_id")
	private Integer appointmentId;
	@Column(name = "doctor_name")
	private String doctorName;
	@Column(name = "patient_name")
	private String patientName;
	@Column(name = "age")
	private Integer age;
	@Column(name = "mobile_no")
	private String mobileNo;
	@Column(name = "address")
	private String address;
	@Column(name = "appointment_date")
	private Date appointmentDate;
	@Column(name = "appointment_time")
	private String appointmentTime;
	@Column(name = "appointment_for_desc")
	private String appointmentForDescription;
	
}
