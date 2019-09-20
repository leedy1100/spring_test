package com.mvc.test.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		return sqlSession.selectOne(namespace + "selectOne", id);
	}
	
	public CustomerDto selectOne2(String id) {
		return sqlSession.selectOne(namespace + "selectOne2", id);
	}

	@Override
	public int insert(CustomerDto dto) {

		int res = 0;
		res = sqlSession.insert(namespace + "insert", dto);
		res += sqlSession.insert(namespace + "insertauthority", dto);

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

	@Override
	public CustomerDto login(String id, String pw) {

		CustomerDto dto = null;

		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pw", pw);

		try {
			dto = sqlSession.selectOne(namespace + "login", map);
		} catch (Exception e) {
			System.out.println("LOGIN ERROR");
			e.printStackTrace();
		}

		return dto;
	}

	public CustomerDto findById(String username) {
		
		return selectOne2(username);
	}

	public CustomerDto findById2(String username) {

		return selectOne(username);
	}

}
