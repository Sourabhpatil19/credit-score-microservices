package com.project.data_collection_service.expection;



public class ResourceNotFoundException
        extends RuntimeException {

    public ResourceNotFoundException(String message, Long userId) {

        super(message);

    }
}