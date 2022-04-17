package dazor.api;

import java.util.ArrayList;

public interface IEntity {

	/**
	 * 
	 * @return 
	 */
	public String getName();
	
	/**
	 * 
	 * @param name
	 */
	public void setName(String name);
	
//	/*
//	 * The first section of the Entity Interface is used to enable normal OOP for an Entitiy.
//	 * This is enabled by the use of a preUpdate, update and postUpdate function.
//	 * The preUpdate and the postUpdate functions are mostly only used to make debugging easier.
//	 * The update function is used to actually do the OOP stuff.
//	 * Yet it is not fully OOP because it still uses the underlying ECS system,
//	 * because it still changes the Information inside the components of the Entity
//	 * and still requires the user to fetch these from the ECS system.
//	 */
//	
//	
//	/**
//	 * 
//	 */
//	public void preUpdate();	
//	
//	/**
//	 * 
//	 */
//	public void update();
//	
//	/**
//	 * 
//	 */
//	public void postUpdate();
	
	
	/**
	 * The second section of the Entity Interface is used to enable the ECS capabilities for the Entity.
	 * This is enabled by the the use of functions like addComponent, removeComponent, getComponent.
	 * These functions allow the The Entity which is created to add and remove components from an internal Tracker.
	 * It also allows the Entity to return a component to another part of the Engine,
	 * for example a Rendering System or for the use of OOP.
	 */
	
	/**
	 * 
	 * @param component
	 */
	public void addComponent(Object component);
	
	/**
	 * 
	 * @param components
	 */
	public void addComponents(Object... components);
	
	/**
	 * 
	 * @param component
	 */
	public void removeComponent(Class<?> component);
	
	/**
	 * 
	 * @param components
	 */
	public void removeComponent(Class<?>... components);
	
	/**
	 * 
	 * @param component
	 */
	public Object getComponent(Class<?> component);
	
	public boolean hasComponent(Class<?> component);
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<Object> getAllComponents();
	
}
