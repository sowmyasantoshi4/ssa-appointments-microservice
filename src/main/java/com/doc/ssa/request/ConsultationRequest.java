package com.doc.ssa.request;

import lombok.Data;

@Data
public class ConsultationRequest {
	
	private Integer appointmentId;
	private String prescription;

}
