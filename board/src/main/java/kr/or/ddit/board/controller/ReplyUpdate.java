package kr.or.ddit.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.IReplyService;
import kr.or.ddit.board.service.ReplyServiceImpl;
import kr.or.ddit.board.vo.ReplyVO;

@WebServlet("/ReplyUpdate.do")
public class ReplyUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		int rnum = Integer.parseInt(request.getParameter("renum"));
		String cont = request.getParameter("cont");
		
		ReplyVO vo = new ReplyVO();
		vo.setRenum(rnum);
		vo.setCont(cont);
		
		IReplyService service = ReplyServiceImpl.getInstance();
		
		int res = service.updateReply(vo); // vo를 넘겨주기 위해 vo객체를 생성해 넘겨줌
		
		request.setAttribute("resultJSPres", res); // result.jsp의 공유데이터 res 꺼내기
		
		request.getRequestDispatcher("work/result.jsp").forward(request, response);
	}

}
