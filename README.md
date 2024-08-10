# HotelBookingSystem

# Install

1. Go to this website to download Eclipse:
https://www.eclipse.org/downloads/
   - **Using Eclipse will be much easier to perfome testing compare to other compiler.**
   - The link below contains the guideline to complete the downloading process : https://www.eclipse.org/downloads/packages/installer

2. Go to  this website to download VMware:
https://www.vmware.com/info/workstation-player/evaluation
   - **VMware is a vitual machine is a virtualization and cloud computing software provider.** 
User can us VMware to gain asses to Windows10 to Windows 11.
   - The link below contains the guideline to install and use VMware:
https://www.youtube.com/watch?v=cygsGM5sNJo

# Prerequisite
**Junit4 and Jar files must be installed and set up properly**
For the following process, please refer to **Set Up The Enviroment**.

# Set Up
1. Download all the files provided onto your desktop.
2. Open your Eclipse.
3. On the top left-hand corner, you can see **File**; click on it.
4. Next, click on **import**.
5. Click **Next** and choose **Browse**. (This will lead you into your directory and select the folder you had downloaded just now.)
6. Click **Finish**.
7. Now you can see the project file on the right-hand side in Eclipse.

**Reminder**
1. If your libary dependency is below JAVA-SE18, you have to change to Java System Libary (JAVA SE-20).

# Deployment
How to deploy the JAVA program after download from GitHub
1.	Download the archive file containing your project code.
2.	Extract the archive to a specified directory.
3.	Import the extracted project into the Eclipse workspace.
4.	Build the project using Eclipse.
Here’s how you can create a GitHub Actions workflow to accomplish this:
![image](https://github.com/user-attachments/assets/5d274ff3-1c37-4d50-85e4-60462b532b37)
Explanation:
	1.	Download Project Archive:
	o	Downloads the archive file from a specified URL. Adjust the URL to where your archive is located.
	2.	Extract Project Archive:
	o	Extracts the contents of the archive to a directory on the GitHub Actions runner. Adjust the extraction path as needed.
	3.	Install Eclipse:
	o	Downloads and installs Eclipse, and creates a symbolic link to the eclipse executable.
	4.	Build with Eclipse:
	o	Import the Project: Imports the extracted project into the Eclipse workspace. Adjust the import path as needed.
	o	Build the Project: Uses Eclipse command-line tools to build the project.
	5.	List files:
	o	Lists files in the bin directory to verify that the JAR file was created successfully. 

**Notes:**
- Replace URL: Ensure you replace https://example.com/path/to/your/archive.zip with the actual URL of your project archive.
- Update Paths: Adjust /path/to/extracted/project to match the location where you want to extract the project and where the JAR file should be located after the build.
- Workspace Directory: /tmp/workspace is used here as a temporary workspace directory. You can customize this path based on your requirements.

This workflow handles downloading, extracting, importing, and building your Java project using Eclipse in a GitHub Actions pipeline.

# Test Description

Junit is a widely used testing framework for Java programming. It helps developers swrite and run tests for their code, ensuring that individual units of functionality work as intended.

## Installed jar file
1.	Provide the jar file
2.	Install the jar file on your own laptop or PC it will be use after this.

## Set Up The Environment
1.	Eclipse contains the JUnit package to create a JUnit test.

2.	The library can be added during the creation of class using the wizard. If we want to add it later, **right-click** on project name in the packges explorer and select **Build Path -> Add Libraries**.

3.	Select the JUnit from the list. Then, click **[Next]**

4. Select JUnit 4 and then **[Finish]**, You should see the JUnit library has been added into the project in the package explorer.

5. The **jar file** can be added during the creation of class using the wizard. If we want to add it later, right-click on project name in the package explorer and select confugure build path.

6.	Select Add External JARs at **right hand side.**

7.	Select **All** the jar file on your desktop file, then click **open**.

8.	After that, we can see the jar that we selected was shown in the library. Then click **Apply and Close** at the right bottom.

9.	You also may check the library and jar file that are added on the left-hand side.

10.	Once we set up the testing environment , we are able to conduct unit testing, integration testing and test suite

## Unit Test
Unit testing is a software testing technique where individual components or functions of a program are tested in isolation to ensure they work as expected. These tests are typically automated and focus on verifying the correctness of specific parts of the code, often called "units."

1.	The @RunWith(JUnitParamsRunner.class) annotation is used in JUnit testing to specify a custom runner class that will execute the test class. In this case, the JUnitParamsRunner is a runner provided by the JUnitParams library, which is used to support parameterized tests in JUnit.

2.	To make the method will run in test, there must be an @Test before the method.

3.	Example code:
```java
//test the setRoomsWishToBook
    @Test
    @Parameters({"guest, 1, 1", "normal, 2, 2", "VIP, 3, 3"})
    public void setRoomWishToBookWithValid(String member_type, int count, int expectedResult) {
        User u = new User("TestUser", member_type); // Create a User object with a name and member type
        u.setRoomsWishToBook(count); 
        assertEquals(expectedResult, u.getRoomsWishToBook()); 
    }

 //test set name
    @Test
    @Parameters({"Jason,Jason","Emily,Emily"})
    public void setNameWithValid(String name,String expectedResult)
    {
    	//User u = new User("TestUser", "member");
    	u.setName(name);
    	String actualResult = u.getName();
    	assertEquals(expectedResult,actualResult);
    }
```
4.	To view if the testing method is valid or invalid, we can click the green icon for run. Then it will show the result green color means valid or successful, red color means invalid or unsuccessful.
![image](https://github.com/user-attachments/assets/a527d434-524a-4ce8-9318-c2650606b907)

![image](https://github.com/user-attachments/assets/4b46a6af-b0c7-4946-82e0-0427e81fe3fd)

## Integration Test
1.	The @RunWith(JUnitParamsRunner.class) annotation is used in JUnit testing to specify a custom runner class that will execute the test class. In this case, the JUnitParamsRunner is a runner provided by the JUnitParams library, which is used to support parameterized tests in JUnit.

2.	For run the integration test , we will use the @Before and @After annotations in the test method.


3.	The @Before annotation in JUnit is used to specify a method that should be executed before each test method in the test class. This method is often used to set up the test environment, initialize data, or configure necessary resources to ensure that each test starts from a known and consistent state.

```java
@Before
	public void setUpTests() {
		//this is to set up the availablerooms.txt file to 5,5,5 Before each test
		try {
	    	PrintWriter pw = new PrintWriter("availablerooms.txt");
	    	pw.println("5,5,5");
	    	pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

4.	The @After annotation in JUnit is used to specify a method that should be executed after each test method in the test class. This method is often used for cleanup activities or to reset the test environment to a consistent state, ensuring that tests do not interfere with each other.


@After
	public void afterTests() {
		try {
			//this is to reset the availablerooms.txt file to 5,5,5 after each test
	    	PrintWriter pw = new PrintWriter("availablerooms.txt");
	    	pw.println("5,5,5");
	    	pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	User vip1 = new User("xy","VIP");
	User vip2 = new User("as","VIP");
	User vip3 = new User("ch","VIP");
	User vip4 = new User("ws","VIP");
	User vip5 = new User("jason","VIP");
	User vip6 = new User("jasmine","VIP");
	User normal1 = new User("nor1", "normal");
	User normal2 = new User("nor2", "normal");
	User normal3 = new User("nor3", "normal");
	User normal4 = new User("nor4", "normal");
	User normal5 = new User("nor5", "normal");
	User normal6 = new User("nor6", "normal");
	User guest1 = new User("guest1", "guest");
	User guest2 = new User("guest2", "guest");
	User guest3 = new User("guest3", "guest");
	User guest4 = new User("guest4", "guest");
	User guest5 = new User("guest5", "guest");
	User guest6 = new User("guest6", "guest");
	

	//this test will mock the ConsoleUtilities class in order to fix the answer to "y" when user is prompted to cancel.
	//booking
	//Asusual, we will assume every user book the maximum allowed number of rooms to truly test the reliability. 
	//potential of the hotel room booking system
	@Test//no1
	@Parameters(method = "getParamsForTestBookingAndCancel")
	public void testBookingAndCancel(User[] users, User[] usersCancelBooking, User[] expectedRemainingUsersInWL, User[] usersBookingPendingStillTrue) {
		ConsoleUtilities cuMock = mock(ConsoleUtilities.class);
		when(cuMock.nextLine()).thenReturn("y");
	 	WaitingList wl = new WaitingList(cuMock);
	 		
	 	for(User user : users)
	 	{
	 		boolean bookingOutcome = user.bookRooms(user.getMaxRooms());
	 		if(!bookingOutcome)
	 		{
	 			wl.addWaiting(user, user.getMaxRooms());
	 		}
	 	}
	 	
	 	ArrayList<User> actualUsersLeftInWL = new ArrayList<User>();
	 	for(User user : usersCancelBooking)
	 	{
	 	/*
	 	* cancelOutcome will check if the user was placed on the waiting list.
	 	* if yes, it will remove the user from the WL and return a true boolean value.
	 	* which will then be tested to see whether the user has been removed from the WL ArrayList.
	 	* if not, it will return a false boolean value, and will proceed to cancel the successfully placed booking.
	 	* which will then be tested whether their previous successfully placed booking pending status is set to false.
	 	*/
	 		boolean cancelBookingFromWL = wl.removeWaiting(user);
	 		if(!cancelBookingFromWL)
	 		{
	 			for(int x = 0; x < user.getBookings().size(); x++) {
	 				user.getBookings().get(x).cancelBooking();
	 				assertFalse(user.getBookings().get(x).isPending());
	 			}
	 		}
	 	}
			if(wl.getVipWaiting().size()>0)
			{
				for(int x = 0; x < wl.getVipWaiting().size(); x++)
					actualUsersLeftInWL.add(wl.getVipWaiting().get(x));
			}
			if(wl.getMemberWaiting().size()>0)
			{
				for(int x = 0; x < wl.getMemberWaiting().size(); x++)
					actualUsersLeftInWL.add(wl.getMemberWaiting().get(x));
			}
			if(wl.getNormalWaiting().size()>0)
			{
				for(int x = 0; x < wl.getNormalWaiting().size(); x++)
					actualUsersLeftInWL.add(wl.getNormalWaiting().get(x));
			}
			User [] actualUsersLeftArray = new User[actualUsersLeftInWL.size()];
			actualUsersLeftArray = actualUsersLeftInWL.toArray(actualUsersLeftArray);
			assertArrayEquals(expectedRemainingUsersInWL, actualUsersLeftArray);
	 	for(int x = 0; x < usersBookingPendingStillTrue.length; x++)
	 	{
	 			assertTrue(usersBookingPendingStillTrue[x].getBookings().get(0).isPending());
	 	}
	}
	
	 private Object[] getParamsForTestBookingAndCancel() {
	    	return new Object[] {
	    			//1st parameter: the users that want to book rooms
	    			//2nd parameter: the selected users who want to cancel booking
	    			//3rd parameter: the remaining users who are still in Waiting List
	    			//4th parameter: the users whose successfully placed a booking, but didn't cancel
	    			new Object[] {
	    					new User[] {vip1, vip2, vip3, vip4, vip5, vip6}, 
	    					new User[] {vip5, vip6}, 
	    					new User[] {},
	    					new User[] {vip1, vip2, vip3, vip4}
	    			},
	    			new Object[] {
	    					new User[] {vip1, vip2, normal1, normal2, normal3, vip6, guest1, normal4, guest4, vip5}, 
	    					new User[] {guest1, vip6, normal4}, 
	    					new User[] {vip5, guest4},
	    					new User[] {vip1, vip2, normal1, normal2, normal3}
	    			}
	    	};
	    }
}
```
## Additional Notes
There are other testing classes and you can explore them! Good Luck!!!

# Build Configuration with Gradle
## Prerequisites
1.	Install Gradle
-	Ensure Gradle is installed on your system. You can download it from https://docs.gradle.org/current/userguide/installation.html#ex-installing-manually

2.	Install java
-	Ensure JDK is installed on your system, version acceptable are JDK 17 and JDK 20. Also if there is latest version of JDK is acceptable.
-	Download link refer https://www.oracle.com/my/java/technologies/downloads/#jdk22-windows

Steps:
1.	Navigate to the project directory
-	Open command prompt
-	Use cd to navigate ‘SoftwareTestingAssignment’ directory
-	cd path\to\SoftwareTestingAssignment

2.	Create the ‘build.gradle’ file
-	Use text editor like notepad to create the ‘build.gradle’ file
-	Write the below code in the ‘build.gradle’ file
-	After done enter the code, save the file in the ‘Assignment’ directory
3.	Directory structure
-	Ensure the ‘SoftwareTestingAssignment’’ directory has the following structure
-	Command for use check the project directory (dir build.gradle OR dir)
4.	Run Gradle command
-	gradle build

5.	Run the test
-	gradle test
