package com.ucaproject.ucaattendancesystem.exceptions;

public class PersonInfoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public PersonInfoNotFoundException(String message) {
        super(message);
    }
}

