package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.buyer.service.BuyerServiceImpl;
import kr.or.ddit.buyer.service.IBuyerService;
import kr.or.ddit.buyer.vo.BuyerVO;
import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.ProdVO;

/**
 * Servlet implementation class ProdServlet
 */
@WebServlet("/ProdServlet.do")
public class ProdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// lprod를 선택시 prod id와 name을 select

		// 1. 전송시 데이터 받기
		String lgu = request.getParameter("lgu"); // "lgu" : html의 data값

		// 2. service객체 얻기
		IProdService service = ProdServiceImpl.getInstance();

		// 3. service메서드 호출하기 -> 결과값 리턴 (value)
		List<ProdVO> list = service.selectByLgu(lgu);
		
		/* JSP 방식
		// 4. 결과를 가지고 출력 또는 응답데이터(text, json, xml) 생성
		// view페이지로 이동
		// view페이지와 결과값을 공유하기 위해서 request에 저장
		request.setAttribute("lguList", list); // jsp로 보냄

		// 5. view페이지로 forward
		request.getRequestDispatcher("0330/prodList.jsp").forward(request, response);
		*/
		
		// GSON 방식
		Gson gson = new Gson();
		// json형태의 문자로 변경
		String result = gson.toJson(list); // 파라미터로 list를 넣어주면 String으로 리턴 
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(result);
		out.flush(); // PrintWriter객체에 있는 내용을 지운다.
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// selectById

		// 1. 전송시 데이터 받기
		String id = request.getParameter("id");

		// 2. service객체 얻기
		IProdService service = ProdServiceImpl.getInstance();

		// 3. service메서드 호출하기 -> 결과값 리턴 (value)
		ProdVO vo = service.selectById(id);

		/* JSP 방식
		// 4. request에 결과값을 저장 ("key", value)
		request.setAttribute("prodId", vo);

		// 5. jsp로 위임(forward)하여 응답데이터 생성 (출력 또는 json데이터)
		request.getRequestDispatcher("0330/prod_detail.jsp").forward(request, response);
		*/
		
		// GSON 방식
		Gson gson = new Gson();
		String result = gson.toJson(vo);
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(result);
		out.flush(); // PrintWriter객체에 있는 내용을 지운다.
		
	}

}
