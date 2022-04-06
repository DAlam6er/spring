package ru.specialist.springdiAnnotaion;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.specialist.building.House;

@ComponentScan("ru.specialist.building")
public class App 
{
    public static void main( String[] args )
    {
		//ApplicationContext context = 
		//		new ClassPathXmlApplicationContext("applicationContext.xml");
		
    	try(AnnotationConfigApplicationContext context = 
		          new AnnotationConfigApplicationContext(App.class))  {
			//House house = context.getBean("houseBean", House.class);
			House house = context.getBean(House.class);
			
			house.buildWall();
			house.view();
	
			
    	}//context.close();
		
		/*
		 *  @Component – универсальная аннотация, указывающая, что класс
				является компонентом Spring;
			@Controller – указывает, что класс определяет контроллер
				Spring MVC;
			@Repository – указывает, что класс определяет репозиторий дан-
					ных;
			@Service – указывает, что класс определяет службу;
			
			любая пользовательская аннотация, определенная с помощью
					аннотации @Component.
		 * 
		 */
		
		/*
		 * Тип фильтра 	Описание
			annotation 	Отыскивает классы, отмеченные указанной аннотацией
						на уровне типа. Аннотация определяется атрибутом expression
			assignable 	Отыскивает классы, экземпляры которого могут присваиваться
						свойствам указанного типа. Тип свойств определяется
						атрибутом expression
			aspectj 	Отыскивает классы, тип которых соответствует выражению
						типа AspectJ, указанному в атрибуте expression
			custom 		Использует пользовательскую реализацию org.springframework.
						core.type.TypeFilter, указанную в атрибуте expression
			regex 		Отыскивает классы, имена которых соответствуют регулярному
						выражению, указанному в атрибуте expression
		 */
    }
}
