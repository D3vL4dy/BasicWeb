package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.dao.IReplyDao;
import kr.or.ddit.board.dao.ReplyDaoImpl;
import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.board.vo.ReplyVO;

public class ReplyServiceImpl implements IReplyService {
	private IReplyDao dao;
	private static IReplyService service;
	
	//생성자  - dao 객체 얻기 
	public ReplyServiceImpl() {
		dao = ReplyDaoImpl.getInstance();
	}
	
	
	//getInstance()메소드 - service 객체 생성 , 리턴 
	public static IReplyService getInstance() {
		if(service == null) service = new ReplyServiceImpl();
		return service;
	}

	

	@Override
	public int updateReply(ReplyVO vo) {
		int result = 0;
		
		try {
			result = dao.updateReply(vo);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return result;
	}

	@Override
	public int deleteReply(int reply) {
		int result = 0;
		
		try {
			result = dao.deleteReply(reply);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		
		return result;
	}

	@Override
	public List<ReplyVO> replyList(int bnum) {
		List<ReplyVO> list = null;
		
		try {
			list = dao.replyList(bnum);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return list;
	}

	@Override
	public int insertReply(ReplyVO vo) {
		int result = 0;
		
		try {
			result = dao.insertReply(vo);
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return result;
	}

}
