package ru.specialist.house;

import org.springframework.beans.factory.InitializingBean;

// singleton
// thread safe
public class MainWindow 
implements InitializingBean//, DisposableBean
{
	
	private MainWindow() {}

	// вложенный статический класс
	private static class MainWindowHolder  {
		static MainWindow instance = new MainWindow();
	}
	// метода для получения единственного объекта Singleton
	public static MainWindow getInstance() {
		return MainWindowHolder.instance;
	}
	
	public void show() {
		System.out.println("Show main window");
	}
	
	public void openConnection()
	{
		System.out.println("Main window open connection");
	}
	public void closeConnection()
	{
		System.out.println("close connection");
	}

	public void destroy() throws Exception {
		closeConnection();
	}

	public void afterPropertiesSet() throws Exception {
		//openConnection();
		System.out.printf("%s properties set.\n", this.getClass().getName());
	}

}
