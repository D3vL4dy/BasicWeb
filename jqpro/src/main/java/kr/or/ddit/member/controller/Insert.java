package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

/**
 * Servlet implementation class Insert
 */
@WebServlet("/Insert.do")
public class Insert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insert() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8"); // post일 경우만
		
		// 1. 전송 데이터 받기
		MemberVO vo = new MemberVO();
		
		//vo.setMem_id(request.getParameter("mem_id")); // 라이브러리를 사용하지 않으면 모두 적어야 돼
		// 위의 모든 데이터를 Map으로 가져옴
		try {
			BeanUtils.populate(vo, request.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// 2. service객체 얻기
		IMemberService service = MemberServiceImpl.getInstance();
		
		// 3. service메서드 호출
		String res = service.insertMember(vo); // 성공여부
		
		// 4. 결과값을 request에 저장
		request.setAttribute("keyres", res);
		//request.setAttribute("formId", vo.getMem_id()); // form에서 입력한 아이디를 vo에서 꺼내 
		
		// 5. jsp로 forward
		request.getRequestDispatcher("member/result.jsp").forward(request, response);
	}

}
