package gov.usgs.cida.pubs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gov.usgs.cida.pubs.dao.intfc.IDeletedPublicationDao;
import gov.usgs.cida.pubs.domain.DeletedPublication;

@Repository
public class DeletedPublicationDao extends SqlSessionDaoSupport implements IDeletedPublicationDao {

	private static final String NS = "deletedPublication";
	public static final String DELETED_SINCE = "deletedSince";

	@Autowired
	public DeletedPublicationDao(SqlSessionFactory sqlSessionFactory) {
		setSqlSessionFactory(sqlSessionFactory);
	}

	@Transactional
	@Override
	public void add(DeletedPublication deletedPublication) {
		getSqlSession().insert(NS + BaseDao.ADD, deletedPublication);
	}

	@Transactional(readOnly = true)
	@Override
	public List<DeletedPublication> getByMap(Map<String, Object> filters) {
		return getSqlSession().selectList(NS + BaseDao.GET_BY_MAP, filters);
	}

	@Transactional(readOnly = true)
	@Override
	public Integer getObjectCount(Map<String, Object> filters) {
		return getSqlSession().selectOne(NS + BaseDao.GET_COUNT, filters);
	}

}
