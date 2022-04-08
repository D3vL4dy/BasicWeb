package kr.or.ddit.board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


@WebServlet("/List.do")
public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ListController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8"); // post일 경우 Encoding
		
		// 1. 요청시 전송 데이터 받기 - page번호, stype, sword
		int rqpage = Integer.parseInt(request.getParameter("page")); // ******* ajax에서 받아오기 *******
		
		// 있거나 없거나 -> xml에서 동적으로 처리
		String rqtype = request.getParameter("stype");
		String rqword = request.getParameter("sword");
		
		// 2. service객체 얻기
		IBoardService service = BoardServiceImpl.getInstance();
		
		// page관련 작업 - 전체 글 수, 총 페이지 수, 
		// 한 페이지 당 출력할 글 갯수, 한 화면에 출력할 페이지 갯수
		Map<String, Object> pmap = service.getPageInfo(rqpage); // service에서 start, end를 구한 map
		
		// parameter Map생성
		Map<String, Integer> map = new HashMap<String, Integer>(); // selectList를 하기 위해 파라미터로 넘겨주는 map
		
		int startVal = (int)pmap.get("start"); // pmap에서 꺼낸 값
		int endVal = (int)pmap.get("end"); // pmap에서 꺼낸 값
		
		map.put("start", startVal);
		map.put("end", endVal);
		
		// 3. service()메서드 호출하기 - 결과값 받기
		List<BoardVO> list = service.selectList(map);
		
		// 4. 결과값으로 응답데이터 생성 - html, text, xml, json데이터로
		Gson gson = new Gson();
		
		String result = gson.toJson(list);
		
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.print(result);
		out.flush();
		
	}

}
