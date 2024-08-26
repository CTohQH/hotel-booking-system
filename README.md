# hotel-booking-system_

# Install

1. Go to this website to download Eclipse:
https://www.eclipse.org/downloads/
   - **Using Eclipse will be much easier to perfome testing compare to other compiler.**
   - The link below contains the guideline to complete the downloading process : https://www.eclipse.org/downloads/packages/installer

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
	You can create a GitHub Actions workflow to accomplish this.
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
	5.	Export the java project into an executable JAR file
	6.	List files:
	o	Lists files in the bin directory to verify that the JAR file was created successfully. 

**Notes:**
- Replace URL: Ensure you replace https://example.com/path/to/your/archive.zip with the actual URL of your project archive.
- Update Paths: Adjust /path/to/extracted/project to match the location where you want to extract the project and where the JAR file should be located after the build.
- Workspace Directory: /tmp/workspace is used here as a temporary workspace directory. You can customize this path based on your requirements.

This workflow handles downloading, extracting, importing, and building your Java project using Eclipse in a GitHub Actions pipeline.

# Test Description

JUnit is a widely used testing framework for Java programming. It helps developers to write and run tests for their code, ensuring that individual units of functionality work as intended.

## Installed jar file
1.	Provide the jar file
2.	Install the jar file on your own laptop or PC it will be use after this.

## Set Up The Environment
1.	Eclipse contains the JUnit package to create a JUnit test.

2.	The library can be added during the creation of class using the wizard. If we want to add it later, **right-click** on project name in the packges explorer and select **Build Path -> Add Libraries**.

3.	Select the JUnit from the list. Then, click **[Next]**

4. 	Select JUnit 4 and then **[Finish]**, You should see the JUnit library has been added into the project in the package explorer.

5. 	The **jar file** can be added during the creation of class using the wizard. If we want to add it later, right-click on project name in the package explorer and select confugure build path.

6.	Select Add External JARs at **right hand side.**

7.	Select **All** the jar file on your desktop file, then click **open**.

8.	After that, we can see the jar that we selected was shown in the library. Then click **Apply and Close** at the right bottom.

9.	You also may check the library and jar file that are added on the left-hand side.

10.	Once we set up the testing environment , we are able to conduct unit testing, integration testing and test suite

## Unit Test
Unit testing is a software testing technique where individual components or functions of a program are tested in isolation to ensure they work as expected. These tests are typically automated and focus on verifying the correctness of specific parts of the code, often called "units."

1.	The @RunWith(JUnitParamsRunner.class) annotation is used in JUnit testing to specify a custom runner class that will execute the test class. In this case, the JUnitParamsRunner is a runner provided by the JUnitParams library, which is used to support parameterized tests in JUnit.

2.	To make the method will run in test, there must be an @Test before the method.

3.	To view if the testing method is valid or invalid, we can click the green icon for run. Then it will show the result green color means valid or successful, red color means invalid or unsuccessful.

## Integration Test
1.	The @RunWith(JUnitParamsRunner.class) annotation is used in JUnit testing to specify a custom runner class that will execute the test class. In this case, the JUnitParamsRunner is a runner provided by the JUnitParams library, which is used to support parameterized tests in JUnit.

2.	For run the integration test , we will use the @Before and @After annotations in the test method.


3.	The @Before annotation in JUnit is used to specify a method that should be executed before each test method in the test class. This method is often used to set up the test environment, initialize data, or configure necessary resources to ensure that each test starts from a known and consistent state.

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
-	Use cd to navigate ‘Hotel’ directory
-	cd path\to\Hotel

2.	Create the ‘build.gradle’ file
-	Use text editor like notepad to create the ‘build.gradle’ file
-	Write the below code in the ‘build.gradle’ file
-	After done enter the code, save the file in the ‘Hotel’ directory
3.	Directory structure
-	Ensure the ‘Hotel’’ directory has the following structure
-	Command for use check the project directory (dir build.gradle OR dir)
4.	Run Gradle command
-	gradle build

5.	Run the test
-	gradle test
