package com.web.Fremdsprache.util;

import java.io.IOException;
import java.util.Date;

import com.web.Fremdsprache.entity.mongodb.ConstEnDict;
import com.web.Fremdsprache.entity.mongodb.Words;
import com.web.Fremdsprache.translator.Translator;

public class Convertor {

	public static Words convert_class_ConstEnDict_to_class_Words_for_process_insert_10_words(ConstEnDict enty,
			long maxId, String owner) throws IOException {
		return  Words.builder()
				.id(maxId+1)
				.dateLearned(new Date())
				.dateRepeat(new Date())
				.learned(false)
				.englishWord(enty.getWord())
				.russianWord(Translator.translate("ru", enty.getWord() ))
				.owner(owner)
				.repeatTomorrow(false)
				.build();	
	}





}
