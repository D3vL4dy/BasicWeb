package kr.or.ddit.board.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardService {
	
	// List 출력
	public List<BoardVO> selectList(Map<String, Object> map);
	
	// 전체 글 갯수 가져오기
	public int totalCount(Map<String, String> map); // stype, sword가 있는 map
	
	// page정보 구하기
	public Map<String, Object> getPageInfo(int page, String type, String word);
	
	// 글 삭제
	public int deleteBoard(int num);
}
