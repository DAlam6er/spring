package ru.specialist.builder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
//@PropertySource("classpath:jdbc.properties")
//@PropertySource("classpath:foo.properties")
//@EnableTransactionManagement // transaction support (db)
//@EnableWebMvc // web mvc support
public class BuilderConfig {
	
	// Декларативно указываем где брать url - указываем ключ properties файла
	//@Value("${jdbc.url}")
	//private String url;
	
	// Императивно(в коде) указываем, используя спринговый сервис Environment
	// Spring автоматом заинжектит сюда ссылку на этот сервис
	// Этот сервис предоставляет доступ к окружению
	//@Autowired
	//private Environment env;
	// Читаем значение "jdbc.url"
	//env.getProperty("jdbc.url")

	@Primary
	@Bean("abc")
	@Scope("prototype")
	public Material logs() {
		return new Wood();
	}
	


	@Bean("wall")
	@Scope("prototype")
	public Material bricks() {
		return new Brick();
	}
	

	
	//@Bean("woodFrame")
	@Bean
	@Scope("prototype")
	public Window woodFrameWindow() {
		return new WoodFrameWindow();
	}
	/*@Bean
	public Window plasticWindow() {
		return new PlasticWindow();
	}*/
	
	@Bean()
	//@Lazy
	public House house()
	{
		// это не просто вызов метода  woodFrameWindow()
		// если он помечен аннотацией @Bean
		// это не вызов метода! А запрос к контейнеру:
		// дай мне bean с именем "prototype"
		// оператор вызова метода перестает быть таковым
		// а превращается в запрос к контейнеру на получение bean
		System.out.println(woodFrameWindow());
		System.out.println(woodFrameWindow());

		House house = new House(woodFrameWindow());
		//house.setHeight(2); // 2 этажа
		return house;
	}
}
