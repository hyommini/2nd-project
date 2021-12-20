package com.helloworld.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.helloworld.vo.BoardVO;
import com.helloworld.mybatis.DBService;

public class BoardDAO {
	//현재 페이지에 해당하는 글목록(게시글) 가져오기
	public static List<BoardVO> getList(int begin, int end, int u_idx) {
		SqlSession ss = DBService.getFactory().openSession();
		Map<String, Integer> map = new HashMap<>();
		map.put("begin", begin);
		map.put("end", end);
		map.put("u_idx", u_idx);
		List<BoardVO> list = ss.selectList("hw.boardList", map);
		ss.close();
		return list;
	} 
	
	//총 게시글 수 구하기
	public static int getTotalCount(int u_idx) {
		SqlSession ss = DBService.getFactory().openSession();
		int cnt = ss.selectOne("hw.boardCount", u_idx);
		ss.close();
		return cnt;
	}
	
	//게시글 데이터베이스에 저장하기
	public static int insert(BoardVO bvo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.insert("hw.insert", bvo);
		ss.close();
		return result;
	}
	
	//게시글 상세보기
	public static BoardVO detail(String b_idx) {
		SqlSession ss = DBService.getFactory().openSession();
		BoardVO bvo = ss.selectOne("hw.detail", b_idx);
		ss.close();
		return bvo;
	}
	
	//해당 글번호를 가진 게시글 삭제
	public static void delete(String b_idx) {
		System.out.println("DAO b_idx : " + b_idx);
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.delete("hw.delete", b_idx);
		ss.close();
		if (result == 1) {
			System.out.println("게시글 삭제 완료!");
		}else if (result == 0) {
			System.out.println("게시글 삭제 실패!");
		}
	}
	
	//게시글 수정
	public static void update(String title, String content, String b_idx) {
		Map<String, String> map = new HashMap<>();
		map.put("title", title);
		map.put("content", content);
		map.put("b_idx", b_idx);
		SqlSession ss = DBService.getFactory().openSession(true);
		ss.update("hw.update", map);
		ss.close();
	}
	
	//조회수 증가 메서드
	public static void hitPlus(String b_idx) {
		SqlSession ss = DBService.getFactory().openSession(true);
		ss.update("hw.boardHitPlus", b_idx);
		ss.close();
	}
	
	//달린 댓글이 24시간 이내인 게시글 제목을 불러오는 메서드
	public static List<BoardVO> newComments(String u_idx) {
		SqlSession ss = DBService.getFactory().openSession();
		List<BoardVO> blist = ss.selectList("hw.newComments", u_idx);
		ss.close();
		return blist;
	}
	
	//24시간 이내에 작성된 게시글 하나 불러오기
	public static BoardVO newBoard(String u_idx) {
		SqlSession ss = DBService.getFactory().openSession();
		BoardVO bvo = ss.selectOne("hw.newBoard", u_idx);
		ss.close();
		return bvo;
	}
}
