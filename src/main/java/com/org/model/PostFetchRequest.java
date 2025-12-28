package com.org.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PostFetchRequest {
	private LocalDate fromDate;
	private LocalDate toDate;
	private String userType;
}
