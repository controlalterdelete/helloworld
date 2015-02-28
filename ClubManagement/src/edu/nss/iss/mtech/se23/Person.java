package edu.nss.iss.mtech.se23;

public class Person {
	private String surname;
	private String firstName;
	private String secondName;
	
	public String getSurname() {
		return surname;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public Person(String sur, String first, String second) {
		firstName = first;
		secondName = second;
		surname = sur;
	}
	
	public String toString() {
		return "Person First Name: " + firstName + ", Second Name: " + secondName + ", Surname: " + surname; 
	}
	
	public void show() {
		System.out.println(this);
	}
}
