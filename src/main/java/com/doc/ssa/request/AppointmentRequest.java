package com.doc.ssa.request;

import lombok.Data;

@Data
public class AppointmentRequest {
	
	private String doctorName;
	private String patientName;
	private Integer age;
	private String mobileNo;
	private String address;
	private String appointmentDate;
	private String appointmentTime;
	private String appointmentForDescription;
	
}
