package dazor.framework;

import java.util.ArrayList;
import java.util.Stack;

import dazor.api.IComponent;
import dazor.api.IEntity;
import dazor.api.ISystem;
import dazor.api.Reign;
import dazor.framework.classes.Entity;

public class Engine implements Reign {
	
	private boolean isUpdating, isRunning;
	
	private ArrayList<IEntity> entities;
	private ArrayList<ISystem> systems;
	
	private Stack<IEntity> entityAddStack;
	private Stack<ISystem> systemAddStack;
	private Stack<IComponent> componentAddStack;
	
	
	private Engine() {
		this.isUpdating = this.isRunning = false;
		this.entityAddStack = new Stack<>();
		this.systemAddStack = new Stack<>();
		this.componentAddStack = new Stack<>();
		this.entities = new ArrayList<>();
		this.systems = new ArrayList<>();
	}

	private Engine(Reign reign) {
		this.isUpdating = this.isRunning = false;
		this.entityAddStack = new Stack<>();
		this.systemAddStack = new Stack<>();
		this.componentAddStack = new Stack<>();
		this.entities = new ArrayList<>();
		this.systems = new ArrayList<>();
		this.entities.addAll(reign.getAllEntities());
	}

	@Override
	public void createEntity(Object... components) {
		entityAddStack.add(new Entity(components));
	}

	@Override
	public void createEntity(String name, Object... components) {
		entityAddStack.add(new Entity(name, components));
	}

	@Override
	public void createEntity(IEntity preFabricatedEntity) {
		entityAddStack.add(preFabricatedEntity);
	}

	@Override
	public void createEntity(String name, IEntity preFabricatedEntity) {
		entityAddStack.add(new Entity(name, preFabricatedEntity));
	}

	@Override
	public void createEntity(IEntity preFabricatedEntity, Object... components) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createEntity(String name, IEntity preFabricatedEntity, Object... components) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComponent(IEntity iEntity, Object component) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComponent(int entityID, Object component) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComponents(IEntity iEntity, Object... components) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addComponents(int entityID, Object... components) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeComponent(IEntity iEntity, Class<?> component) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeComponents(IEntity iEntity, Class<?>... components) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeComponent(int entityID, Class<?> component) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeComponents(int entityID, Class<?>... components) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSystem(ISystem system) {
		this.systemAddStack.push(system);
	}
	
	@Override
	public void removeSystem(Class<ISystem> system) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void start() {
		this.isRunning = true;
		this.isUpdating = true;
		run();
	}

	@Override
	public void run() {
		while(isRunning) {
			if(isUpdating) {
				update();
				updateEntities();
			}
			handleEvents();
		}
		dispose();
	}

	
	public void update() {
		
		for(ISystem system : systems) {
			system.update();
		}
		
	}

	
	public void updateEntities() {
		
		
	}

	
	public void handleEvents() {
		
		while(!this.entityAddStack.isEmpty()) {
			this.entities.add(entityAddStack.pop());	
		}
		
		//TODO fix :(
		while(!this.componentAddStack.isEmpty()) {
			componentAddStack.pop();
		}
		
		while(!this.systemAddStack.isEmpty()) {
			this.systems.add(systemAddStack.pop());
		}
		
	}

	public void dispose() {
		this.entities = null;
		System.gc();
	}

	public void save() {
		// TODO Auto-generated method stub
		
	}

	public void load(String fileName) {
		// TODO Auto-generated method stub
		
	}
	
	public void loadScene() {
		// TODO Auto-generated method stub
		
	}

	public void saveScene() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<IEntity> getAllEntities() {
		// TODO Auto-generated method stub
		return this.entities;
	}

	@Override
	public ArrayList<IEntity> getEntitiesWith(Class<?> component) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ArrayList<IEntity> getEntitiesWith(Class<?>... components) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IEntity getFirstEntityWith(Class<?>... components) {
		for(IEntity entity : entities) {
			for(Class<?> component : components) {
				if(!entity.hasComponent(component)) {
					continue;
				}
			}
			return entity;
		}
		return null;
	}
	
	@Override
	public IEntity getFirstEntityWith(Class<?> component) {
		for(IEntity entity : entities) {
			if(entity.hasComponent(component)) {
				return entity;
			}
		}
		return null;
	}

	public static class Factory implements Reign.Factory {
		
		@Override
		public Reign create() {
			return new Engine();
		}

		public Reign create(Reign reign) {
			return new Engine(reign);
		}
		
	}
g
	@Override
	public Reign copy() {
		return new Engine(this);
	}
	
}
