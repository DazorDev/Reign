package dazor.framework.classes;

import java.util.ArrayList;

import dazor.api.IComponent;
import dazor.api.IEntity;
import dazor.api.References;

public class Entity implements IEntity {
	
	private ArrayList<IComponent> components = new ArrayList<IComponent>();
	
	private String name;
	
	private boolean isActive;
	
	public Entity(IComponent... components) {
		this(null, components);
	}
	
	public Entity(String name, IComponent... component) {
		this.name = References.ENTITY.getName();
		if(name != null) {
			this.name = name;
		}
		if(components != null) {
			this.addComponents(component);
		}
		this.isActive = true;
	}
	
	public Entity(String name, ArrayList<IComponent> components) {
		this.name = References.ENTITY.getName();
		if(name != null) {
			this.name = name;
		}
		if(components != null) {
			this.addComponents(components);
		}
		this.isActive = true;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean getIsActive() {
		return this.isActive;
	}

	@Override
	public void setIsActive(boolean status) {
		this.isActive = status;
	}	
	
	@Override
	public void addComponent(IComponent component) {
		components.add(component);
	}

	@Override
	public void addComponents(IComponent... components) {
		for(IComponent component : components) {
			this.components.add(component);
		}
	}

	@Override
	public void addComponents(ArrayList<IComponent> components) {
		this.components.addAll(components);
	}
	
	@Override
	public void removeComponent(Class<?> component) {
		for(IComponent targetComponent : components) {
			if(targetComponent.getClass() == component) {
				this.components.remove(targetComponent);
			}
		}
	}

	@Override
	public void removeComponent(Class<?>... componentTypes) {
		for(Class<?> type : componentTypes ) {
			for(IComponent targetComponent : components) {
				if(targetComponent.getClass() == type) {
					this.components.remove(targetComponent);
				}
			}
		}
	}

	@Override
	public IComponent getComponent(Class<?> component) {
		for(IComponent searchedComponent : components) {
			if(searchedComponent.getClass() == component) {
				return searchedComponent;
			}
		}
		return null;
	}

	@Override
	public ArrayList<IComponent> getAllComponents() {
		return this.components;
	}

	@Override
	public boolean hasComponent(Class<?> componentType) {
		for(IComponent component : components) {
			if(component.getClass() == componentType) {
				return true;
			}
		}
		return false;
	}

	@Override
	public IEntity copy() {
		Entity e = new Entity();
		this.getAllComponents().forEach( component -> {
			e.addComponent(component.copy());
		});
		e.setName(this.getName()+" copy");
		return e;
	}
}
