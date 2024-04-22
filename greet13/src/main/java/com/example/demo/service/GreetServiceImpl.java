package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.GreetMessage;
import com.example.demo.form.GreetForm;
import com.example.demo.repository.GreetRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class GreetServiceImpl implements GreetService {

	private final GreetRepository greetRepository;
	
	@Override //言語登録チェック
	public String saveGreetMessage(GreetForm greetForm) {
		
		List<String> languages = getLanguages();
		for(String language:languages) {
			if(language.equals(greetForm.getLanguage())) {
				return "指定した言語は既に登録されています";
			}
		}

		//形式の変換
		GreetMessage greetMessage = new GreetMessage();
		greetMessage.setLanguage(greetForm.getLanguage());
		greetMessage.setMorning(greetForm.getMorning());
		greetMessage.setNoon(greetForm.getNoon());
		greetMessage.setEvening(greetForm.getEvening());
		greetMessage.setNight(greetForm.getNight());
		greetMessage.setRemarks(greetForm.getRemarks());
		
		
		//挨拶の登録
		try {
			greetRepository.save(greetMessage);
		} catch(Exception e) {
			System.out.println("DB書き込みエラー");
			return "DB書き込み時エラー";
		}
		return null;
	}

	@SuppressWarnings("null")
	@Override
	public List<String> getLanguages() {
		List<String> languages = new ArrayList<String>();
//		List<GreetMessage> greetMessages = greetRepository.findAll();
//		greetMessages.forEach(message -> languages.add(message.getLanguage()));
		languages = greetRepository.getLanguages();
		return languages;
	}

	@Override
	public String sayMessage(String language,String timeframe) {
		
		GreetMessage message = greetRepository.findBylanguage(language);
		
		switch (timeframe) {
		case "morning": return message.getMorning();
		case "noon": return message.getNoon();
		case "evening": return message.getEvening();
		case "night": return message.getNight();
		default: return "登録されていません";
		}
	}
}
