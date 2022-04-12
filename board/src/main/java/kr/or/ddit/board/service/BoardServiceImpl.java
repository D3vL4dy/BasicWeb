package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

public class BoardServiceImpl implements IBoardService{
	// 묶어서 처리 시 try-catch는 service에서 실행

	private IBoardDao dao;
	private static IBoardService service;
	
	// 생성자 - dao객체 얻기
	private BoardServiceImpl(){
		dao = BoardDaoImpl.getDaoInstance();
	}
	// getInstance()메서드 = service객체 생성 후 리턴
	public static IBoardService getInstance() {
		if(service == null)
			service = new BoardServiceImpl();
		
		return service;
	}
	
	@Override
	public List<BoardVO> selectList(Map<String, Object> map) {
		List<BoardVO> list = null;
		
		try {
			list = dao.selectList(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public int totalCount(Map<String, String> map) {
		// controller에서 넘어온 값(stype, sword) 
		int count = 0;
		
		try {
			count = dao.totalCount(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count; // controller로 리턴됨
	}
	
	@Override
	// start, end 페이지를 계산하기 위한 것으로 dao에는 없는 메서드(DB로 가지 않음)
	public Map<String, Object> getPageInfo(int page, String type, String word) { // int page -> controller에서 넘어온 rqpage
		Map<String, Object> map = new HashMap<String, Object>(); // 리턴map
		
		// 한 페이지 당 출력할 글 갯수
		int perlist = 3;
		
		// 한 화면에 출력할 페이지 갯수
		int perpage = 2;
		
		Map<String, String> paramap = new HashMap<String, String>();
		paramap.put("stype", type);
		paramap.put("sword", word);
		
		// 전체 글 갯수 
		int count = this.totalCount(paramap); // dao로 가지 않고 totalCount()로 -> (dao를 직접 호출하지 않음)
		
		// 전체 페이지 수
		int totalPage = (int)Math.ceil((double)count / perlist);
				
		// start, end
		int start = (page - 1) * perlist + 1; // 1이면 1-3, 2이면 4-6...
		int end = start + perlist - 1;
		
		if(end > count) end = count; // end = 21이고 count = 20일 때 끝을 count로 바꿔줌
		
		// startPage, endPage구하기
		// page 1 => 1 2
		// page 2 => 1 2
		// page 3 => 3 4
		// page 4 => 3 4
		// page 7 => 7
		
		int startPage = ((page - 1) / perpage * perpage) + 1;
		int endPage = startPage + perpage - 1;
		
		if(endPage > totalPage) endPage = totalPage;
		
		map.put("start", start);
		map.put("end", end);
		map.put("startpage", startPage);
		map.put("endpage", endPage);
		map.put("totalpage", totalPage);
		
		return map;
	}
	
	@Override
	public int deleteBoard(int num) {
		int res = 0;
		
		try {
			res = dao.deleteBoard(num);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

}
