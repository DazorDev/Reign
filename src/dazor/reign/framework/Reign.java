package dazor.reign.framework;

import java.util.List;

public interface Reign {

	public static Reign create() {
		return new Engine();
	}
	
	void addEntity(Object... components);
	
	void removeEntity(int id);
	
	int entityAmount();
	
	void addSystem(Runnable system);
	
	void removeSystem(Runnable system);
	
	int systemAmount();
	
	List<Runnable> getSystems();
	
	<T> List<T> getComponentType(Class<T> componentType);
	
	List<Integer> getIndexWhere(Class<?>... componentTypes);
	
	void tick();
	
	<T> void register(Class<T> componentType);
	
	void clearEntities();
	
	void clearSystems();
	
	void clearTypes();
	
	default void clear() {
		clearTypes();
		clearSystems();
	}
	
}
