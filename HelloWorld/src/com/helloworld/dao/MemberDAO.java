package com.helloworld.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.helloworld.mybatis.DBService;
import com.helloworld.vo.MemberVO;


public class MemberDAO {
	
	// id, pw를 입력받아 해당 유저가 있는지 확인
	public static int getIdPw(String id, String pw){
		System.out.println("getIdPw 실행");
		Map<String, String> map = new HashMap<>();
		map.put("id", id);
		map.put("pw", pw);
		
		SqlSession ss = DBService.getFactory().openSession();
		int result = ss.selectOne("hw.idPw",map);
		ss.close();
		return result;
	}
	
	//id를 입력받아 해당 유저 객체를 반환
	public static MemberVO selectOne(String id) {
		SqlSession ss = DBService.getFactory().openSession();
		MemberVO mvo = ss.selectOne("hw.one", id);
		return mvo;
	}
	//idx 를 입력받아 해당 유저 객체를 반환
	public static MemberVO selectByIdx(String u_idx) {
		SqlSession ss = DBService.getFactory().openSession();
		MemberVO mvo = ss.selectOne("hw.selectById", u_idx);
		return mvo;
	}
	
	//닉네임을 입력받아 해당 유저의 객체 반환
	public static MemberVO selectByNickname(String nickname) {
		SqlSession ss = DBService.getFactory().openSession();
		MemberVO vo = ss.selectOne("hw.byNickname", nickname);
		return vo;
	}
	
	//중복 아이디 검사용
	public static int chkId(String id) {
		SqlSession ss = DBService.getFactory().openSession();
		System.out.println("MEMBERDAO : " + id);
		int result = ss.selectOne("hw.chkId", id);
		ss.close();
		return result;
	}
		
	//중복 닉네임 검사용
	public static int chkNickname(String id) {
		SqlSession ss = DBService.getFactory().openSession();
		int result = ss.selectOne("hw.chkNickname", id);
		ss.close();
		return result;
	}

	//회원 가입
	public static int insMember(Map map) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.insert("hw.insMember", map);
		ss.close();
		return result;
	}

	//ID 찾기
	public static String findId(Map<String, String> map) {
		SqlSession ss = DBService.getFactory().openSession();
		String result = ss.selectOne("hw.findId", map);
		ss.close();
		return result;
	}	
	
	//PASSWORD 찾기(재설정)
	public static String findPwd(Map<String, String> map) {
		SqlSession ss = DBService.getFactory().openSession();
		String result = ss.selectOne("hw.findPwd", map);
		ss.close();
		return result;
	}
	
	//사람 찾기
	public static List<MemberVO> findPeople(String nickname){
		SqlSession ss = DBService.getFactory().openSession();
		List<MemberVO> list = ss.selectList("hw.findPeople", nickname);
		ss.close();
		return list;
	}
	
	//비밀번호 재설정
	public static int setPwd(Map<String, String> map) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.update("hw.setPwd", map);
		ss.close();
		return result;
	}
	
	//해당 u_idx를 가진 사람의 정보를 받아오기
	public static MemberVO getInfo(int u_idx) {
		SqlSession ss = DBService.getFactory().openSession();
		MemberVO mvo = ss.selectOne("hw.profile",u_idx);
		System.out.println("MemberVO getInfo : " + mvo.toString());
		ss.close();
		return mvo;
	}
	public static void infoUpdate(Map<String,Object> map) {
		SqlSession ss = DBService.getFactory().openSession();
		ss.close();
		ss.update("hw.pUpdate",map);
	}
	//프로필 수정
	public static void profileUpdate(MemberVO vo) {
		
		System.out.println("프로필 수정 시작 ");
		SqlSession ss = DBService.getFactory().openSession(true);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("nickname", vo.getNickname());
		map.put("birth", vo.getBirth());
		map.put("gender", vo.getGender());
		map.put("email", vo.getEmail());
		map.put("phone", vo.getPhone());
		map.put("u_idx", vo.getU_idx());
		
		System.out.println("map:"+map);
		ss.update("hw.pUpdate",map);
		
		ss.close();
	}

}
