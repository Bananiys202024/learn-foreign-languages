package com.web.Fremdsprache.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.web.Fremdsprache.config.JwtTokenProvider;
import com.web.Fremdsprache.entity.mongodb.User;
import static org.springframework.http.ResponseEntity.ok;
//this class doesn't conatin any logic, is simple create object on base of inputed parameters;
public class Creator {

	public static ResponseEntity generate_user_response_in_process_login(String username, char[] password,
			User user, JwtTokenProvider jwtTokenProvider) {
		
		String token = jwtTokenProvider.createToken(username, user.getRoles());
		
        Map<Object, Object> model = new HashMap<>();
        model.put("email", username); // It is really email, but have name of "username"
        model.put("token", token);
        model.put("password", password);
        model.put("username", user.getUsername());
        model.put("role", user.getRoles().iterator().next().getRole());
		
		return ok(model);
	}

}
