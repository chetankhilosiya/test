package test;


import java.util.Arrays;
import java.util.LinkedList;

import org.hibernate.event.spi.PreUpdateEvent;
import org.hibernate.event.spi.PreUpdateEventListener;

public class MyPreUpdate implements PreUpdateEventListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//on class update event we can call notification manager to send notification to every one.
	@Override
	public boolean onPreUpdate(PreUpdateEvent event) {
		System.out.println("In the pre update event listener");
		Object entityObject=event.getEntity();
		if (entityObject.getClass().isAnnotationPresent(Notified.class))	{	//used to match any number of classes which are notified to observer.
			Object id=event.getId();
			Object[] oldState=event.getOldState();
			LinkedList<Object> oldTemp=new LinkedList<Object>(Arrays.asList(oldState));
			oldTemp.addFirst(id);
			oldState=oldTemp.toArray();
			Object[] newState=event.getState();
			LinkedList<Object> newTemp=new LinkedList<Object>(Arrays.asList(newState));
			newTemp.addFirst(id);
			newState=newTemp.toArray();
			NotificationManager manager=NotificationManager.getInstance();
			manager.notifyObservers(event.getEntityName(), oldState, newState);			
		}
		return false;		//return true if update should not perform
	}
}
