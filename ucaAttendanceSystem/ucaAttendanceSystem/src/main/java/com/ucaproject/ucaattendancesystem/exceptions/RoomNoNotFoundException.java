package com.ucaproject.ucaattendancesystem.exceptions;

public class RoomNoNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public RoomNoNotFoundException(String message) {
        super(message);
    }
}

