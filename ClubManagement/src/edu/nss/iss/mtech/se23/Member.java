package edu.nss.iss.mtech.se23;

public class Member extends Person {
	private int memberNumber;
	
	public Member(String surname, String firstName, String lastName, int memberNumber) {
		super(surname, firstName, lastName);
		this.memberNumber = memberNumber;
	}

	public int getMemberNumber() {
		return memberNumber;
	}
	
	public String toString() {
		return super.toString() + ", Mem Num: " + memberNumber; 
	}
	
	public void show() {
		System.out.println(this);
	}
}
