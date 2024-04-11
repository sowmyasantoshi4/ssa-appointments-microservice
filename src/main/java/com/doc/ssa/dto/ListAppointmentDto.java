package com.doc.ssa.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ListAppointmentDto {
	
	private Integer appointmentId;
	private String doctorName;
	private String patientName;
	private Integer age;
	private String mobileNo;
	private String address;
	private String appointmentDate;
	private String appointmentTime;
	private String appointmentForDescription;
	private String status;
//	private Integer consultationId;
//	private String prescription;
//	private String feedback;
//	private Integer rating;

}
