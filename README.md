# GitHub Login Test Automation

This project automates the login process for GitHub using Selenium WebDriver. It performs three test cases:
1. Login with an incorrect username and correct password.
2. Login with a correct username and incorrect password.
3. Login with correct username and password.

## Prerequisites

- Java JDK (version 8 or higher)
- Maven
- Google Chrome browser
- ChromeDriver (compatible version with your Chrome browser)
- An IDE such as IntelliJ IDEA or Eclipse

## Setup Instructions

### 1. Install Java

Make sure Java JDK is installed on your system. You can download it from the [official Oracle website](https://www.oracle.com/java/technologies/javase-downloads.html).

### 2. Install Maven

Ensure Maven is installed on your system. You can download it from the [official Maven website](https://maven.apache.org/download.cgi).

### 3. Download ChromeBrowser

Download and install the Google Chrome Browser

### 4. Download ChromeDriver

Download the ChromeDriver that matches your Google Chrome version from the [ChromeDriver downloads page](https://sites.google.com/a/chromium.org/chromedriver/downloads).

### Set Up Your Project

### 1. **Clone the Repository**:
   
   
   git clone <repository-url>
   cd <repository-directory>

### 2. Add Dependencies
Add the following dependencies to your pom.xml file:xml
Copy code
````<dependencies>
<dependency>
<groupId>org.seleniumhq.selenium</groupId>
<artifactId>selenium-java</artifactId>
<version>4.0.0</version>
</dependency>
</dependencies>
````
## Run The Test
Run the "github_login" class as a Java application.
