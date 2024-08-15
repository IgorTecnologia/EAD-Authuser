package com.ead.authuser.resources.exception;

import lombok.*;

import java.time.*;

@Data
public class StandardError {

    private Instant timeStamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
