# LingodaTest
This Test is designed to validate the Amazon Search functionality. It validates the user search for a product, Product title, Search 
results count, price and currency symbol of the product. Also it validates the title and price of the product after clicking on the 
first search result.

## Getting Started
Below instructions will help us to get a copy of the project up and running on our local machine for testing purposes.

## Prerequisites
1. Eclispe IDE and JDK 1.8 should be installed.
   - [Eclipse IDE Download] (https://www.eclipse.org/downloads/)
   - [JDK Download] (https://www.oracle.com/technetwork/java/javase/downloads/index.html)
2. Add the JDK path in the system variables and User variables and create JAVA_HOME variable with JDK path in user variables.

## Test Creation and Setup

- Open Eclipe IDE.
- Create a Maven Project. File-> New -> Project -> Maven Project.
- Enter Artifact ID and Group ID and Click on Finish button.
- Open POM.xml and include the required dependencies from Maven Repository. For this current scenario we have added Selenium java, 
  TestNG, ApachePOI and Maven model libraries.
- Right Click on the POM.xml file and select Run As -> Maven Build. This action will add all the required libraries to project.

## Test Framework Artifacts 

- Object Repository
  - Create package and enum file. Place all the object, object properties and methods for object operations in this package.
  
- Page Functions
  - Create package and class file. All the reusable functions should be placed in this package.
  
- Test Scripts
  - Create package and class file. Call the reusable functions created in the Page Functions file and frame a test case. Use TestNG 
  annotations to control the execution flow.
  
- Utility 
  - Create package and class file. All the utility functions for our framework are created here. Eg: Excel Operations

- Drivers
  - Create a Drivers folder and place all the driver executable files in this folder.
  
- Screenshots
  - Create a screenshots folder where all the captured screenshots will be stored during execution.
  
- TestData
  - Create a TestData Folder and place all the Input data sheets here. Here we are using .xslx files.
  
- XML
  - Create a xml file which is used to declare the suite name, Testname and class names which are to be executed.
  
# Running the Tests

  - Steps
    1. Goto XML folder
    2. Right Click on AmazonSearch.xml file -> Run As -> TestNG Suite
  
  - After Test execution completion
    - Validation messages will get displayed in the console tab. 
    - Folder with currect test name and current date appended to it (Eg:AmazonSearch2019-04-17) will get created under screenshots 
      folder. 
    - screenshot files will get created with current system time (hour, minute and second) appended to the file name.
   

   
 



