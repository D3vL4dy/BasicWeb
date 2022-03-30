package kr.or.ddit.member.dao;

import java.sql.SQLException;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.ibatis.config.SqlMapClientFactory;
import kr.or.ddit.member.vo.MemberVO;

// mapper접근 - SqlMapClient객체가 필요
// dao클래스객체 생성 - 리턴 - service에서 사용
public class MemberDaoImpl implements IMemberDao{
	
	private SqlMapClient client;
	
	// MemberDaoImpl클래스이름으로 생성해도 되지만 다형성을 위해서 IMemberDao인터페이스로 생성 
	private static IMemberDao dao; 

	private MemberDaoImpl() {
		client = SqlMapClientFactory.getSqlMapClient();
	}
	
	// service에서 사용하기 때문에 public으로 선언
	public static IMemberDao getDaoInstance() {
		// dao가 null일 경우만 생성
		if(dao == null) dao = new MemberDaoImpl();
		
		return dao;
	}
	
	@Override
	public List<MemberVO> selectAll() {
		List<MemberVO> list = null;
		
		try {
			list = client.queryForList("member.selectAll"); // mapper의 "namespace.id"
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return list;
	}
	
}
