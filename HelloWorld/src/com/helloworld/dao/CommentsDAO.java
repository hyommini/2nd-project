package com.helloworld.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.helloworld.mybatis.DBService;
import com.helloworld.vo.CommentsVO;

public class CommentsDAO {
	//db 댓글 테이블에 해당 내용을 저장하는 메서드
	public static void boardInsert(String b_idx, String content, String nickname) {
		//들어온 값이 셋 중 하나라도 없으면 추가 진행 안함
		if (b_idx.equals(null) || content.equals(null) || nickname.equals(null)) {
			return;
		}
		Map<String,String> map = new HashMap<>();
		map.put("b_idx", b_idx);
		map.put("content", content);
		map.put("nickname", nickname);
		System.out.println("boardInsert 실행~");
		
		SqlSession ss = DBService.getFactory().openSession(true);
		ss.insert("hw.boardCommentInsert", map);
		ss.close();
	}
	
	//해당 게시글 번호에 해당하는 댓글들을 모두 반환하는 메서드
	public static List<CommentsVO> boardCommentsAll(String b_idx) {
		System.out.println("boardList 실행~");
		SqlSession ss = DBService.getFactory().openSession();
		List<CommentsVO> list = ss.selectList("hw.boardCommentsAll", b_idx);
		ss.close();
		System.out.println("boardCommentsAll list : " + list);
		return list;
	}
	
	//해당 번호를 가지는 댓글을 삭제하는 메서드
	public static void boardCommentDelete(String c_idx) {
		SqlSession ss = DBService.getFactory().openSession(true);
		ss.delete("hw.boardCommentDelete", c_idx);
		ss.close();
	}
}
