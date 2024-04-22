package com.example.demo.controller;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Board;
import com.example.demo.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("/board")
	public ModelAndView getBoard(ModelAndView modelAndView) {
		
		List<Board> boardList = boardService.getAllBoard();
		
		modelAndView.setViewName("board");
		modelAndView.addObject("boardList",boardList);
	

		return modelAndView;
	}
	
//	@GetMapping("/deleteBoard")
//	@PostMapping("/updateBoard")
}
