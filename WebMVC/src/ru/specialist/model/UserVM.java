package ru.specialist.model;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

// age Привет

// hours >= 18
// 0 .. 6  Доброй ночи
// 6 .. 12 Доброе утро
// 12 ..18 Добрый день
// 18..23  Добрый вечер

@Component("userVM") // базовая аннотация для бина
public class UserVM {

    private String userName;

    // Для локализации
    private MessageSource messageSource;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    // Для локализации
    public MessageSource getMessageSource() {
        return messageSource;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // Свойство Hello - read-only!
    public String getHello() {
        return (getUserName() == null || getUserName().isEmpty()) ?
            "Привет!" :
            String.format("Привет, %s!", getUserName());

        // Локализация
        /*
        String hello = getMessageSource().getMessage("header_hello", null, Locale.getDefault());

        return (getUserName() == null || getUserName().isEmpty() ? hello :
            getMessageSource().getMessage("header_hello_username",
                new Object[] {getUserName()}, Locale.getDefault()));
         */

    }
}
