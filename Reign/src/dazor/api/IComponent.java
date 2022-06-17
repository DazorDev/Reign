package dazor.api;

public interface IComponent {

	public boolean getIsActive();
	
	public void setIsActive(boolean state);
	
	public IComponent copy();
	
}
