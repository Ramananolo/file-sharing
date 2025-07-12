package com.file.share.endpoint.authRequestBody;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}
