package ru.specialist.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

@Transactional
@Repository("courseDao")
public class HibernateCourseDAO implements CourseDAO
{
    private static final Log LOG = LogFactory.getLog(HibernateCourseDAO.class);
    // данная реализация репозитория жёстко привязана к ORM Hibernate
    // из-за SessionFactory
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // инжектим property sessionFactory, связывая его с одноименным bean
    @Resource(name="sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory)
    {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public Course findById(int id)
    {
        return (Course)getSessionFactory().getCurrentSession().
            byId(Course.class).load(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll()
    {
        return getSessionFactory().getCurrentSession().
            createQuery("from Course c").list(); // HQL
    }

    @Override
    public List<Course> findByName(String title)
    {
        return getSessionFactory().getCurrentSession().
            createQuery("from Course x where x.title LIKE :search")
            .setParameter("search", "%"+title.trim()+"%")
            .list(); // HQL
    }

    @Override
    public void insert(Course course)
    {
        getSessionFactory().getCurrentSession().save(course);
        LOG.info("Course saved with id: " + course.getId());
    }

    @Override
    public void update(Course course)
    {
        getSessionFactory().getCurrentSession().update(course);
        LOG.info("Course updated with id: " + course.getId());
    }

    @Override
    public void delete(int id)
    {
        Course c = new Course();
        c.setId(id);
        getSessionFactory().getCurrentSession().delete(c);
        LOG.info("Course deleted with id: " + c.getId());
    }

}
