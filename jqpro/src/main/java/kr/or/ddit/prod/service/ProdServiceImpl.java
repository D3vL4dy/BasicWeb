package kr.or.ddit.prod.service;

import java.util.List;

import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.prod.vo.ProdVO;

public class ProdServiceImpl implements IProdService{
	
	private IProdDao dao;
	public static IProdService service;
	
	private ProdServiceImpl() {
		dao = ProdDaoImpl.getDaoInstance();
	}
	
	public static IProdService getInstance() {
		if(service == null)
			service = new ProdServiceImpl();
		
		return service;
	}

	@Override
	public List<ProdVO> selectByLgu(String prodLgu) {
		return dao.selectByLgu(prodLgu);
	}

	@Override
	public ProdVO selectById(String id) {
		return dao.selectById(id);
	}

}
