package com.example.itf_processing.exception;

import lombok.Getter;

@Getter
public class FileException extends RuntimeException {

    public FileException(String message) {
        super(message);
    }
}