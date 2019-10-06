package com.web.Fremdsprache.repositoryImpl;


import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import com.web.Fremdsprache.controllers.TrainingController;
import com.web.Fremdsprache.entity.mongodb.Dictionary;
import com.web.Fremdsprache.entity.mongodb.Preference;
import com.web.Fremdsprache.entity.mongodb.User;
import com.web.Fremdsprache.repositories.DictionaryRepository;
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

	public static Date get_timezone(UserRepository users, Optional<User> found) {
		
		//get timezone
		String timezone = found.get().getPreference().iterator().next().getTimezone();
		
		Instant nowUtc = Instant.now();
		DateTimeZone time_zone = DateTimeZone.forID(timezone);
		DateTime result_current_time = nowUtc.toDateTime(time_zone);

		return result_current_time.toDate();
	}

	public static void synchronize_dictionary_for_words_on_reapeating_checking_if_day_expired_for_words_on_reapeat(User user,
			DictionaryRepository dictionaryRepository) {
			
			Optional<Dictionary> found = dictionaryRepository.findByOwner(user.getEmail());
			
			if(found.isPresent())
			{
				 Date time_repeat = found.get().getWords().iterator().next().getDateRepeat();
				 long diffInMillies = Math.abs(new Date().getTime() - time_repeat.getTime());
				 long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
				 logger.info("Found time---"+diff);
				 
			}
			//synchronize dictionary
			//get field of "dateReapeat"
			//if value(time) more than 24 houres from now then change all values of all words from "dateReapeat" to false in particular timezone

	
	}

}
