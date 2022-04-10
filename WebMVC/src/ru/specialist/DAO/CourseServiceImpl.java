package ru.specialist.DAO;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

//@Repository
@Transactional
@Service("courseService")
public class CourseServiceImpl implements CourseService {
    private static final Log LOG = LogFactory.getLog(CourseServiceImpl.class);

    private CourseRepository courseRepository;

    public CourseRepository getCourseRepository()
    {
        return courseRepository;
    }

    @Autowired
    public void setCourseRepository(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional(readOnly=true)
    @Override
    public List<Course> findAll() {
        return Lists.newArrayList(getCourseRepository().findAll());
    }

    @Transactional(readOnly=true)
    @Override
    public Course findById(int id) {
        //return em.find(Course.class, id);
        return getCourseRepository().findById(id).orElse(new Course());
    }

    @Override
    public Course save(Course course) {
        return getCourseRepository().save(course);
    }

    @Override
    public void delete(int id) {
        getCourseRepository().deleteById(id);
        //em.remove(findById(id));
        LOG.info("Course deleted with id: " + id);
    }
}