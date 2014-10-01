package test;

public class DemoObserver implements GetNotification{

	@Override
	public void setUpdateNotification(String className, Object[] oldValues,
			Object[] newValues) {
		// TODO Auto-generated method stub
		System.out.println("The notification got in DemoObserver class");
		System.out.println("The object is :"+className);
	}

}
