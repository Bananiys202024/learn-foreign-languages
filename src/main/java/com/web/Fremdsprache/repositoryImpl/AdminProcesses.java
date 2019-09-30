package com.web.Fremdsprache.repositoryImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.web.Fremdsprache.entity.mongodb.Preference;
import com.web.Fremdsprache.entity.mongodb.User;
import com.web.Fremdsprache.initializer.Initializing;
import com.web.Fremdsprache.repositories.PreferenceRepository;
import com.web.Fremdsprache.repositories.UserRepository;

public class AdminProcesses {

	private static final Logger logger = LogManager.getLogger(AdminProcesses.class);

	public static void disable_user_by_email(String email, UserRepository users, User found, PreferenceRepository preferenceRepo) {

		 Set<Preference> got = (Set<Preference>) found.getPreference();
		 Preference preference = got.iterator().next();
		 preference.setEnabled(false);
		
	    found.setPreference(new HashSet<>(Arrays.asList(preference)));

		users.save(found);
		preferenceRepo.save(preference);

	}

}
