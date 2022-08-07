package com.inventory.businessException;

public class FileUploadException extends RuntimeException{

    public FileUploadException(String message) {
        super(message);
    }
}
