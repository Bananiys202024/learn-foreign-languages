package com.web.Fremdsprache.repositoryImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import com.web.Fremdsprache.entity.mongodb.Preference;
import com.web.Fremdsprache.entity.mongodb.User;
import com.web.Fremdsprache.repositories.PreferenceRepository;
import com.web.Fremdsprache.repositories.UserRepository;

public class UserProcess {

	public static void set_time_zone_user(Optional<User> found, String timezone, UserRepository users,
			PreferenceRepository preferenceRepo) {
		
		//set timezone to user
		User user = found.get();
		Preference preference = found.get().getPreference().iterator().next();
		preference.setTimezone(timezone);
		user.setPreference(new HashSet<>(Arrays.asList(preference)));
		
		preferenceRepo.save(preference);
		users.save(user);
	}

}
