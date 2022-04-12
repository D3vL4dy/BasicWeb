package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {

	// List 출력
	public List<BoardVO> selectList(Map<String, Object> map) throws SQLException; // 기능이 분리되어 있을 경우 dao에서 try처리
	
	// 전체 글 갯수 가져오기
	public int totalCount(Map<String, String> map) throws SQLException;
	
	// 글 삭제
	public int deleteBoard(int num) throws SQLException;
}
