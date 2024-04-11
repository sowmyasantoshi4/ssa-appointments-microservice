package com.doc.ssa.request;

import lombok.Data;

@Data
public class FeedbackRequest {
	
	private Integer consultationId;
	private Integer rating;
	private String feedback;

}
