package ru.specialist.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component("userVM") // базовая аннотация для бина
public class UserVM {

    private String userName;

    private enum TimeOfDay
    {
        NIGHT,
        MORNING,
        DAY,
        EVENING
    }

    // Для локализации
    private MessageSource messageSource;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private String getGreeting()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("H");
        int h = Integer.parseInt(dtf.format(LocalTime.now()));
        TimeOfDay tod;
        switch (h) {
            case 0: case 1: case 2:
            case 3: case 4: case 5:
                tod = TimeOfDay.NIGHT;
                break;
            case 6: case 7: case 8:
            case 9: case 10: case 11:
                tod = TimeOfDay.MORNING;
                break;
            case 18: case 19: case 20:
            case 21: case 22: case 23:
                tod = TimeOfDay.EVENING;
                break;
            default:
                tod = TimeOfDay.DAY;
        }
        return getGreeting(tod);
    }

    private String getGreeting(TimeOfDay tod) {
        String greeting;

        switch (tod) {
            case NIGHT:
                greeting = "header_goodnight";
                break;
            case MORNING:
                greeting = "header_goodmorning";
                break;
            case DAY:
                greeting = "header_goodafternoon";
                break;
            case EVENING:
                greeting = "header_goodevening";
                break;
            default:
                greeting = "header_hello";
        }
        return greeting;
    }

    private String getWelcomeByLocale(String todCode,
                                      @Nullable Object[] args,
                                      Locale locale)
    {
        String localeWelcomeCode = getMessageSource().getMessage(
            todCode, new Object[] {getGreeting()}, locale);

        return getMessageSource().getMessage(
            localeWelcomeCode, args, locale);
    }

    // Свойство hello - read-only!
    public String getHello() {
        // Локализация, default locale указана в bean id="localeResolver"
        // header_hello - ключ. По ключу получаем строку

        Locale.setDefault(new Locale("ru", "RU"));
        String emptyWelcome = getWelcomeByLocale(
            "header_welcome",
            null, Locale.getDefault());

        return (getUserName() == null || getUserName().isEmpty() ?
            String.format("%s!", emptyWelcome) :
            getMessageSource().getMessage(
                "header_welcome_username",
                new Object[] {emptyWelcome, getUserName()},
                Locale.getDefault()
            ));
    }
}
