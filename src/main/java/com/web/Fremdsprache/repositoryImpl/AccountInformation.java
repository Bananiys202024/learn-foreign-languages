package com.web.Fremdsprache.repositoryImpl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.web.Fremdsprache.entity.mongodb.Dictionary;
import com.web.Fremdsprache.entity.mongodb.Preference;
import com.web.Fremdsprache.entity.mongodb.User;
import com.web.Fremdsprache.entity.redis.Experience;
import com.web.Fremdsprache.model.BanchProgressData;
import com.web.Fremdsprache.repositories.CashExperience;
import com.web.Fremdsprache.repositories.CashePersistence;
import com.web.Fremdsprache.repositories.DictionaryRepository;
import com.web.Fremdsprache.repositories.UserRepository;

public class AccountInformation {

	public static BanchProgressData generate_progress_information(DictionaryRepository dictionaryRepository,
			UserRepository users, String owner, CashExperience cashExperience) {
		
		List<Dictionary> list = dictionaryRepository.findAll()
										.stream()
										.filter(c -> c.getOwner().equals(owner))
										.collect(Collectors.toList());
		
		short level_experience_division_part_of_indicator_of_level = 500;
		
		int learned_words = (int) list.stream().filter(c -> c.getWords().iterator().next().isLearned()==true ).count();
		int all_words_dictionary = list.size();
		int learning_words = all_words_dictionary-learned_words;
		long current_level_experience = users.findByEmail(owner).get().getPreference().iterator().next().getExperience();
		int experience_before_next_level = 1422; //there is will method for count that number;

		byte experience_earned_today = get_experience_earned_today(cashExperience, owner);
		short current_level = (byte) (current_level_experience/500);
		short before_next_level =  count_experience_to_next_level(level_experience_division_part_of_indicator_of_level, current_level_experience);
		byte how_do_many_days_persistence_of_learning = get_persistence_days_of_learning();
		
		int detected_words = 0; //we will add it later

		return BanchProgressData
								.builder()
								.learned_words(learned_words)
								.detected_words(detected_words)
								.learning_words(learning_words)
								.all_words_dictionary(all_words_dictionary)
								.current_level_experience(current_level_experience)
								.experience_before_next_level(experience_before_next_level)
								.experience_earned_today(experience_earned_today)
								.current_level(current_level)
								.before_next_level(before_next_level)
								.how_do_many_days_persistence_of_learning(how_do_many_days_persistence_of_learning)
								.build();
	}

	private static short count_experience_to_next_level(short level_experience_division_part_of_indicator_of_level, long current_level_experience) {

		
		while(current_level_experience>level_experience_division_part_of_indicator_of_level)
		{
			current_level_experience-=level_experience_division_part_of_indicator_of_level;
		}
		
		return current_level_experience==0?level_experience_division_part_of_indicator_of_level: (short) (500-current_level_experience);	
	}

	private static byte get_persistence_days_of_learning() {
		return 3;
	}

	private static byte get_experience_earned_today(CashExperience cashExperience, String owner) {
		Optional<Experience> found = cashExperience.findById(owner);		
		return (byte) (found.isPresent()?found.get().getExperience():0);
	}



	public static int days_persistence_learning(UserRepository users, String loggedUser,
			CashePersistence cashe_persistence) {
		
//		Optional<User> found = users.findByEmail(loggedUser);
//		
//		if(found.isPresent())
//		{
//		Set<Preference> set_preference = found.get().getPreference();
//		Preference preference = set_preference.iterator().next();
//		String timezone = preference.getTimezone();
//		Date current_date = new Date();
//		
//		}
//		else
//		{
//			return 0;
//		}
		
		//users get time-zone
		//count date from now;
		//check if in cashe_persistence exist user(owner)
		//if no then create new example of cash_persistence objecct PersistenceCash
		//with current data, days to one , owner -loggedName
		//if yes check last date, if current date equals by lastDate.
		//then miss  and return
		//if last date not equals them
		//update object cahePersitenct of cashe_persistence
		//days+1, if days==5 then goes to 1;
		//date current set and set owner-loggedName.
		
		
		return 3;
	}

}
