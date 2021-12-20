package com.helloworld.dao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.helloworld.mybatis.DBService;
import com.helloworld.vo.CommentsPVO;
import com.helloworld.vo.PhotoVO;

public class PhotoDAO {
	
	// 사진첩 등록
	public static int insPhoto(PhotoVO vo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.insert("hw.insPhoto", vo);
		ss.close();
		return result;
	}
	
	// 사진첩 리스트 가져오기
	public static List<PhotoVO> listAll(int u_idx) {
		SqlSession ss = DBService.getFactory().openSession();
		List<PhotoVO> list = new ArrayList<>();
		list = ss.selectList("hw.listAll", u_idx);
		ss.close();
		return list;
	}
	
	//사진첩 수정
	public static int upPhoto(PhotoVO vo) {
		System.out.println("upPhoto 실행됨");
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.update("hw.upPhoto", vo);
		ss.close();
		return result;
	}
	
	//사진첩 수정
	public static int upPhotoOrig(PhotoVO vo) {
		System.out.println("upPhotoOrig 실행됨");
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.update("hw.upPhotoOrig", vo);
		ss.close();
		return result;
	}
	
	// 사진첩 게시물 삭제
	public static int pDelete(int p_idx) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.delete("hw.delPhoto", p_idx);
		ss.close();
		return result;
	}
	
	// 사진첩 수정용 게시물 정보 가져오기
	public static PhotoVO pModifyInfo(int p_idx) {
		SqlSession ss = DBService.getFactory().openSession(true);
		PhotoVO vo = ss.selectOne("hw.pModifyInfo", p_idx);
		ss.close();
		return vo;
	}
	
	
	//------------------------------
	public static int getTotalCount(int u_idx) {
		SqlSession ss = DBService.getFactory().openSession();
		int totalCount = ss.selectOne("hw.totalCount", u_idx);
		ss.close();
		return totalCount;
	}
	
	//현재 페이지에 해당하는 글목록(게시글) 가져오기
	public static List<PhotoVO> getList(int begin, int end, int u_idxx) {
		Map<String, Integer> map = new HashMap<>();
		map.put("begin", begin);
		map.put("end", end);
		map.put("u_idx", u_idxx);
		
		SqlSession ss = DBService.getFactory().openSession();
		List<PhotoVO> list = ss.selectList("hw.list", map);
		ss.close();
		return list;
	}

	//--댓글 기능관련
	// -댓글 입력
	public static int insCmnt(CommentsPVO vo) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.insert("hw.insCmnt", vo);
		ss.close();
		return result;
	}
	//--댓글 리스트 가져오기
	public static List<CommentsPVO> listCmnt(Map map) {
		SqlSession ss = DBService.getFactory().openSession();
		List<CommentsPVO> list = ss.selectList("hw.commList", map);
		ss.close();
		return list;
	}
	
	//--하나의 게시물 댓글 리스트 가져오기
	public static List<CommentsPVO> listOneCmnt(int p_idx) {
		SqlSession ss = DBService.getFactory().openSession();
		List<CommentsPVO> list = ss.selectList("hw.commListOne", p_idx);
		ss.close();
		return list;
	}

	public static int delCmnt(int c_idx) {
		SqlSession ss = DBService.getFactory().openSession(true);
		int result = ss.delete("hw.delCmnt", c_idx); 
		ss.close();
		return result;
	}
	
	public static int delOneListCmnt(int p_idx) {
		SqlSession ss = DBService.getFactory().openSession(true);
		System.out.println("delOneListCmnt 진입");
		int result = ss.delete("hw.delOneListCmnt", p_idx); 
		System.out.println("delOneListCmnt 작동완료");
		ss.close();
		return result;
	}

	public static PhotoVO newPhoto(String u_idx) {
		SqlSession ss = DBService.getFactory().openSession();
		PhotoVO pvo = ss.selectOne("hw.newPhoto", u_idx);
		ss.close();
		return pvo;
	}

}
