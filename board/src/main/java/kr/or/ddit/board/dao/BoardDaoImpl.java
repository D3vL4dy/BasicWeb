package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.ibatis.config.SqlMapClientFactory;

public class BoardDaoImpl implements IBoardDao{
	private SqlMapClient client;
	private static IBoardDao dao;
	
	// 생성자 - client객체 얻어오기
	private BoardDaoImpl() {
		client = SqlMapClientFactory.getSqlMapClient();
	}
	
	// getInstance()메서드 - dao객체 생성 후 리턴
	public static IBoardDao getDaoInstance() {
		if(dao == null) dao = new BoardDaoImpl();
		
		return dao;
	}
	
	@Override
	public List<BoardVO> selectList(Map<String, Object> map) throws SQLException {
//		List<BoardVO> list = null;
//		
//		list = client.queryForList("board.selectList", map);
//		return list;
		
		return client.queryForList("board.selectList", map);
	}

	@Override
	public int totalCount(Map<String, String> map) throws SQLException {
		
		// stype, sword가 들어있는 map을 리턴해 xml실행
		return (int)client.queryForObject("board.totalCount", map); // 결과가 1개면 queryForObject
	}

	@Override
	public int deleteBoard(int num) throws SQLException {
		return (int)client.delete("board.deleteBoard", num);
	}
	
}
