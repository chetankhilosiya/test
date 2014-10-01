package test;

import org.hibernate.envers.RevisionListener;
import test.ExampleRevEntity;

public class ExampleListener implements RevisionListener{
	
	public void newRevision(Object revisionEntity)	{
		ExampleRevEntity exampleRevEntity = (ExampleRevEntity) revisionEntity;
		exampleRevEntity.setUsername("Chetan");
	}
}
