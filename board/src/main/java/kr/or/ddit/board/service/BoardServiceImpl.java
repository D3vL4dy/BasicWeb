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
	public List<BoardVO> selectList(Map<String, Integer> map) {
		List<BoardVO> list = null;
		
		try {
			list = dao.selectList(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public int totalCount() {
		// controller에서 넘어온 값이 
		int count = 0;
		
		try {
			count = dao.totalCount();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count; // controller로 리턴됨
	}
	
	@Override
	public Map<String, Object> getPageInfo(int page) { // int page -> controller에서 넘어온 rqpage
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 한 페이지 당 출력할 글 갯수
		int perlist = 3;
		
		// 전체 글 갯수 
		int count = this.totalCount(); // dao로 가지 않고 totalCount()로 -> (dao를 직접 호출하지 않음)
		
		// 전체 페이지 수
		int totalPage = (int)Math.ceil((double)count / perlist);
				
		// start, end
		int start = (page - 1) * perlist + 1; 
		int end = start + perlist - 1;
		
		if(end > count) end = count; // end = 21이고 count = 20일 때 끝을 count로 바꿔줌
		
		map.put("start", start);
		map.put("end", end);
		
		return map;
	}

}
