package com.web.Fremdsprache.repositoryImpl;


import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.TimeZone;

import com.web.Fremdsprache.controllers.TrainingController;
import com.web.Fremdsprache.entity.mongodb.Preference;
import com.web.Fremdsprache.entity.mongodb.User;
import com.web.Fremdsprache.repositories.PreferenceRepository;
import com.web.Fremdsprache.repositories.UserRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Instant;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;


public class UserProcess {

	private static final Logger logger = LogManager.getLogger(UserProcess.class);
	
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

	public static LocalDateTime get_timezone(UserRepository users, Optional<User> found) {
		
		//get timezone
		String timezone = found.get().getPreference().iterator().next().getTimezone();
		
		Instant nowUtc = Instant.now();
		DateTimeZone time_zone = DateTimeZone.forID(timezone);
		DateTime result_current_time = nowUtc.toDateTime(time_zone);

        LocalDateTime now = result_current_time.toLocalDateTime();

		return result_current_time.toLocalDateTime();
	}

}
