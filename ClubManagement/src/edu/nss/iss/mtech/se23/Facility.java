package edu.nss.iss.mtech.se23;

public class Facility {
	private String name;
	private String description;
	
	public Facility(String name, String description) {
		this.name = name;
		this.description = description;
	}
	
	public Facility(String name) {
		this(name, null);
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}
	
	public String toString() {
		return "Facility Name: " + name + ", Description: " + description; 
	}
	
	public void show() {
		System.out.println(this);
	}
}
