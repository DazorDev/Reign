package dazor.test;

import java.util.ArrayList;

import dazor.api.IComponent;
import dazor.api.IEntity;
import dazor.api.ISystem;
import dazor.api.Reign;
import dazor.framework.classes.Entity;

public class CreatingReignTest {
	
	public static void main(String[] args) {
		Reign sandbox = Reign.create();
		
		IComponent p = new Position(3,5);
		IComponent m = new Motion(3, 0);
		
		String s = new String("Turtle");
		Entity e = new Entity(s,p,m);
		sandbox.createEntity(e);
		Gravity g = new Gravity();
		
		sandbox.addSystem(g);
		
		Reign test = Reign.copy(sandbox);

		for(int i=0; i!= 1; i++) {
			sandbox.start();
		}
		for(int i=0; i!= 2; i++) {
			test.start();
		}
		

	}
	
	static class Position implements IComponent {
		
		public Position(int x, int y) {
			this.xPos = x;
			this.yPos = y;
		}
		
		public void add(int x, int y) {
			this.xPos += x;
			this.yPos += y;
		}
		
		int xPos;
		int yPos;
		
		@Override
		public boolean getIsActive() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setIsActive(boolean state) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public IComponent copy() {
			return new Position(xPos, yPos);
		}
	}
	
	static class Motion implements IComponent {
		
		public Motion(int x, int y) {
			this.xMotion = x;
			this.yMotion = y;
		}
		
		public int getX() {
			return xMotion;
		}
		
		public int getY() {
			return yMotion;
		}
		
		int xMotion;
		int yMotion;
		@Override
		public boolean getIsActive() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public void setIsActive(boolean state) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public IComponent copy() {
			return new Motion(xMotion, yMotion);
		}
	}
	
	static class Gravity implements ISystem {

		Reign engine;
		
		ArrayList<IEntity> entity = new ArrayList<>();
		
		public Gravity() {
			
		}
		
		@Override
		public void setEngine(Reign engine) {
			this.engine = engine;
		}

		@Override
		public void preUpdate() {
			entity.addAll(engine.getEntitiesWith(Position.class, Motion.class));
		}

		@Override
		public void update() {
			for(IEntity e : entity) {
				Position p = ((Position) e.getComponent(Position.class));
				Motion m = ((Motion) e.getComponent(Motion.class));
				p.add(m.getX(),m.getY());
				System.out.println("Entity : "+ e.getName()+ " x = "+ ((Position) e.getComponent(Position.class)).xPos+ "  y = "+ ((Position) e.getComponent(Position.class)).yPos);
			}
		}

		@Override
		public void postUpdate() {
			entity.clear();
		}
		
		@Override
		public ISystem copy() {
			return new Gravity();
		}	
	}
}
