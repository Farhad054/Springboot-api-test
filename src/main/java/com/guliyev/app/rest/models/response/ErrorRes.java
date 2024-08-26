package com.guliyev.app.rest.models.response;
import org.springframework.http.HttpStatus;
import lombok.Data;
@Data
public class ErrorRes {
    HttpStatus httpStatus;
    String message;
    public ErrorRes(HttpStatus status, String message) {
        this.httpStatus = status;
        this.message = message;
    }

}
