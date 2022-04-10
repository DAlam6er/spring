package ru.specialist.model;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//   Привет

// hours >= 18
// 0 .. 6  Доброй ночи
// 6 .. 12 Доброе утро
// 12 ..18 Добрый день
// 18..23  Добрый вечер

@Component("userVM")
public class UserVM {
	
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Autowired
	private MessageSource messageSource;
	public MessageSource getMessageSource() {
		return messageSource;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}	
	

	public String getHello() {
		
		//return (getUserName() == null || getUserName().isEmpty()) ? "Привет!" : 
		//	String.format("Привет, %s!", getUserName());
		
		String hello = getMessageSource().getMessage("header_hello", null, Locale.getDefault());
		
		return (getUserName() == null || getUserName().isEmpty() ? hello :
			getMessageSource().getMessage("header_hello_username", 
					new Object[] {getUserName()}, Locale.getDefault()));
		
	}
	

}
