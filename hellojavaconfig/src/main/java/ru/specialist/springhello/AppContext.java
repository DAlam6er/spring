package ru.specialist.springhello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppContext {
    @Bean("myself")
    @Scope("singleton")
    public PersonBean person() {
        System.out.println("person call.");
        return new PersonBean("Sergey", 41);
    }

    @Bean("allPersons")
    public PersonList persons()
    {
        PersonList list = new PersonList();
        list.add(person());
        list.add(person());
        list.add(person());
        list.add(person());
        // в итоге метод будет вызван один раз, а не 4
        // из-за аннотации @Bean("myself"), наделяющего метод особыми св-вами
        // Spring перехватит вызов и решает, надо ли его делать или нет
        return list;
    }
}