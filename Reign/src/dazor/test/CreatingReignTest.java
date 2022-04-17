package dazor.test;

import dazor.api.Reign;
import dazor.framework.classes.Entity;

public class CreatingReignTest {
	
	public static void main(String[] args) {
		Reign sandbox = Reign.create();
		
		String s = new String("kekw");
		Entity e = new Entity(s);
		TestSystem test = new TestSystem(sandbox);
		sandbox.createEntity(e);
		sandbox.addSystem(test);
		
		sandbox.start();
		
	}
	
}
