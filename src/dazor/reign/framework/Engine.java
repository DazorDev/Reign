package dazor.reign.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Engine implements Reign {

	ArrayList<Runnable> systemList = new ArrayList<>();
	
	HashMap<Class<?>, ArrayList<Object>> componentDatabase = new HashMap<>();
	
	int entityIndex = 0;
	
	@Override
	public void addEntity(Object... components) {
		if(components == null) return;
		entityIndex++;
		for(Object component : components) {
			Class<?> componentClass = component.getClass();
			register(componentClass);
			componentDatabase.get(componentClass).add(component);
		}
		for(List<Object> componentList : componentDatabase.values()) {
			while(componentList.size() < entityIndex) componentList.add(null);
		}
	}

	@Override
	public void removeEntity(int id) {
		if(id < 0 || id >= entityIndex) return;
		for(List<Object> componentList : componentDatabase.values()) {
			componentList.remove(id);
		}
		entityIndex--;
	}

	@Override
	public void addSystem(Runnable system) {
		if(system == null) return;
		systemList.add(system);
	}

	@Override
	public void removeSystem(Runnable system) {
		if(system == null) return;
		systemList.remove(system);
	}

	@Override
	public List<Runnable> getSystems() {
		return systemList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> getComponentType(Class<T> componentType) {
		register(componentType);
		return (ArrayList<T>) componentDatabase.get(componentType);
	}

	@Override
	public void tick() {
		for(int i=0; i<systemList.size(); i++) {
			systemList.get(i).run();
		}
	}

	@Override
	public <T> void register(Class<T> componentType) {
		if(componentDatabase.get(componentType) != null) return;
		componentDatabase.put(componentType, new ArrayList<>());
	}

	@Override
	public void clearEntities() {
		entityIndex = 0;
		for(List<Object> componentList : componentDatabase.values()) {
			componentList.clear();
		}
	}

	@Override
	public void clearSystems() {
		systemList.clear();
	}

	@Override
	public int entityAmount() {
		return entityIndex;
	}

	@Override
	public int systemAmount() {
		return systemList.size();
	}

	@Override
	public List<Integer> getIndexWhere(Class<?>... componentTypes) {
		return searchComponentDatabase(componentTypes);
	}

	private List<Integer> searchComponentDatabase(Class<?>[] componentTypes) {
		List<Integer> ints = new ArrayList<>();
		for(int i=0; i<entityIndex; i++) {
			int components = 0;
			for(int j=0; j<componentTypes.length; j++) {
				if(componentDatabase.get(componentTypes[j]) == null) continue;
				if(componentDatabase.get(componentTypes[j]).get(i) == null) continue;
				components++;
			}
			if(components < componentTypes.length) continue;
			ints.add(i);
		}
		return ints;
	}

	@Override
	public void clearTypes() {
		componentDatabase.clear();
	}
	

}
