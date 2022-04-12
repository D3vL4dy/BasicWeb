package kr.or.ddit.board.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

@WebServlet("/BoardDelete.do")
public class BoardDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		int rqpage = Integer.parseInt(request.getParameter("page"));
		int rqnum = Integer.parseInt(request.getParameter("num"));
		String rqtype = request.getParameter("type");
		String rqword = request.getParameter("word");
		
		IBoardService service = BoardServiceImpl.getInstance();
		
		int num = service.deleteBoard(rqnum);
		
		if(num > 0) {
			// totalPage를 다시 구하기
			Map<String, Object> map = service.getPageInfo(rqpage, rqtype, rqword);
			
			// request에 공유 데이터 저장
			request.setAttribute("service_num", num); // value는 32라인 service에서 꺼내온 값
			request.setAttribute("getPageInfo_map", map.get("totalpage"));
			
			// jsp로 forward - json데이터 생성		
			request.getRequestDispatcher("work/deleteboard.jsp").forward(request, response);
			
		}else {
			
		}
	}

}
