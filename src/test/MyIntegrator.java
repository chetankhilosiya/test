package test;

import org.hibernate.cfg.Configuration;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.metamodel.source.MetadataImplementor;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

public class MyIntegrator implements Integrator{

	@Override
	public void disintegrate(SessionFactoryImplementor arg0,
			SessionFactoryServiceRegistry arg1) {
		// TODO Auto-generated method stub
		
	}

	//used to register our event listener to hibernate SessionFactory.
	@Override
	public void integrate(Configuration config, SessionFactoryImplementor sessionFactory,
			SessionFactoryServiceRegistry serviceRegistry) {
	
		final EventListenerRegistry eventListenerRegistry=serviceRegistry.getService(EventListenerRegistry.class);
		MyPreUpdate eventListener=new MyPreUpdate();
		eventListenerRegistry.appendListeners(EventType.PRE_UPDATE, eventListener);
		
	}

	@Override
	public void integrate(MetadataImplementor arg0,
			SessionFactoryImplementor arg1, SessionFactoryServiceRegistry arg2) {
		// TODO Auto-generated method stub
		
	}
}
