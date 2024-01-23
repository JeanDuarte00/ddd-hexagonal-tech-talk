package org.example.domain.rent.model;

public enum RentStatus {
	COMPLETED, CREATED;
	public static boolean isCompleted(RentStatus status){
		return status.equals(COMPLETED);
	}
}
