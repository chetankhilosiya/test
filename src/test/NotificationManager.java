package test;

import java.util.LinkedList;
import java.util.List;

public class NotificationManager {
	private static final NotificationManager instance=new NotificationManager();
	List<GetNotification> observers;
	
	private NotificationManager()	{
		observers=new LinkedList<GetNotification>();
	}
	
	public static NotificationManager getInstance()	{
		return NotificationManager.instance;
	}
	
	public void notifyObservers(String className,Object[] oldValues,Object[] newValues)	{
		for(GetNotification observer:observers)	{
			observer.setUpdateNotification(className, oldValues, newValues);
		}
	}
	
	public void registerObserver(GetNotification observer)	{
			observers.add(observer);
	}
	
}
