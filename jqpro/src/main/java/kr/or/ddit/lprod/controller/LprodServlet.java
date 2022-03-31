package kr.or.ddit.lprod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.lprod.service.LprodServiceImpl;
import kr.or.ddit.lprod.vo.LprodVO;

/**
 * Servlet implementation class LprodServlet
 */
@WebServlet("/LprodServlet.do")
public class LprodServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LprodServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 전송시(요청시) 데이터 받기

		// service객체 얻기
		ILprodService service = LprodServiceImpl.getInstance();
		// service메소드 호출 - 결과를 얻는다.
		List<LprodVO> list = service.selectAll();
		// 결과를 가지고 출력 또는 응답데이터(text, json, xml)를 생성한다.
		// view페이지로 이동
		// view페이지와 결과값을 공유하기 위해서 request에 저장
		request.setAttribute("listvalue", list);

		// view페이지로 foward
		request.getRequestDispatcher("0329/lprodList.jsp").forward(request, response);

	}

}
