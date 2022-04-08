package ru.specialist.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import org.springframework.cache.annotation.Cacheable;
//import org.springframework.dao.ConcurrencyFailureException;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


@Transactional( isolation = Isolation.READ_COMMITTED, 
	timeout=20, propagation = Propagation.REQUIRED /*default*/)
@Repository("courseDal")
public class JPACourseDAO implements CourseDAO {
	private static final Log LOG = LogFactory.getLog(JPACourseDAO.class);
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	@Transactional( readOnly = true)
	//@Cacheable("courses", key="#parameter_name")
	@Cacheable("courses")
	public Course findById(int id) {
		/*TypedQuery<Course>  query = em.createQuery(
			"select c from Course c where c.id = :id", Course.class); // JPQL
		query.setParameter("id", id);
		
		return query.getSingleResult();*/
		return em.find(Course.class, id);
	}

	@Override
	@Transactional( readOnly = false)
	public List<Course> findAll() {
		
		return em.createQuery("select c from Course c",  // JPQL
				Course.class).getResultList();
	}

	@Override
	public void insert(Course course) {
		em.persist(course);
		LOG.info("Course saved with id: " + course.getId());
	}

	@Override
	@CachePut(value = "courses", key = "#course.id")
	public void update(Course course) {
		if (course.getId() != 0) {
			Course updatedCourse = em.merge(course);
		}
		//em.persist(course);
		LOG.info("Course updated with id: " + course.getId());
	}
	
	@Override
	@CacheEvict("courses")
	public void delete(int id) {
		em.remove(findById(id));
		LOG.info("Course deleted with id: " + id);
	}

	@Transactional( readOnly = true )
	@Override
	public List<Course> findByTitle(String title) {
		return 
		em.createQuery("select c from Course c where c.title LIKE :title", Course.class)
			.setParameter("title", "%"+title.trim()+"%")
			.getResultList();
	
	}}
