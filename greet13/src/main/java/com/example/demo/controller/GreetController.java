package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.form.GreetForm;
import com.example.demo.service.GreetService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class GreetController {

	private final GreetService greetService;
	
//	@GetMapping({"/","/greet"})
	@GetMapping("/greet")
	public ModelAndView greet(ModelAndView modelAndView) {
		List<String> languages = greetService.getLanguages();
		modelAndView.setViewName("greet");
		modelAndView.addObject("language","日本語");
		modelAndView.addObject("languageOption",languages);
		modelAndView.addObject("timeframe","morning");
		return modelAndView;
	}
	
	@PostMapping("/greet")
	public ModelAndView greetWhat(@RequestParam("language") String language,
								  @RequestParam("timeframe") String timeframe,
								  ModelAndView modelAndView) {
		
		List<String> languages = greetService.getLanguages();
		String message = greetService.sayMessage(language,timeframe);
		
		modelAndView.setViewName("greet");
		modelAndView.addObject("language",language);
		modelAndView.addObject("languageOption",languages);
		modelAndView.addObject("timeframe",timeframe);
		modelAndView.addObject("message",message);
		return modelAndView;
	
	}

	@GetMapping("/input")
	public String inputGreet(Model model) {
		model.addAttribute("greetForm",new GreetForm());
		return "input";
	}

//	@PostMapping("/register")
	@PostMapping("/input")
	public ModelAndView registerGreet(@ModelAttribute @Validated GreetForm greetForm,BindingResult result,ModelAndView modelAndView) {
		//バリデーションエラーチェック
		if(result.hasErrors()) {
			modelAndView.setViewName("input");
			return modelAndView;
		}
		
		//入力挨拶格納
		String returnMessage = greetService.saveGreetMessage(greetForm);
		
		// 戻り値Stringメソッドのため、空でエラー判定する
		if(returnMessage != null) {
			modelAndView.addObject("error",returnMessage);
		}
		modelAndView.setViewName("result");
		return modelAndView;
	}
}
