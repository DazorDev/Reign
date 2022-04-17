package dazor.test;

import dazor.api.ISystem;
import dazor.api.Reign;

public class TestSystem implements ISystem {

	Reign e;
	
	public TestSystem(Reign sandbox) {
		e = sandbox;
	}
	
	@Override
	public void preUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		e.createEntity(new String("kekw"));
		System.out.println(e.getAllEntities());
	}

	@Override
	public void postUpdate() {
		// TODO Auto-generated method stub
		
	}

	
	
}
