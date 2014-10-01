package annotations;

public class AnotRun {
	public void method_A()	{
		System.out.println("Method_A");
	}
	
	@CanRun(event="onFlush")
	public void method_B()	{
		System.out.println("Method_B");
	}
	
	public void method_C()	{
		System.out.println("Method_C");
	}
}
