package edu.nus.iss.mtech.se23;

import junit.framework.TestCase;

import org.junit.Test;

import edu.nss.iss.mtech.se23.Club;
import edu.nss.iss.mtech.se23.Facility;
import edu.nss.iss.mtech.se23.Member;

public class  ClubTest extends TestCase {
	// fixtures
	Club club;
	
	public void setUp() {
		club = new Club();
	}
	
	@Test
	public void testAddMember() {
		Member m = null;
		int memNo = -1;
		
		m = club.addMember("Smith", "John", "A");
		assertNotNull(m);
		memNo = m.getMemberNumber();
		assertTrue(memNo == 1);
		m = club.addMember("Smith", "John", "A");
		assertNotNull(m);
		memNo = m.getMemberNumber();
		assertTrue(memNo == 2);
		m = club.addMember("Smith", "John", "A");
		assertNotNull(m);
		memNo = m.getMemberNumber();
		assertTrue(memNo == 3);
	}
	
	@Test
	public void testGetMembers() {
		Member[] mems = null;
		Member m = null;
		
		// check get members on empty club
		mems = club.getMembers();
		assertNull(mems);
		
		// check get members on club with one member
		m = club.addMember("Smith", "John", "A");
		mems = club.getMembers();
		assertNotNull(mems);
		assertEquals(mems.length, 1);
		assertSame(m, mems[0]);
		
		// check get members after remove member
		try {
			club.removeMember(m.getMemberNumber());
		}
		catch (Exception ex) {
			ex.printStackTrace();
			fail();
		}
		mems = club.getMembers();
		assertNull(mems);
		
		// check that modifying list does not have effect on original list
		club.addMember("Smith", "John", "A");
		club.addMember("Smith", "Jane", "B");
		club.addMember("Sam", "Uncle", "C");
		mems = club.getMembers();
		assertEquals(mems.length, 3);
		mems[0] = null;
		mems = club.getMembers();
		assertNotNull(mems[0]);
	}
	
	@Test
	public void testShowMembers() {
		
		// test on empty club
		System.out.println("About to show members of empty club: ");
		club.showMembers();
		
		 // test on club after adding one member
		Member m1 = club.addMember("Sam", "Uncle", "C");
		System.out.println("About to show members of club with 1 member: ");
		club.showMembers();
		
		// test on club after removing one member
		System.out.println("About to show members of club with 1 member, after removing one member: ");
		try {
			club.removeMember(m1.getMemberNumber());
			club.showMembers();
		}
		catch(Exception ex) {
			fail();
			ex.printStackTrace();
		}
		
		// test on club after attempting to remove a member that doesn't exist
		System.out.println("About to show members of club after removing one member that doesn't exist: ");
		try {
			club.removeMember(100);
			fail();
		}
		catch (Exception ex) {
			club.showMembers();
		}
	}
	
	@Test
	public void testGetMember() {
		Member m1 = null;
		Member m2 = null;
		
		// test getMember on empty club
		m1 = club.getMember(1);
		assertNull(m1);
		
		// test getMember on club that have members, but not that memberId
		m2 = club.addMember("Smith", "John", "A");
		m1 = club.getMember(2);
		assertNull(m1);
		
		// test getMember on club that has members
		m1 = club.getMember(1);
		assertSame(m1, m2);
		
		// test getMember on a removed member
		m1 = club.addMember("Obama", "Barack", "H");
		int memnum = m1.getMemberNumber();
		try {
			club.removeMember(memnum);
		}
		catch (Exception ex) {
			fail();
		}
		assertNull(club.getMember(memnum));
	}
	
	@Test
	public void testRemoveMember() {
		
		// test remove member on empty club, should throw Exception
		try {
			club.removeMember(1);
			fail();
		}
		catch(Exception ex) {}
		
		// test remove member on club with one valid member, should work, and member should not be retrieved
		Member m1 = club.addMember("Smith", "John", "A");
		int memnum = m1.getMemberNumber();
		try {
			club.removeMember(memnum);
		}
		catch (Exception ex) {
			fail();
		}
		assertNull(club.getMembers());
		assertNull(club.getMember(memnum));
		
		// test remove member on club with multiple valid members, should work, and member should not be retrieved
		m1 = club.addMember("Smith", "John", "A");
		memnum = m1.getMemberNumber();
		club.addMember("Bush", "George", "W");
		try {
			club.removeMember(memnum);
		}
		catch (Exception ex) {
			fail();
		}
		assertNotNull(club.getMembers());
		assertNull(club.getMember(memnum));
	}
	
	public void testGetFacility() {
		
		try {
			// get something on empty club
			Facility fac = club.getFacility("Library");
			assertNull(fac);
			
			// get something that doesn't exist in club
			club.addFacility("Library", "A library");
			fac = club.getFacility("Gym");
			assertNull(fac);
			
			// get something that exists in club
			club.addFacility("Disco", "Dance the night away!");
			fac = club.getFacility("Disco");
			assertEquals(fac.getDescription(), "Dance the night away!");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			fail();
		}
	}
	
	public void testGetFacilities() {
		try {
			// get facilities of empty club
			Facility[] facs = club.getFacilities();
			assertNull(facs);
		
			// get facilities of one facility club
			Facility fac = club.addFacility("Gym", "This is my gym");
			facs = club.getFacilities();
			assertEquals(facs[0], fac);
			
			// get facilities after removing a facility
			club.removeFacility("Gym");
			facs = club.getFacilities();
			assertNull(facs);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			fail();
		}
		
		
	}
	
	public void testAddFacility() {
		// normal add facility
		try {
			Facility f1 = club.addFacility("Library", "A library");
			assertEquals(f1.getName(), "Library");
			assertEquals(f1.getDescription(), "A library");
			club.addFacility("Gym", "A gymnasium");
		}
		catch(Exception ex) {
			ex.printStackTrace();
			fail();
		}
		
		// add duplicate facility
		try {
			club.addFacility("Library", "This is another library");
			fail();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void testRemoveFacility() {
		
		// remove something from empty club
		try {
			club.removeFacility("Library");
			fail();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		// remove something that doesn't exist in club
		try {
			club.addFacility("Tennis Court", "Tennis, anyone?");
			club.removeFacility("Library");
			fail();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		// remove something that exists in club
		try {
			club.addFacility("Swimming Pool", "Water everywhere");
			club.removeFacility("Tennis Court");
		}
		catch(Exception ex) {
			fail();
			ex.printStackTrace();
		}
	}
	
	public void testShowFacilities() {
		// just check no exceptions
		try {
			club.showFacilities();
			club.addFacility("Tennis Court", "A tennis court");
			club.showFacilities();
			club.removeFacility("Tennis Court");
			club.showFacilities();
			club.addFacility("Meeting Room", "For meetings");
			club.addFacility("Sauna", "To relax");
			club.showFacilities();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}