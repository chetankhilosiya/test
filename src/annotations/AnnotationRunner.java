package annotations;



public class AnnotationRunner {
	public void method1()	{
		System.out.println("Method 1");
	}
	
	@CanRun(event="onDelete")
	public void method2()	{
		System.out.println("Method 2");
	}
	
	@CanRun(event="onSave")
	public void method3()	{
		System.out.println("Method 3");
	}
	
	public void method4()	{
		System.out.println("Method 4");
	}
	
	public void method5()	{
		System.out.println("Method 5");
	}
}
