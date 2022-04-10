package ru.specialist.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

// <Course, Integer> - сама сущность JPA и тип первичного ключа
public interface CourseRepository extends CrudRepository<Course, Integer>
{
    List<Course> findAll();
    // https://www.baeldung.com/spring-data-derived-queries
    // можно при условии задания имени метода в соответствии со Spring
    // даже не писать @Query !
    @Query("SELECT c FROM Course c WHERE c.title LIKE :search") // JPQL
    List<Course> findByTitle(@Param("search") String search);

    // @Query подсказывает реализацию методу, описанному интерфейсом
    // @Param - связывает параметр метода с именованным параметром JPQL-запроса
    @Query("SELECT c FROM Course c WHERE c.length <= :mLength") // JPQL
    List<Course> findShortCourses(@Param("mLength") int maxLength);

    // метод, модифицирующий данные
    @Modifying
    @Query("update Course c set c.length = :nLength where c.length = :oLength")
    int incrementLength(@Param("oLength") int oldLength,
                        @Param("nLength") int newLength);
/*	
	// courses.length <= 24
	//@Query("SELECT c FROM Course c WHERE c.length <= 24") // JPQL
//	@Query(value = "SELECT * FROM Course c WHERE c.length <= 24",
//			nativeQuery = true) // SQL
	
	// indexed params (по порядку)
	//@Query("SELECT c FROM Course c WHERE c.length <= ?1") // JPQL
	
	List<Course> findShortCourses(@Param("mLength") int maxLength);
	
	
	*/
}
