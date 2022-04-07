package ru.specialist.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

// Дженерик типизируется классом на который производится отображение - Course
class CourseRowMapper implements RowMapper<Course>
{
	@Override
	// передаваемый ResultSet уже стоит на нужной строке
	// класс имеет смысл, если имена properties не совпадают
	// с именами столбцов таблицы БД
	public Course mapRow(ResultSet rs, int rowNum) throws SQLException
	{
		Course c = new Course();
		c.setId( rs.getInt("id") );
		c.setTitle( rs.getString("title") );
		c.setLength( rs.getInt("length") );
		c.setDescription( rs.getString("description") );
		return c;
	}
}

// реализация функционала репозитория с использованием JdbcTemplate
public class JdbcCourseDAO implements CourseDAO
{
	// psfs в IDEA
	private static final String SQL_SELECT_COURSE =
			"SELECT id, title, length, description FROM courses";
	
	private static final String SQL_SELECT_COURSE_BY_ID = 
			SQL_SELECT_COURSE + " WHERE id = ?";
	
	private static final String SQL_SELECT_COURSE_BY_TITLE =
			SQL_SELECT_COURSE+" WHERE title LIKE ?";
	
	private static final String SQL_DELETE_COURSE_BY_ID =
			 "DELETE FROM courses WHERE id = ?";
	
	private static final String SQL_INSERT_COURSE =
			 "INSERT INTO courses (title, length, description) VALUES (?, ?, ?)";
	
	private static final String SQL_UPDATE_COURSE =
			 "UPDATE courses SET title = ?, length = ?, description = ? WHERE id = ?";
	
	// spring-jdbc артефакт
	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	// property JdbcTemplate
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Course findById(int id) {
		return getJdbcTemplate().queryForObject(
			SQL_SELECT_COURSE_BY_ID,
			new CourseRowMapper(),
			id);
	}

	@Override
	public List<Course> findAll() {
		// Контейнер Spring уже внедрил зависимости,
		// имеем готовый JdbcTemplate
		// queryForList возвращает коллекцию строк
		// а каждая строка будет представлять собой коллекцию Map
		// Map - ассоциативная коллекция, где ключ - название колонки
		// а значение - объект
		// manual map rows -> objects
		// ручное отображение строк на объекты
		/*
		List<Map<String, Object>> rows =
				getJdbcTemplate().queryForList(SQL_SELECT_COURSE);

		// решение задачи получения списка курсов "в лоб"
		List<Course> courses = new ArrayList<Course>();
		for(Map<String, Object>row : rows) {
			Course c = new Course();
			c.setId( (int)row.get("id") );
			c.setTitle( (String)row.get("title") );
			c.setLength( (int)row.get("length") );
			c.setDescription( (String)row.get("description") );
			courses.add(c);
		}
		*/

		// Воспользовались готовым RowMapper'ом
		// параметр BeanPropertyRowMapper - класс,
		// к которому надо приводить строки
		// properties совпадают с именами столбцов
		//new CourseRowMapper() имел бы смысл, если бы они отличались
		List<Course> courses = getJdbcTemplate().query(
			SQL_SELECT_COURSE,
			//new CourseRowMapper());
			new BeanPropertyRowMapper(Course.class));
		return courses;
	}

	@Override
	public List<Course> findByTitle(String title) {
		return getJdbcTemplate().query(SQL_SELECT_COURSE_BY_TITLE,
			new Object[] { "%"+title+"%"},
			new BeanPropertyRowMapper(Course.class));
	}

	@Override
	public void insert(Course course) {
		PreparedStatementCreatorFactory f =
			new PreparedStatementCreatorFactory(SQL_INSERT_COURSE,
				Types.NVARCHAR, Types.INTEGER, Types.NVARCHAR);
		
		f.setGeneratedKeysColumnNames("id");
		KeyHolder kh = new GeneratedKeyHolder();
		
		getJdbcTemplate().update(
			f.newPreparedStatementCreator(new Object[] {
				course.getTitle(), course.getLength(), course.getDescription()}),
			kh);
		
		course.setId(kh.getKey().intValue());
		
	}

	@Override
	public void update(Course course) {
		getJdbcTemplate().update(SQL_UPDATE_COURSE, 
				course.getTitle(), course.getLength(), 
				course.getDescription(), course.getId());
		
	}

	@Override
	public void delete(int id) {
		getJdbcTemplate().update(SQL_DELETE_COURSE_BY_ID, id);
	}

}
