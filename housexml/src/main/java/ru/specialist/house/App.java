package ru.specialist.house;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
		ClassPathXmlApplicationContext context = 
				new ClassPathXmlApplicationContext(
					"applicationContext.xml");

		// di
		// зависимости внедряет spring
		House house = context.getBean("houseBean", House.class);
		house.buildWall();
		house.view();

		context.getBean("windowBean", WoodFrameWindow.class).open();
		// так тоже сработает, т.к. такой bean всего один
		context.getBean(WoodFrameWindow.class).open();
		// так тоже сработает, "открыть какое-то окно"
		// запрашиваем bean не конкретного типа, а любого из его родителей
		// или интерфейса, который он реализует
		// контейнер сам у себя запросит bean, который реализует интерфейс Window
		context.getBean(Window.class).open();
		context.getBean(Material.class).cover();

		/*
		house.buildWall();
		house.installDoors();
		house.view();
		
		System.out.println(house);
		System.out.println(context.getBean("houseBean"));
		
		MainWindow mainWindow = context.getBean(MainWindow.class);
		
		mainWindow.show();
		
		System.out.println(context.getBean("brickBean"));
		System.out.println(context.getBean("brickBean"));
		*/

		/*
		 * Scope Bean
		 * singleton	В каждом контейнере Spring может быть создан только
						один компонент (по умолчанию) В РАМКАХ КОНТЕЙНЕРА!
			prototype	Позволяет создавать произвольное количество
						компонентов (по одному на каждое обращение)
			request		Область действия компонента ограничивается
						HTTP-запросом. Может применяться только в веб-приложениях Spring (например, использующих Spring MVC)
			session 	Область действия компонента ограничивается
						HTTP-сеансом. Может применяться только в веб-приложениях Spring (например, использующих Spring MVC)
			global-session Область действия компонента ограничивается
						глобальным HTTP-сеансом. Может применяться только
						в портлетах
						
						
			 default-init-method
			 default-destroy-method
			 для всех если есть
			 
			 default-autowire="byType"
			  
		 */

		/*
		 * Внедрение коллекций
		 * 
			Элемент	коллекция	 Назначение
			<list> 				Связывание списка значений, допускаются повторяющиеся
								значения
			<set>				 Связывание множества значений, гарантирует отсутствие
								повторяющихся значений
			<map>				 Связывание коллекций пар имя/значение, где имя и значение
								могут быть значениями любых типов
			<props		Связывание коллекций пар имя/значениее, где имя и значение
								должны имеет строковый тип (String) 		 
		*/
		
		//new Trooper().song();
		
		context.close();
    }
}
