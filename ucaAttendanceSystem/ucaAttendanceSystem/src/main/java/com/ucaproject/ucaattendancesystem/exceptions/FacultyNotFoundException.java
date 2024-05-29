package com.ucaproject.ucaattendancesystem.exceptions;

public class FacultyNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public FacultyNotFoundException(String message) {
        super(message);
    }
}
