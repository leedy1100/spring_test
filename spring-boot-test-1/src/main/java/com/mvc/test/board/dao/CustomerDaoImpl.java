package com.mvc.test.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import com.mvc.test.board.dto.CustomerDto;

@Repository
public class CustomerDaoImpl implements CustomerDao {

	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	@Override
	public List<CustomerDto> selectList() {

		return sqlSession.selectList(namespace + "selectList");
	}

	@Override
	public CustomerDto selectOne(String id) {
		
		CustomerDto dto = null;;
		dto = sqlSession.selectOne(namespace + "selectOne", id);
		
		return dto;
	}

	@Override
	public int insert(CustomerDto dto) {

		int res = 0;
		res = sqlSession.insert(namespace + "insert", dto);

		return res;
	}

	@Override
	public int update(CustomerDto dto) {
		return sqlSession.update(namespace + "update", dto);
	}

	@Override
	public int delete(String id) {
		return sqlSession.delete(namespace + "delete", id);
	}

}
