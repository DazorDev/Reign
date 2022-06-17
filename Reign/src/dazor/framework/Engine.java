package dazor.framework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import dazor.api.IComponent;
import dazor.api.IEntity;
import dazor.api.ISystem;
import dazor.api.Reign;
import dazor.framework.classes.Entity;

public class Engine implements Reign {
	
	private boolean isUpdating;
	
	private ArrayList<IEntity> entities;
	private ArrayList<ISystem> systems;
	
	private Stack<IEntity> disabledEntities;
	private Stack<IEntity> entityAddStack;
	private Stack<ISystem> systemAddStack;
	private Stack<IComponent> componentAddStack;
	private Stack<IEntity> componentEntityAddStack;
	
	private Stack<IEntity> componentEntityRemoveStack;
	private Stack<Class<ISystem>> systemRemoveStack;
	private Stack<Class<?>> componentRemoveStack;
	
	public Engine() {
		createVariables();
	}

	public Engine(Reign reign) {
		createVariables(); 
		reign.getAllEntities().forEach(entity -> {
			createEntity(entity.copy());
		});
		reign.getAllSystems().forEach( system -> {
			addSystem(system.copy());
		});
	}

	private void createVariables() {
		this.isUpdating = false;
		this.entityAddStack = new Stack<>();
		this.systemAddStack = new Stack<>();
		this.componentAddStack = new Stack<>();
		this.componentEntityAddStack = new Stack<>();
		this.disabledEntities = new Stack<>();
		this.systemRemoveStack = new Stack<>();
		this.componentEntityRemoveStack = new Stack<>();
		this.componentRemoveStack = new Stack<>();
		this.entities = new ArrayList<>();
		this.systems = new ArrayList<>();
	}
	
	@Override
	public void createEntity(IComponent... components) {
		if(!isUpdating) {
			this.entities.add(new Entity(components));
			return;
		}
		this.entityAddStack.push(new Entity(components));
	}

	@Override
	public void createEntity(String name, IComponent... components) {
		if(!isUpdating) {
			this.entities.add(new Entity(name, components));
			return;
		}
		this.entityAddStack.push(new Entity(name, components));
	}

	@Override
	public void createEntity(IEntity preFabricatedEntity) {
		if(!isUpdating) {
			this.entities.add(preFabricatedEntity);
			return;
		}
		this.entityAddStack.push(preFabricatedEntity);
	}

	@Override
	public void createEntity(String name, IEntity preFabricatedEntity) {
		preFabricatedEntity.setName(name);
		if(!isUpdating) {
			this.entities.add(preFabricatedEntity);
			return;
		}
		this.entityAddStack.push(preFabricatedEntity);
	}

	@Override
	public void createEntity(IEntity preFabricatedEntity, IComponent... components) {
		preFabricatedEntity.addComponents(components);
		if(!isUpdating) {
			this.entities.add(preFabricatedEntity);
			return;
		}
		this.entityAddStack.push(preFabricatedEntity);
	}

	@Override
	public void createEntity(String name, IEntity preFabricatedEntity, IComponent... components) {
		preFabricatedEntity.setName(name);
		preFabricatedEntity.addComponents(components);
		if(!isUpdating) {
			this.entities.add(preFabricatedEntity);
			return;
		}
		this.entityAddStack.push(preFabricatedEntity);
	}

	@Override
	public void addComponent(IEntity entity, IComponent component) {
		componentEntityAddStack.push(entity);
		componentAddStack.push(component);
	}

	@Override
	public void addComponent(int entityID, IComponent component) {
		//TODO :(
//		componentEntityAddStack.push(entityID);
//		componentAddStack.push(component);
	}

	@Override
	public void addComponents(IEntity entity, IComponent... components) {
		for(IComponent component : components) {
			componentEntityAddStack.push(entity);
			componentAddStack.push(component);
		}
	}

	@Override
	public void addComponents(int entityID, IComponent... components) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeComponent(IEntity entity, Class<IComponent> component) {
		this.componentEntityRemoveStack.push(entity);
		this.componentRemoveStack.push(component);
	}

	@Override
	public void removeComponents(IEntity entity, Class<?>... components) {
		for(Class<?> component : components) {
			componentEntityRemoveStack.push(entity);
			componentRemoveStack.push(component);
		}
	}

	@Override
	public void removeComponent(int entityID, Class<IComponent> component) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeComponents(int entityID, Class<?>... components) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addSystem(ISystem system) {
		if(!isUpdating) {
			system.setEngine(this);
			this.systems.add(system);
		}
		this.systemAddStack.push(system);
		
	}
	
	@Override
	public void removeSystem(Class<ISystem> system) {
		this.systemRemoveStack.push(system);
	}
	
	@Override
	public void start() {
		this.isUpdating = true;
		run();
	}

	@Override
	public void run() {
		handleEvents();
		if(isUpdating) {
			update();
			updateEntities();
		}
	}

	
	public void update() {
		
		for(ISystem system : systems) {
			system.updateCycle();
		}
	}

	
	public void updateEntities() {
		
//		for(IEntity entity : entities) {
//			
//		}
		
	}

	
	public void handleEvents() {
		
		while(!this.entityAddStack.isEmpty()) {
			this.entities.add(entityAddStack.pop());	
		}
		
		while(!this.componentAddStack.isEmpty()) {
			this.componentEntityAddStack.pop().addComponent(componentAddStack.pop());
		}
		
		while(!this.systemAddStack.isEmpty()) {
			ISystem system = systemAddStack.pop();
			system.setEngine(this);
			this.systems.add(system);
		}
		
		for(IEntity entity : entities) {
			if(!entity.getIsActive()) {
				entity = null;
				disabledEntities.push(entity);
			}
		}
		
	}

	@Override
	public ArrayList<IEntity> getAllEntities() {
		return this.entities;
	}

	@Override
	public ArrayList<IEntity> getEntitiesWith(Class<IComponent> component) {
		ArrayList<IEntity> entitiesWithComponents = new ArrayList<IEntity>();
		for(IEntity entity : entities) {
			if(entity.hasComponent(component)) {
				entitiesWithComponents.add(entity);
			}
		}
		return entitiesWithComponents;
	}


	@Override
	public ArrayList<IEntity> getEntitiesWith(Class<?>... components) {
		ArrayList<IEntity> entitiesWithComponents = new ArrayList<IEntity>();
		int i = 0;
		for(IEntity entity : entities) {	
			for(Class<?> component : components) {
				if(!entity.hasComponent(component)) {
					break;
				}
				i++;
			}
			if(i == components.length) {
				entitiesWithComponents.add(entity);
			}
			i = 0;	
		}
		return entitiesWithComponents;
	}


	@Override
	public IEntity getFirstEntityWith(Class<?>... components) {
		int i = 0;	
		for(IEntity entity : entities) {
			for(Class<?> component : components) {
				if(!entity.hasComponent(component)) {
					break;
				}
				i++;
			}
			if(i == components.length) {
				return entity;		
			}
		}
		return null;
	}
	
	@Override
	public IEntity getFirstEntityWith(Class<IComponent> component) {
		for(IEntity entity : entities) {
			if(entity.hasComponent(component)) {
				return entity;
			}
		}
		return null;
	}

	@Override
	public ISystem getSystem(Class<ISystem> systemType) {
		for(ISystem system : systems) {
			if(system.getClass() == systemType) {
				return system;
			}
		}
		return null;
	}

	@Override
	public ArrayList<ISystem> getAllSystems() {
		return this.systems;
	}

	@Override
	public void addSystems(ISystem... systems) {
		for(ISystem system : systems) {
			this.systemAddStack.add(system);
		}
	}

	@Override
	public void addSystems(Collection<? extends ISystem> systems) {
		this.systemAddStack.addAll(systems);
	}
	
}
