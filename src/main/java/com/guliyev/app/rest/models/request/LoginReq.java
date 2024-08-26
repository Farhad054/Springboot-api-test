package com.guliyev.app.rest.models.request;
import lombok.Data;

@Data
public class LoginReq {
    private String email;
    private String password;
}
