package com.helloworld.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.helloworld.common.PhotoPaging;
import com.helloworld.dao.MemberDAO;
import com.helloworld.dao.PhotoDAO;
import com.helloworld.vo.CommentsPVO;
import com.helloworld.vo.MemberVO;
import com.helloworld.vo.PhotoVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.multipart.Part;

@WebServlet("/photoController")
@MultipartConfig
public class photoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	List<PhotoVO> list = null;
	
	// 리턴 타입에 따른 처리용 변수
	int result = -1;
	
	@SuppressWarnings("null")
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		//--파라미터 값 가져오기
		//-컨트롤러 기능 분류용 파라미터
		String type = request.getParameter("type");
		//-유저 인덱스 파라미터
		String u_idx = request.getParameter("u_idx");
		
		PrintWriter out = response.getWriter();
		List<PhotoVO> list = null;
		PhotoPaging p = null;
		PhotoVO vo = null;
		List<CommentsPVO> cList = new ArrayList<>();
		String strResult = "";
		
		//사진첩 게시물 정보 가져오기
		if ("pListAll".equals(type)) {
			
			p = new PhotoPaging();
			
			//1. 전체 게시물의 수량 구하기	
			p.setTotalRecord(PhotoDAO.getTotalCount(Integer.parseInt(u_idx)));
			p.setTotalPage();
			
			//2. 현재 페이지 구하기
			String cPage = request.getParameter("cPage");
			if (cPage != null) {
				p.setNowPage(Integer.parseInt(cPage));
			}
			
			//3. 현재 페이지에 표시할 게시글 시작번호(begin), 끝번호(end) 구하기
			p.setEnd(p.getNowPage() * p.getNumPerPage()); //현재페이지번호 * 페이지당게시글 수
			p.setBegin(p.getEnd() - p.getNumPerPage() + 1);
			
			//(선택적) 3-1. 끝 번호가 데이터 건수보다 많아지면 데이터 건수와 동일한 번호로 설정
			if (p.getEnd() > p.getTotalRecord()) {
				p.setEnd(p.getTotalRecord());
			}
			
			//---- 블록(block) 계산하기 ----------------
			//4. 블록의 시작페이지, 끝페이지 구하기(현재페이지 번호 사용)
			//시작페이지 구하기
			int nowPage = p.getNowPage();
			int beginPage = (nowPage - 1) / p.getPagePerBlock() * p.getPagePerBlock() + 1;
			p.setBeginPage(beginPage);
			p.setEndPage(p.getBeginPage() + p.getPagePerBlock() - 1);

			//4-1. 끝페이지(endPage)가 전체페이지 수(totalPage) 보다 크면
			// 끝페이지를 전체페이지 수로 변경 처리
			if (p.getEndPage() > p.getTotalPage()) {
				p.setEndPage(p.getTotalPage());
			}
			int u_idxx = Integer.parseInt(request.getParameter("u_idx"));
			System.out.println("u_idx : " + u_idxx);
			list = PhotoDAO.getList(p.getBegin(), p.getEnd(), u_idxx);
			
			//코멘트 리스트 가져오기
			List<Integer> tempList = new ArrayList<>();
			if(list.size() > 0) {
				Map<String, Object> map = new HashMap<String, Object>();
				for(PhotoVO temp : list) {
					tempList.add(temp.getP_idx());
				}
				map.put("list", tempList);
				cList = PhotoDAO.listCmnt(map);
			}
			result = 1;
		} else if ("modifyInfo".equals(type)) { 
			System.out.println("modifyInfo 잘 넘어옴");
			int p_idx = Integer.parseInt(request.getParameter("p_idx"));
			vo = PhotoDAO.pModifyInfo(p_idx);
			
			result = 2;
		} else if ("modify".equals(type)) {
			
		} else if ("delete".equals(type)) {
			List<CommentsPVO> cOneList = null;
			int p_idx = Integer.parseInt(request.getParameter("p_idx"));
			cOneList = PhotoDAO.listOneCmnt(p_idx);
			if(cOneList != null || cOneList.size() != 0) {
				PhotoDAO.delOneListCmnt(p_idx);
			}
			int delResult = PhotoDAO.pDelete(p_idx);
			result = 4;
		//-- 댓글 기능들
		//- 댓글 입력
		} else if ("pCmntInsert".equals(type)) {
				CommentsPVO cVO = new CommentsPVO();
				List<CommentsPVO> cOneList = null;
				cVO.setP_idx(Integer.parseInt(request.getParameter("p_idx")));
				cVO.setNickname(request.getParameter("nickname"));
				cVO.setContent(request.getParameter("comment"));
				int tempResult = PhotoDAO.insCmnt(cVO);
				if (tempResult != 0 ) {
					cOneList = PhotoDAO.listOneCmnt(Integer.parseInt(request.getParameter("p_idx")));
					strResult = changeJson(cOneList);
					result = 3;
				}
		
		//- 댓글 삭제
		} else if ("cmntDelete".equals(type)) {
			System.out.println("잘 넘어옴~ : " + request.getParameter("c_idx")); 
			List<CommentsPVO> cOneList = null;
			int tempResult = PhotoDAO.delCmnt(Integer.parseInt(request.getParameter("c_idx")));
			if (tempResult != 0 ) {
				cOneList = PhotoDAO.listOneCmnt(Integer.parseInt(request.getParameter("p_idx")));
				System.out.println("cOneList : " + cOneList);
				if(cOneList.size() != 0) {
					strResult = changeJson(cOneList);
				}else {
					strResult = null;
				}
				result = 3;
			}
		}
		
		//List 타입 리턴
		if(result == 1) {
			request.setAttribute("photoVO", list);
			request.setAttribute("pvo", p);
			request.setAttribute("flag", "y"); // 분기 처리용
			request.setAttribute("cList", cList); // 코멘트 리스트
			request.getRequestDispatcher("photo.jsp").forward(request, response);
		}else if(result == 2) { // vo 리턴
			request.setAttribute("photoVO", vo);
			request.getRequestDispatcher("photoModify.jsp").forward(request, response);
		}else if(result == 3) { // 스트링 데이터 리턴용
			System.out.println("스트링 데이터 리턴용!!");
			System.out.println(strResult);
			out.print(strResult);
		}else if(result == 4) {
			request.getRequestDispatcher("photo.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
		//Json 형태로 변경하기
	private String changeJson(List<CommentsPVO> list) {
		StringBuilder result = new StringBuilder();
		result.append("{\"list\" : [");
		for (CommentsPVO vo : list) {
			result.append("{");
			result.append("\"p_idx\" : " + vo.getP_idx() + ",");
			result.append("\"c_idx\" : \"" + vo.getC_idx() + "\",");
			result.append("\"nickname\" : \"" + vo.getNickname() + "\",");
			result.append("\"regdate\" : \"" + vo.getRegdate() + "\",");
			result.append("\"content\" : \"" + vo.getContent() + "\"");
			result.append("},");
		}
		result.delete(result.length()-1, result.length());
		result.append("]}");
		
		return result.toString();
	}

}
