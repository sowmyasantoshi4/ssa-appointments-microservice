package com.doc.ssa.dto;

import lombok.Data;

@Data
public class AppointmentDto {
	
	private Integer appointmentId;
	private String doctorName;
	private String patientName;
	private Integer age;
	private String mobileNo;
	private String address;
	private String appointmentDate;
	private String appointmentTime;
	private String appointmentForDescription;
	
}
