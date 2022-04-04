package ru.specialist.springhello;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
		//ApplicationContext context =
    	AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppContext.class);

    	//ApplicationContext ctx = new AnnotationConfigApplicationContext();
    	//ctx.register(AppContext.class)

		PersonBean person = context.getBean(PersonBean.class);
		PersonBean person2 = context.getBean(PersonBean.class);

		System.out.printf("%s - %d\n", person.getName(), person.getAge());
		
		context.close();
}
}
