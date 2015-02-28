package edu.nss.iss.mtech.se23;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class Club {
	private int arraySizeIncrement = 10;
	// cumulative, used for generating member id
	private int numMembers;
	
	
	private List<Member> members;
	
	private Map<String,Facility> facilities = new HashMap<String,Facility>();
	
	public Member getMember(int memberNum) {
		Member m = null;
		if(members != null && !members.isEmpty()) {
			for (Member mem : members) {
				if (mem.getMemberNumber() == memberNum) {
					m = mem;
					break;
				}
			}
		}
		return m;
	}
	
	public Member[] getMembers() {
		if(members == null || members.isEmpty()) {
			return null;
		}
		
		return members.toArray(new Member[members.size()]);
	}
	
	public Member addMember(String surname, String firstName, String secondName) {
		Member newMem = new Member(surname, firstName, secondName, ++numMembers);
		if (members == null) {
			members = new ArrayList<Member>(arraySizeIncrement);
		}
		members.add(newMem);
		return newMem;
	}
	
	public void removeMember(int memberNum) throws Exception {
		if (members == null) {
			throw new Exception("No members, cannot remove");
		}
		boolean found = false;
		Iterator<Member> ite = members.iterator();
		Member mem = null;
		while (ite.hasNext()) {
			mem = ite.next();
			if (mem.getMemberNumber() == memberNum) {
				found = true;
				ite.remove();
				break;
			}
		}
		
		if (!found) {
			throw new Exception("Cannot find member with ID " + memberNum);
		}
	}
	
	public void showMembers() {
		if (members != null && !members.isEmpty()) {
			for (Member mem : members) {
				mem.show();
			}
		}
	}
	
	public Facility getFacility(String name) {
		return facilities.get(name);
	}
	
	public Facility[] getFacilities() {
		if (facilities.isEmpty()) {
			return null;
		}
		
		Collection<Facility> facs = facilities.values();
		Facility[] temp = new Facility[facs.size()];
		int i=0;
		for (Facility f : facs) {
			temp[i++] = f;
		}
		return temp;
	}
	
	public Facility addFacility(String name, String desc) throws Exception {
		if (facilities.containsKey(name)) {
			throw new Exception("Facility " + name + " already exists");
		}
		
		Facility f = new Facility(name, desc);
		facilities.put(name, f);
		return f;
	}
	
	public void removeFacility(String name) throws Exception {
		Facility f = facilities.remove(name);
		if (f == null) {
			throw new Exception("Facility " + name + " does not exist");
		}
	}
	
	public void showFacilities() {
		for (Map.Entry<String, Facility> entry : facilities.entrySet()) {
			System.out.println("Facility Name - " + entry.getKey() + ", Description - " + entry.getValue().getDescription());
		}
	}
}
