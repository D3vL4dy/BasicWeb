package kr.or.ddit.board.service;

import java.util.List;

import kr.or.ddit.board.vo.ReplyVO;

public interface IReplyService {
	// 댓글수정 - cont, redate
	public int updateReply(ReplyVO vo);
	
	// 댓글삿제 
	public int deleteReply(int reply);
	
	// 댓글리스트
	public List<ReplyVO> replyList(int bonum);
	
	// 댓글저장
	public int insertReply(ReplyVO vo);

}
