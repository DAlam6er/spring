import static java.lang.System.out;

public class HouseBuilder
{
	// шаблон алгоритма, описанный в виде одного метода
	// состоящего из нескольких шагов,
	// каждый из которых - отдельный метод
	public void buildeHouse()
	{
		doBasement();
		doWalls();
		doRoof();
		doWindows();
		doDoors();
		doAdditions();
	} 
	
	protected void doBasement()
	{
		out.println("Построить фундамент");
	}
	protected void doWalls()
	{
		out.println("Возвести стены");
	}
	protected void doRoof()
	{
		out.println("Покрыть крышу");
	}
	protected void doWindows()
	{
		out.println("Вставить окна");
	}
	protected void doDoors()
	{
		out.println("Установить двери");
	}
	protected void doAdditions() {}
}
