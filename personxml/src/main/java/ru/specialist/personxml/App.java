package ru.specialist.personxml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext(
                    "applicationContext.xml");
       Person person = context.getBean("myself", Person.class);
       // можно не указывать идентификатор, т.к. конфигурация Person одна
       Person person2 = context.getBean(Person.class);

       person.setName("Kostya");

       System.out.println(person);
       System.out.println(person2);
       System.out.println(person.hashCode());
       System.out.println(person2.hashCode());
    }
}
