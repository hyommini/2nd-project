package com.helloworld.dao;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.helloworld.mybatis.DBService;

public class TodayDAO {
	
	//홈페이지 방문시 홈페이지의 u_idx와 당일날짜가 같은 ip가 있는지 중복확인 select
	public static int chIp(Map<String,String> map) {
		SqlSession session = DBService.getFactory().openSession();
		int result = session.selectOne("hw.chIp",map);
		session.close();
		
		return result;
	}
	
	//홈페이지 방문시 방문자의 ip주소 디비에 insert 
	public static int setIp(Map<String,String> map) {
		SqlSession session = DBService.getFactory().openSession(true);
		
		int result = session.insert("hw.setIp",map);
		
		session.close();
		return result;
	}
	 
	//디비에있는 홈페이지와 당일날짜 일치하는 데이터 수 select 
	public static int today(String u_idx) {
		SqlSession session = DBService.getFactory().openSession();
		int today = session.selectOne("hw.today",u_idx);
		
		session.close();
		return today;
	}
	
	//디비에 있는 해당 홈페이지 u_idx의 데이터 수 select 
	public static int total(String u_idx) {
		SqlSession session = DBService.getFactory().openSession();
		int total = session.selectOne("hw.total",u_idx);
		
		session.close();
		return total;
	}
	
}
