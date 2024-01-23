package org.example.domain.model;

public enum RentStatus {
	COMPLETED, CREATED;
	public static boolean isCompleted(RentStatus status){
		return status.equals(COMPLETED);
	}
}
