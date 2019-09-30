package com.web.Fremdsprache.model;

import lombok.Data;

public @Data class AuthBody {

    private String email;
    private char[] password;

}