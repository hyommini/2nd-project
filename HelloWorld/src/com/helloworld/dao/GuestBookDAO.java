package com.helloworld.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.helloworld.mybatis.DBService;
import com.helloworld.vo.CommentsVO;
import com.helloworld.vo.GuestBookVO;
import com.helloworld.vo.MemberVO;

public class GuestBookDAO {

	public List<GuestBookVO> getGuestBook(Map<String, String> map) {
		SqlSession ss = DBService.getFactory().openSession();
		List<GuestBookVO> list = ss.selectList("hw.guestbook", map);
		ss.close();
		return list;
	}
	
	public MemberVO getUserInfo(String u_idx) {
		SqlSession ss = DBService.getFactory().openSession();
		MemberVO vo = ss.selectOne("hw.getUserInfo", u_idx);
		ss.close();
		return vo;
	}
	
	public List<CommentsVO> getCommentList(String g_idx) {
		SqlSession ss = DBService.getFactory().openSession();
		List<CommentsVO> list = ss.selectList("hw.getCommentList", g_idx);
		ss.close();
		return list;
	}
	
	public int insertContent(Map<String, String> map) {
		SqlSession ss = DBService.getFactory().openSession();
		int result = ss.insert("hw.insertContent", map);
		ss.commit();
		System.out.println("방명록 [ " + result + " ] 건 작성 완료");
		return result;
	}
	
	public int insertComment(Map<String, String> map) { // 파라미터 vo > map으로 변경
		SqlSession ss = DBService.getFactory().openSession();
		int result = ss.insert("hw.insertComment", map); // 파라미터 vo > map으로 변경
		ss.commit();
		System.out.println("댓글 [ " + result + " ] 건 작성 완료");
		return result;
	}
	
	public int deleteContent(String g_idx) {
		SqlSession ss = DBService.getFactory().openSession();
		ss.delete("hw.deleteFK", g_idx);
		int result = ss.delete("hw.deleteContent", g_idx);
		ss.commit();
		System.out.println("방명록(" + g_idx + "번) [ " + result + " ] 건 삭제 완료");
		return result;
	}
	
	public int updateContent(Map<String, String> map) {
		SqlSession ss = DBService.getFactory().openSession();
		int result = ss.delete("hw.updateContent", map);
		ss.commit();
		System.out.println("방명록(" + map.get("g_idx") + "번) [ " + result + " ] 건 수정 완료");
		return result;
	}
	
	public int deleteComment(String c_idx) {
		SqlSession ss = DBService.getFactory().openSession();
		int result = ss.delete("hw.deleteComment", c_idx);
		ss.commit();
		System.out.println("댓글(" + c_idx + "번) [ " + result + " ] 건 수정 완료");
		return result;
	}
}
