package my.edu.utar;

public class User 
{
	private String name;
	private String memberType;
	private boolean reward;

	public void setName(String name) 
	{
		if (name== null||name.trim().isEmpty()) 
		{
			throw new IllegalArgumentException("Invalid name value!!!");
		}
		this.name = name;
	}

	public String getName() 
	{
		return name;
	}

	public void setMemberType(String memberType) 
	{
		if(memberType != null && !(memberType.equals("VIP") || memberType.equals("normal") || memberType.equals("non")))
			{
			throw new IllegalArgumentException("Invalid member type: " + memberType);
			}
		this.memberType = memberType;
	}

	public String getMemberType() 
	{
		return memberType;
	}

	public void setReward(boolean reward) 
	{
		this.reward = reward;
	}

	public boolean getReward() 
	{
		return reward;
	}

	public User (String name, String memberType, boolean reward) 
	{
		this.name = name;
		this.memberType = memberType;
		this.reward = false;
	}
}
