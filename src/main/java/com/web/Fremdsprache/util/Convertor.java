package com.web.Fremdsprache.util;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.web.Fremdsprache.entity.mongodb.DictionaryEnglish;
import com.web.Fremdsprache.entity.redis.TrainingWords;
import com.web.Fremdsprache.entity.redis.TrainingWordsMistakes;


public class Convertor {

	public static List<TrainingWords> ConvertFromDictionaryEntityToTrainingWordsEntity(List<DictionaryEnglish> collect, String owner) {
		
		List<TrainingWords> list = new ArrayList<TrainingWords>();
		
		long count=0;
		
		for(DictionaryEnglish dictionary :collect)
		{
			++count;
			list.add(
					TrainingWords.builder()
					.englishWord(dictionary.getWordEnglish())
					.russianWord(dictionary.getWordRussian())
					.owner(owner)
					.id(owner+""+count)
					.autoCrementId(count)
					.reallyId(dictionary.getId())
					.build()
					);
		}
		
		return list;
	}

	public static List<DictionaryEnglish> convertFromTrainingWordsToDictionaryEnglishWithRightAnswers(List<TrainingWords> right, String owner) {
		
		List<DictionaryEnglish> rightList = new ArrayList();
		
		for(TrainingWords modeL :right)
		rightList.add(
				  DictionaryEnglish.builder()
				  .id(modeL.getReallyId())
				  .owner(owner)
				  .wordEnglish(modeL.getEnglishWord())
				  .wordRussian(modeL.getRussianWord())
				  .repeatTomorrow(false)
				  .dateRepeat(new Date())
				  .learned(true)
				  .dateLearned(new Date())
				  .build()
				);
		
		return rightList;
	}

	public static List<DictionaryEnglish> convertFromTrainingWordsMistakesToDictionaryEnglishWithWrongAnswers(
			List<TrainingWordsMistakes> mistakes, String owner) {

		List<DictionaryEnglish> mistakesList = new ArrayList();
		
		for(TrainingWordsMistakes modeL : mistakes)
		mistakesList.add(
						  DictionaryEnglish.builder()
						  .id(modeL.getReallyId())
						  .owner(owner)
						  .wordEnglish(modeL.getEnglishWord())
						  .wordRussian(modeL.getRussianWord())
						  .repeatTomorrow(true)
						  .dateRepeat(new Date())
						  .learned(false)
						  .dateLearned(new Date())
						  .build()
						);
		
		return mistakesList;
	}
	
	
}
