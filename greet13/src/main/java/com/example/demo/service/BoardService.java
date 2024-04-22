package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Board;

public interface BoardService {
	
	// 全件取得
	public List<Board> getAllBoard();
	
	// 1件取得
	public Board getBoard(Integer id);
	
	// 削除
	public void deleteBoard(Integer id);
	
	// 投稿
	public String updateBoard(Board board);
	

}
