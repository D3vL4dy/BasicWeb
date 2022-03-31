package kr.or.ddit.lprod.service;

import java.util.List;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.dao.LprodDaoImpl;
import kr.or.ddit.lprod.vo.LprodVO;

//dao에 접근 - dao객체가 필요
//service객체 생성 리턴 - controller가 사용
public class LprodServiceImpl implements ILprodService{
	
	private ILprodDao dao;
	private static ILprodService service;
	
	private LprodServiceImpl() {
		dao = LprodDaoImpl.getDaoInstance();
	}
	
	public static ILprodService getInstance() {
		if(service == null)
			service = new LprodServiceImpl();
		
		return service;
	}
	
	
	@Override
	public List<LprodVO> selectAll() {
		
		return dao.selectAll();
	}
}
