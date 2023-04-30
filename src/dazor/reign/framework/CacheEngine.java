package dazor.reign.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CacheEngine extends Engine {



	HashMap<List<Class<?>>, List<Integer>> cache = new HashMap<>();
	
	@Override
	public void addEntity(Object... components) {
		addToCache(components);
		super.addEntity(components);
	}
	
	private void addToCache(Object... components) {
		for(List<Class<?>> componentNeeded : cache.keySet()) {
			int n =0;
			for(Class<?> componentType : componentNeeded) {
				for(Object c : components ) {
					if(componentType != c.getClass()) continue;
					n++;
					break;
				}
			}
			if(n == componentNeeded.size()) {
				cache.get(componentNeeded).add(entityIndex);
			}
		}
	}
	
	@Override
	public void removeEntity(int id) {
		super.removeEntity(id);
		removeFromCache(id);
	}
	
	private void removeFromCache(int id) {
		for(List<Class<?>> componentTypes : cache.keySet()) {
			List<Integer> ints = cache.get(componentTypes);
			for(int i=0; i<ints.size(); i++) {
				if(ints.get(i) != id) continue;
				ints.remove(i);
			}
		}
	}
	
	@Override
	public List<Integer> getIndexWhere(Class<?>... componentTypes) {
		List<Class<?>> componentTypeList = List.of(componentTypes);
		List<Integer> ints = cache.get(componentTypeList);
		if(ints != null) {
			return ints;
		}
		return searchComponentDatabase(componentTypeList);
	}
	
	private List<Integer> searchComponentDatabase(List<Class<?>> componentTypes) {
		List<Integer> ints = new ArrayList<>();
		for(int i=0; i<entityIndex; i++) {
			int components = 0;
			for(int j=0; j<componentTypes.size(); j++) {
				if(componentDatabase.get(componentTypes.get(j)) == null) continue;
				if(componentDatabase.get(componentTypes.get(j)).get(i) == null) continue;
				components++;
			}
			if(components < componentTypes.size()) continue;
			ints.add(i);
		}
		cache.put(componentTypes, ints);
		return ints;
	}
	
	@Override
	public void clear() {
		super.clear();
		clearCache();
	}
	
	private void clearCache() {
		cache.clear();
	}
	
}
