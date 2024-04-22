package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Board;
import com.example.demo.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceimpl implements BoardService {

	private final BoardRepository boardRepository;
	
	@Override	// 全件取得
	public List<Board> getAllBoard() {
		
		List<Board> boardList = new ArrayList<Board>();
		boardList = boardRepository.findAll();
		return boardList;
	}

	@Override	// 1件取得
	public Board getBoard(Integer id) {
		
		Optional<Board> boardOpt = boardRepository.findById(id);
		Board board = new Board();
		
		if(boardOpt.isPresent()) {
			board = boardOpt.get();
		} else {
			System.out.println("board取得エラー");
		}
		
		return board;
	}

	@Override	// 削除
	public void deleteBoard(Integer id) {
		boardRepository.deleteById(id);
	}

	@Override	// 投稿
	public String updateBoard(Board board) {
		
		try {
			boardRepository.save(board);
		} catch (Exception e) {
			System.out.println("投稿失敗");
			return "投稿失敗";
		}
		return null;
		
	}
	

}
