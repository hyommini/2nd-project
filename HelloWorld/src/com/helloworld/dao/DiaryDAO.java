package com.helloworld.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.helloworld.mybatis.DBService;
import com.helloworld.vo.CommentsVO;
import com.helloworld.vo.DiaryVO;
import com.helloworld.vo.MemberVO;

public class DiaryDAO {
	
	public static int getIdPw(String id,String pw){
		Map<String,String> map = new HashMap<>();
		
		map.put("id", id);
		map.put("pw", pw);
		
		SqlSession ss = DBService.getFactory().openSession();
		int result = ss.selectOne("hw.idPw", map);
		ss.close();
		return result;
	}
	
	//u_idx값 추출
//	public static int getOneUIdx (String id, String pw) {
//		Map<String, String> map = new HashMap<>();
//		map.put("id", id);
//		map.put("pw", pw);
//		
//		SqlSession ss = DBService.getFactory().openSession();
//		int result =  ss.selectOne("hw.oneUIdx", map);
//		ss.close();
//		return result;		
//	}
	
	//하나의 MemberVO 객체 조회
	public static MemberVO getOne (String id, String pw) {
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pw", pw);
		System.out.println("map : " + map);
		SqlSession ss = DBService.getFactory().openSession();
		MemberVO vo = ss.selectOne("hw.oneMember", map);
		ss.close();
		return vo;		
	}
	
	// d_idx로 다이어리 1건 조회
	public static DiaryVO getOneDiary(int dIdx) {
		SqlSession ss = DBService.getFactory().openSession();
		DiaryVO dvo =  ss.selectOne("hw.dOne", dIdx);
		ss.close();
		return dvo;
	}
	
	// 모든 회원이 작성한 다이어리 건수 조회
	public static int getDiaryTotalCount() {
		SqlSession ss = DBService.getFactory().openSession();
		int diaryTotalCount = ss.selectOne("hw.diaryTotalCount");
		ss.close();
		return diaryTotalCount;		
	}

	// 회원이 작성한 다이어리 건수 조회
	public static int getDiaryCount(int uIdx) {
		SqlSession ss = DBService.getFactory().openSession();
		int diaryTotalCount = ss.selectOne("hw.diaryCount", uIdx);
		ss.close();
		return diaryTotalCount;		
	}
	
	// 현재페이지에 해당하는 다이어리목록 가져오기
	public static List<DiaryVO> getListDiary(int begin, int end, int uIdx) {
		Map<String, Integer> map = new HashMap<>();
		map.put("begin", begin);
		map.put("end", end);
		map.put("uIdx", uIdx);
		
		SqlSession ss = DBService.getFactory().openSession();
		List<DiaryVO> list = ss.selectList("hw.dlist", map);
		ss.close();
		return list;
	}
	
	//다이어리 작성
	public static int setDiary (DiaryVO vo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.insert("hw.writeDiary", vo);
		ss.close();
		return result;		
	}
	
	//다이어리 수정
	public static int modiDiary (DiaryVO vo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.update("hw.modifyDiary", vo);
		ss.close();
		return result;		
	}
	
	//다이어리 삭제
	public static int deleDiary (int dIdx) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.delete("hw.deleteDiary", dIdx);
		ss.close();
		return result;		
	}
	
	//해당 u_idx에 해당하는 유저가 가장 최근에 작성한 다이어리 하나 가져오기
	public static DiaryVO newDiary (String u_idx) {
		SqlSession ss = DBService.getFactory().openSession();
		DiaryVO dvo = ss.selectOne("hw.newDiary", u_idx);
		ss.close();
		return dvo;
	}
	//-------------------다이어리 댓글 영역-----------------------
	//d_idx에 해당하는 댓글 조회
	public static List<CommentsVO> getDiaryCommentList(int d_idx) {
		SqlSession ss = DBService.getFactory().openSession();
		List<CommentsVO> list = ss.selectList("hw.dCommentAll", d_idx);
		ss.close();
		return list;
	}
	//댓글 작성
	public static int setDiaryComment(CommentsVO vo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result =  ss.insert("hw.dCommentSet", vo);
		ss.close();
		return result;
	}
	
	//댓글 1개 조회
	public static CommentsVO getDiaryCommentOne(int c_idx) {
		SqlSession ss = DBService.getFactory().openSession(true);
		CommentsVO vo = ss.selectOne("hw.dCommentOne", c_idx);
		ss.close();
		return vo;
	}
	
	//댓글 수정
	public static int modiDiaryComment(CommentsVO vo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.update("hw.dCommentModi", vo);
		ss.close();
		return result;
	}
	
	//댓글 삭제
	public static int delDiaryComment(int c_idx, int d_idx) {
		Map<String, Integer> map = new HashMap<>();
		map.put("c_idx", c_idx);
		map.put("d_idx", d_idx);
		
		SqlSession ss = DBService.getFactory().openSession(true);
		int result =  ss.delete("hw.dCommentDel", map);
		ss.close();
		return result;
	}
	
	

}








