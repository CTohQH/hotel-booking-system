package my.edu.utar;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private User user;
	
	@Before
	public void setUp() 
	{
		user = new User("Tyson", "normal", true);
	}
	
	//Test for GetName
	@Test
	public void testGetName()
	{
		String ER="Tyson";
		String AR=user.getName();
		assertEquals(ER,AR);
		
	}
	
	//Test for SetName by set name justin and compare it using getname
	@Test
	public void testSetName() 
	{
		String ER="Justin";
		user.setName("Justin");
		String AR=user.getName();
		assertEquals(ER,AR);
		
		
	}
	
	//Test getMemberType
	@Test
	public void testGetMemberType()
	{
		String ER="normal";
		String AR=user.getMemberType();
		assertEquals(ER,AR);
	}
	
	//Test setMemberType
	@Test
	public void testSetMemberType() 
	{
		String ER="VIP";
		user.setMemberType("VIP");
		String AR=user.getMemberType();
		assertEquals(ER,AR);
	}
	
	//Test getReward
	@Test 
	public void testGetReward()
	{
		user.setReward(true);
		boolean AR=user.getReward();
		assertTrue(AR);
	}
	
	//Test SetReward
	@Test
	public void testSetReward()
	{
		user.setReward(false);
		boolean AR= user.getReward();
		assertFalse(AR);
	}
	
	//Test NullName
	@Test(expected = IllegalArgumentException.class)
	public void testSetNameforNull() 
	{
		user.setName(null);
	}
	
	//Test empty value for setName
	@Test(expected = IllegalArgumentException.class)
	public void testSetNameforEmptyValue() 
	{
		user.setName("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetMemberType_InvalidInput() {
	    user.setMemberType("Joker");
	}

}