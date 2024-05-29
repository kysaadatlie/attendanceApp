package com.ucaproject.ucaattendancesystem.exceptions;

public class StaffNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public StaffNotFoundException(String message) {
        super(message);
    }
}
