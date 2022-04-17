package dazor.framework.classes;

import java.util.ArrayList;

import dazor.api.IEntity;
import dazor.api.References;

public class Entity implements  IEntity {
	
	private ArrayList<Object> components = new ArrayList<Object>();
	
	private String name;
	
	public Entity(Object... components) {
		this(null, null, components);
	}
	
	public Entity(String name, Object... components) {
		this(null, name,  components);
	}
	
	public Entity(IEntity prefabricatedEntity, Object... components) {
		this(prefabricatedEntity, null,  components);
	}
	
	
	public Entity(IEntity prefabricatedEntity, String name, Object... components) {

		if(prefabricatedEntity != null) {
			this.addComponents(prefabricatedEntity.getAllComponents());
			this.name = prefabricatedEntity.getName();
		}
		
		if(name != null) {
			this.name = name;
		}
		
		if(components != null) {
			this.addComponents(components);
		}
		
		if(this.name == null) {
			this.name = References.ENTITY.getName();
		}
		
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
	public void addComponent(Object component) {
		components.add(component);
	}

	@Override
	public void addComponents(Object... components) {
		this.components.add(components);
	}

	@Override
	public void removeComponent(Class<?> component) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeComponent(Class<?>... components) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getComponent(Class<?> component) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Object> getAllComponents() {
		// TODO Auto-generated method stub
		return this.components;
	}

	@Override
	public boolean hasComponent(Class<?> componentType) {
		for(Object component : components) {
			if(component.getClass() == componentType) {
				return true;
			}
		}
		return false;
	}
	
}
