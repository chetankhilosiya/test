package test;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class MySession {
	
	private static SessionFactory factory;
	
	public static void main(String args[])	{
		Configuration config=new Configuration().configure();
		MyIntegrator myintegrator=new MyIntegrator();
		BootstrapServiceRegistryBuilder bootstrapBuilder=new BootstrapServiceRegistryBuilder().with(myintegrator);
		StandardServiceRegistryBuilder standardBuilder=new StandardServiceRegistryBuilder(bootstrapBuilder.build());
		ServiceRegistry serviceRegistry=standardBuilder.applySettings(config.getProperties()).build();
		factory=config.buildSessionFactory(serviceRegistry);
		factory.close();
	}
}
