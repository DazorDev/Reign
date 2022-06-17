package dazor.api;

public interface ISystem {
	
	public void setEngine(Reign engine);
	
	public default void updateCycle() {
		preUpdate();
		update();
		postUpdate();
	}
	
	public void preUpdate();
	
	public void update();
	
	public void postUpdate();
	
	public ISystem copy();
	
}
