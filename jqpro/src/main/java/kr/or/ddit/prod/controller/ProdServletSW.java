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
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.ProdVO;

/**
 * Servlet implementation class ProdServletSW
 */
@WebServlet("/ProdServletSW.do")
public class ProdServletSW extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProdServletSW() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// lprod를 선택시 prod id와 name을 select
		// lprod에서 장난감, 농산물에 해당하는 prod항목이 없다.

		// 1. 전송시 데이터 받기
		String lgu = request.getParameter("lgu"); // "lgu" : html의 data값

		// 2. service객체 얻기
		IProdService service = ProdServiceImpl.getInstance();

		// 3. service메서드 호출하기 -> 결과값 리턴 (value)
		// 장난감, 농산물에 해당하는 list는 없다.
		List<ProdVO> list = service.selectByLgu(lgu);

		JsonObject obj = new JsonObject();
		if(list != null && list.size() > 0) {
			// 데이터가 있는 경우
			obj.addProperty("sw", "ok"); // key : value
			
			Gson gson = new Gson();
			JsonElement jele = gson.toJsonTree(list);
			
			obj.add("datas", jele); // data삽입
			
		}else {
			obj.addProperty("sw", "no");
		}
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		out.print(obj);
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

				// GSON 방식
				Gson gson = new Gson();
				String result = gson.toJson(vo);
				
				response.setContentType("application/json;charset=utf-8");
				PrintWriter out = response.getWriter();
				
				out.print(result);
				out.flush(); // PrintWriter객체에 있는 내용을 지운다.
				
		
	}

}
