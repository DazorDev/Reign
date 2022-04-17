package dazor.api;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * The Abstraction for using the Engine. The Rain is basically equivalent to a project or a world that posses the sets of rules using the systems.
 * It posses all the important Links a User should have access to.
 * For example it can create the underlying Engine, create Entities, create Entities which are Prefabricated,
 * remove Entities, add Systems, remove Systems, add Components and remove Components.
 * @author Daniel Banic
 *
 */
public interface Reign {
	
	/**
	 * function which creates a Engine which implements the Reign Interface.
	 * the Engine is also forced to implement a Factory which is used to Create the actual Engine
	 * @return 
	 */
	public static Reign create() {
		return createFactory().create();
	}

	/**
	 * function which is creating a new Instance of the Default Factory for creating a Reign
	 * @return a new Factory which can create a new Reign
	 */
	private static Factory createFactory() {
		try {
			return (Factory) Class.forName(References.ENGINE.getFactory().getName()).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * function which will create a new Instance of an Entity which posses components but will have null as name
	 * @param components the components the entitiy posses
	 */
	public void createEntity(Object... components);
	
	/**
	 * function which will create a new Instanceof an Entity which posses components and a name
	 * @param name of the entity
	 * @param components the entity posses
	 */
	public void createEntity(String name, Object... components);

	/**
	 * 
	 * @param preFabricatedEntity
	 */
	public void createEntity(IEntity preFabricatedEntity);
	
	/**
	 * 
	 * @param name
	 * @param preFabricatedEntity
	 */
	public void createEntity(String name, IEntity preFabricatedEntity);
	
	/**
	 * 
	 * @param preFabricatedEntity
	 * @param components
	 */
	public void createEntity(IEntity preFabricatedEntity, Object...components);
	
	/**
	 * 
	 * @param name
	 * @param preFabricatedEntity
	 * @param components
	 */
	public void createEntity(String name, IEntity preFabricatedEntity, Object...components);		
	
	/**
	 * 
	 * @param iEntity
	 * @param component
	 */
	public void addComponent(IEntity iEntity, Object component);
	
	/**
	 * 
	 * @param entityID
	 * @param component
	 */
	public void addComponent(int entityID, Object component);
	
	/**
	 * 
	 * @param iEntity
	 * @param component
	 */
	public void addComponents(IEntity iEntity, Object... components);
	
	/**
	 * 
	 * @param entityID
	 * @param component
	 */
	public void addComponents(int entityID, Object... components);
	
	/**
	 * 
	 * @param iEntity
	 * @param component
	 */
	public void removeComponent(IEntity iEntity, Class<?> component);
	

	/**
	 * 
	 * @param iEntity
	 * @param component
	 */
	public void removeComponents(IEntity iEntity, Class<?>... components);
	
	/**
	 * 
	 * @param entityID
	 * @param component
	 */
	public void removeComponent(int entityID, Class<?> component);
	
	/**
	 * 
	 * @param entityID
	 * @param component
	 */
	public void removeComponents(int entityID, Class<?>... components);
	
	
	/**
	 * 
	 * @param system
	 */
	public void addSystem(ISystem system);
	
	/**
	 * 
	 * @param system
	 */
	public void removeSystem(Class<ISystem> system);
	
	/**
	 * starts the Engine
	 */
	public void start();
	
	/**
	 * function creates the run loop of the Engine.
	 */
	public void run();

	/**
	 * 
	 */
	public ArrayList<IEntity> getAllEntities();
	
	public ArrayList<IEntity> getEntitiesWith(Class<?> component);
	
	public ArrayList<IEntity> getEntitiesWith(Class<?>... components);

	public IEntity getFirstEntityWith(Class<?> components);
	
	public IEntity getFirstEntityWith(Class<?>... components);
	
	
	/**
	 * A Interface for the Factory which is suppose to create the Engine.
	 * @author Daniel Banic
	 */
	interface Factory {
		
		/**
		 * This function is used to create the Reign
		 * @return a new Reign
		 */
		Reign create();
		
		/**
		 * This function is used to copy the Reign
		 * @return a new Reign
		 */
		Reign create(Reign reign);

		
		
	}


	public Reign copy();


	
}
