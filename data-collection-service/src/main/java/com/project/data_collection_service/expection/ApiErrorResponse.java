package com.project.data_collection_service.expection;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ApiErrorResponse {

    private String message;
    private int status;
    private LocalDateTime timestamp;
}
