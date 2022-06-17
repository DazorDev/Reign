package dazor.api;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;

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
		try {
			return (Reign) Class.forName(References.ENGINE.getEngine().getName()).getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * function which creates a Engine which implements the Reign Interface.
	 * the Engine is also forced to implement a Factory which is used to Create the actual Engine
	 * @return 
	 */
	private static Reign create(Reign reign) {
		try {
			return (Reign) Class.forName(References.ENGINE.getEngine().getName()).getConstructor(Reign.class).newInstance(reign);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Creates a new Engine using the Entities, Systems and Componetents which are currently inside the Engine passed in as Parameter.
	 * For each thing inside the Engine the copy function of the thing will be called to create an identical copy of the Object.
	 * @param reign
	 * @return
	 */
	public static Reign copy(Reign reign) {
		return create(reign);
	}
	
	
	/**
	 * function which will create a new Instance of an Entity which posses components but will have null as name
	 * @param components the components the entitiy posses
	 */
	public void createEntity(IComponent... components);
	
	/**
	 * function which will create a new Instanceof an Entity which posses components and a name
	 * @param name of the entity
	 * @param components the entity posses
	 */
	public void createEntity(String name, IComponent... components);

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
	public void createEntity(IEntity preFabricatedEntity, IComponent...components);
	
	/**
	 * 
	 * @param name
	 * @param preFabricatedEntity
	 * @param components
	 */
	public void createEntity(String name, IEntity preFabricatedEntity, IComponent...components);		
	
	/**
	 * 
	 * @param iEntity
	 * @param component
	 */
	public void addComponent(IEntity iEntity, IComponent component);
	
	/**
	 * 
	 * @param entityID
	 * @param component
	 */
	public void addComponent(int entityID, IComponent component);
	
	/**
	 * 
	 * @param iEntity
	 * @param component
	 */
	public void addComponents(IEntity iEntity, IComponent... components);
	
	/**
	 * 
	 * @param entityID
	 * @param component
	 */
	public void addComponents(int entityID, IComponent... components);
	
	/**
	 * 
	 * @param iEntity
	 * @param component
	 */
	public void removeComponent(IEntity iEntity, Class<IComponent> component);
	

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
	public void removeComponent(int entityID, Class<IComponent> component);
	
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
	
	public void addSystems(ISystem... systems);
	
	public void addSystems(Collection<? extends ISystem> systems);
	
	/**
	 * 
	 * @param system
	 */
	public void removeSystem(Class<ISystem> system);
	
	/**
	 * 
	 * @param system
	 * @return
	 */
	public ISystem getSystem(Class<ISystem> system);
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<ISystem> getAllSystems();
	
	
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
	
	/**
	 * 
	 * @param component
	 * @return
	 */
	public ArrayList<IEntity> getEntitiesWith(Class<IComponent> component);
	
	/**
	 * 
	 * @param components
	 * @return
	 */
	public ArrayList<IEntity> getEntitiesWith(Class<?>... components);

	/**
	 * 
	 * @param components
	 * @return
	 */
	public IEntity getFirstEntityWith(Class<IComponent> components);
	
	/**
	 * 
	 * @param components
	 * @return
	 */
	public IEntity getFirstEntityWith(Class<?>... components);
		
}
