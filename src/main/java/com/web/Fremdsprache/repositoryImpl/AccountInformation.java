package com.web.Fremdsprache.repositoryImpl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.web.Fremdsprache.entity.mongodb.DictionaryEnglish;
import com.web.Fremdsprache.entity.redis.Experience;
import com.web.Fremdsprache.model.BanchProgressData;
import com.web.Fremdsprache.repositories.CashExperience;
import com.web.Fremdsprache.repositories.DictionaryRepository;
import com.web.Fremdsprache.repositories.UserRepository;

public class AccountInformation {

	public static BanchProgressData generate_progress_information(DictionaryRepository dictionaryRepository,
			UserRepository users, String owner, CashExperience cashExperience) {
		
		List<DictionaryEnglish> list = dictionaryRepository.findAll()
										.stream()
										.filter(c -> c.getOwner().equals(owner))
										.collect(Collectors.toList());
		
		int learned_words = (int) list.stream().filter(c -> c.isLearned()==true ).count();
		int all_words_dictionary = list.size();
		int learning_words = all_words_dictionary-learned_words;
		long current_level_experience = users.findByEmail(owner).getExperience();
		int experience_before_next_level = 1422; //there is will method for count that number;

		byte experience_earned_today = get_experience_earned_today(cashExperience, owner);
		short current_level = (byte) (current_level_experience/500);
		short before_next_level =  (byte) (500 - current_level_experience);
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

	private static byte get_persistence_days_of_learning() {
		return 3;
	}

	private static byte get_experience_earned_today(CashExperience cashExperience, String owner) {
		Optional<Experience> found = cashExperience.findById(owner);		
		return (byte) (found.isPresent()?found.get().getExperience():0);
	}

}
