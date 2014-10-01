package annotations;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyTest {
	public static void main(String args[])	{
		
		//AnnotationRunner runner=new AnnotationRunner();
		AnotRun runner=new AnotRun();
		
		Method[] methods=runner.getClass().getMethods();

		for (Method method: methods)	{
			CanRun annos=method.getAnnotation(CanRun.class);
			if (annos!=null)	{
				try {
					System.out.println(annos.event());
					method.invoke(runner, null);
					
				} catch (IllegalAccessException | IllegalArgumentException
						| InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
