package dazor.api;

import dazor.framework.Engine;

/**
 * This enmu holds references to all the Important, Default Settings a Reign posses.
 * This enables moddabilty and more debug potential in the form of overwriting these References to a from the User created Class
 * @author Daniel Banic
 */
public enum References {
	
	/** A refference to the default Engine the Reign is using */
	ENGINE(Engine.class, Engine.Factory.class, "SANDBOX"),
	
	/** A refference to the default options the entity posses */
	ENTITY("STEVE");
	
	/** The class reference for the Engine*/
	public Class<Engine> engineClass;
	
	/** The factory of the Engine */
	public Class<Engine.Factory> factory;
	
	/** The name of the default Engine*/
	public final String name;
	
	/** The Constructor for the Engine reference */
	private References(Class<Engine> engineClass, Class<Engine.Factory> factory, String name) {
		this.engineClass = engineClass;
		this.factory = factory;
		this.name = name;
	}
	
	/** The Constructor for the Engine reference */
	private References(String name) {
		this.name = name;
	}

	/**
	 * Function for Returning the Default Engine
	 * @return
	 */
	public Class<Engine> getEngine() {
		return this.engineClass;
	}
	
	/**
	 * Function for Returning the Default factory 
	 * @return Factory
	 */
	public Class<Engine.Factory> getFactory() {
		return this.factory;
	}
	
	/**
	 * Function for Returning the Defualt name
	 * @return
	 */
	public String getName() {
		return this.name;
	}
}
