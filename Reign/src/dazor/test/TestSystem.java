package dazor.test;

import dazor.api.ISystem;
import dazor.api.Reign;

public class TestSystem implements ISystem {

	Reign engine;
	
	@Override
	public void preUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		engine.createEntity(new String("kekw"));
	}

	@Override
	public void postUpdate() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEngine(Reign engine) {
		this.engine = engine;
	}

	@Override
	public ISystem copy() {
		return new TestSystem();
	}

	
	
}
