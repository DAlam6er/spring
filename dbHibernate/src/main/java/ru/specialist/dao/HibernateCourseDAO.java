package ru.specialist.dao;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;

@Transactional
@Repository("courseDao")
public class HibernateCourseDAO implements CourseDAO
{
    // журналирование
    private static final Log LOG = LogFactory.getLog(HibernateCourseDAO.class);
    // данная реализация репозитория жёстко привязана к ORM Hibernate
    // из-за SessionFactory

    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // инжектим property sessionFactory, связывая его с одноименным bean,
    // сконфигурированным в XML
    // @Resource - аннотация из javax.annotation

    @Autowired
    //@Value("#{'sessionFactory'}")
    //@Resource(name="sessionFactory") // разобраться почему не работает
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
        // depricated since Hibernate 5
        // устанавливаем соединение с ОБЪЕКТНОЙ БД
        // метод .list() выполняет SQL запрос,
        // на основе полученных данных возвращает сформированную коллекцию данных
        return getSessionFactory().getCurrentSession().
            createQuery("from Course c").list(); // HQL
    }

    @Override
    public List<Course> findByName(String title)
    {
        // depricated since Hibernate 5
        // рассматривать x как переменную в цикле foreach
        // :search - именованный параметр. Имя любое
        //.setParameter("имяПараметра", "значениеПараметра")
        return getSessionFactory().getCurrentSession().
            createQuery("from Course x where x.title LIKE :search")
            .setParameter("search", "%" + title.trim() + "%")
            .list(); // HQL
    }

    @Override
    public void insert(Course course)
    {
        // depricated since Hibernate 6
        // свойство id будет автоматом обновлено за счет
        // наличия аннотации @GeneratedValue
        getSessionFactory().getCurrentSession().save(course);
        // Вывод информации в лог
        LOG.info("Course saved with id: " + course.getId());
    }

    @Override
    public void update(Course course)
    {
        // depricated since Hibernate 6
        getSessionFactory().getCurrentSession().update(course);
        LOG.info("Course updated with id: " + course.getId());
    }

    @Override
    public void delete(int id)
    {
        Course c = new Course(); // фейковый объект курса
        c.setId(id);
        // удаляет через объект, через id нельзя, поэтому был создан c
        // depricated since Hibernate 6
        getSessionFactory().getCurrentSession().delete(c);
        LOG.info("Course deleted with id: " + c.getId());
    }
}
