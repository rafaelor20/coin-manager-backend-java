package com.example.coinmanager.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class ErrorObject {
    private Integer statusCode;
    private String message;
    private Date timestamp;
}
