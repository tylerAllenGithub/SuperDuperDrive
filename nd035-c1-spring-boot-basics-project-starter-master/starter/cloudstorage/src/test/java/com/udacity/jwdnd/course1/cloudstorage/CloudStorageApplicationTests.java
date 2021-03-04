package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private WebDriver driver;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getSignupPage() {
		driver.get("http://localhost:" + this.port + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
	}
	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}
	@Test
	public void unathourizedAccess(){
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/note");
		Assertions.assertEquals("Login", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/file");
		Assertions.assertEquals("Login", driver.getTitle());
		driver.get("http://localhost:" + this.port + "/credential");
		Assertions.assertEquals("Login", driver.getTitle());
	}
	@Test
	public void signupLogin()
	{
		driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		String username = "user1";
		String password = "password";
		signupPage.setFirstName("Tyler");
		signupPage.setLastName("Allen");
		signupPage.setUserName(username);
		signupPage.setPassword(password);
		signupPage.signUp();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("login-link")));
		signupPage.clickLoginLink();
		String currentUrl = driver.getCurrentUrl();
		Assertions.assertEquals("http://localhost:"+this.port+"/login", currentUrl);
		LoginPage loginPage = new LoginPage((driver));
		loginPage.setUserName(username);
		loginPage.setPassword(password);
		loginPage.clickSubmitButton();
		marker = wait.until(webDriver -> webDriver.findElement(By.id("homeTitle")));
		currentUrl = driver.getCurrentUrl();
		Assertions.assertEquals("http://localhost:"+this.port+"/home", currentUrl);
	}
	@Test
	public void signupLoginLogout(){
		/*driver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(driver);
		String username = "user1";
		String password = "password";
		signupPage.setFirstName("Tyler");
		signupPage.setLastName("Allen");
		signupPage.setUserName(username);
		signupPage.setPassword(password);
		signupPage.signUp();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("login-link")));
		signupPage.clickLoginLink();
		String currentUrl = driver.getCurrentUrl();
		Assertions.assertEquals("http://localhost:"+this.port+"/login", currentUrl);
		LoginPage loginPage = new LoginPage((driver));
		loginPage.setUserName(username);
		loginPage.setPassword(password);
		loginPage.clickSubmitButton();
		marker = wait.until(webDriver -> webDriver.findElement(By.id("homeTitle")));
		currentUrl = driver.getCurrentUrl();
		Assertions.assertEquals("http://localhost:"+this.port+"/home", currentUrl);
		*/
		signupLogin();
		HomePage homePage = new HomePage(driver);
		homePage.logout();
		String currentUrl = driver.getCurrentUrl();
		Assertions.assertEquals("http://localhost:"+this.port+"/login?logout", currentUrl);
	}
	@Test
	public void createNote()
	{
		signupLogin();
		HomePage homePage = new HomePage(driver);
		homePage.navigateToNotes();
		String currentUrl = driver.getCurrentUrl();
		homePage.addNewNote();
		String noteTitle = "Best Title";
		homePage.setNoteTitle(noteTitle);
		String noteDescription = "Best Description";
		homePage.setNoteDescription(noteDescription);
		//Integer noteID = homePage.getNoteID();
		homePage.clickNoteSubmitButton();
		currentUrl = driver.getCurrentUrl();
		Assertions.assertEquals("http://localhost:"+this.port+"/note/add", currentUrl);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("resultTitle")));
		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickSuccessLink();
		currentUrl = driver.getCurrentUrl();
		Assertions.assertEquals("http://localhost:"+this.port+"/home", currentUrl);
		homePage.navigateToNotes();
		//Assertions.assertEquals(noteID,homePage.getNoteID());
		Assertions.assertEquals(noteDescription,homePage.getNoteDescriptionList());
		Assertions.assertEquals(noteTitle,homePage.getNoteTitleList());
	}
	@Test
	public void editNote(){
		createNote();
		HomePage homePage = new HomePage(driver);
		homePage.clickNoteEditButton();
		String noteTitle = "Worst Title";
		homePage.setNoteTitle(noteTitle);
		String noteDescription = "Worst Description";
		homePage.setNoteDescription(noteDescription);
		homePage.clickNoteSubmitButton();
		String currentUrl = driver.getCurrentUrl();
		Assertions.assertEquals("http://localhost:"+this.port+"/note/add", currentUrl);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("resultTitle")));
		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickSuccessLink();
		currentUrl = driver.getCurrentUrl();
		Assertions.assertEquals("http://localhost:"+this.port+"/home", currentUrl);
		homePage.navigateToNotes();
		//Assertions.assertEquals(noteID,homePage.getNoteID());
		Assertions.assertEquals(noteDescription,homePage.getNoteDescriptionList());
		Assertions.assertEquals(noteTitle,homePage.getNoteTitleList());
	}
	@Test
	public void deleteNote(){
		createNote();//Best Title, Best Description
		HomePage homePage = new HomePage(driver);
		homePage.clickNoteDeleteButton();
		String currentUrl = driver.getCurrentUrl();
		Assertions.assertEquals("http://localhost:"+this.port+"/note/delete/1", currentUrl);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("resultTitle")));
		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickSuccessLink();
		currentUrl = driver.getCurrentUrl();
		Assertions.assertEquals("http://localhost:"+this.port+"/home", currentUrl);
		homePage.navigateToNotes();
		Boolean isDescriptionPresent = driver.findElements(new By.ById("noteDescriptionList")).size() > 0;
		Boolean isTitlePresent = driver.findElements(new By.ById("noteTitleList")).size() > 0;
		Assertions.assertFalse(isDescriptionPresent);
		Assertions.assertFalse(isTitlePresent);
	}
	@Test
	public void createCredential()
	{
		String url = "defaultUrl.com";
		String username="defaultUsername";
		String password = "password";
		createCredentialParam(url, username, password);
	}
	public void createCredentialParam(String url, String username, String password){
		signupLogin();
		HomePage homePage = new HomePage(driver);
		homePage.navigateToCredentials();
		homePage.clickaddNewCredentialButton();

		homePage.enterCredentials(url, username, password);
		homePage.clickCredentialSubmitButton();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("resultTitle")));
		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickSuccessLink();
		String currentUrl = driver.getCurrentUrl();
		Assertions.assertEquals("http://localhost:"+this.port+"/home", currentUrl);
		homePage.navigateToCredentials();
		Assertions.assertEquals(url, homePage.getCredentialUrlList());
		Assertions.assertEquals(username, homePage.getCredentialUsernameList());
		Assertions.assertNotEquals(password, homePage.getCredentialPasswordList());//Listed Password should be encrypted
	}

	@Test
	public void editCredential() {
		String url = "defaultUrl.com";
		String username="defaultUsername";
		String password = "password";
		createCredentialParam(url, username, password);
		HomePage homePage = new HomePage(driver);
		homePage.clickCredentialEditButton();
		Assertions.assertEquals(url, homePage.getCredentialUrl());
		Assertions.assertEquals(username, homePage.getCredentialUsername());
		Assertions.assertEquals(password, homePage.getCredentialPassword());
		url = "newUrl.com";
		username="newUsername";
		password = "newPassword";
		homePage.enterCredentials(url, username, password);
		homePage.clickCredentialSubmitButton();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("resultTitle")));
		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickSuccessLink();
		String currentUrl = driver.getCurrentUrl();
		Assertions.assertEquals("http://localhost:"+this.port+"/home", currentUrl);
		homePage.navigateToCredentials();
		homePage.clickCredentialEditButton();
		Assertions.assertEquals(url, homePage.getCredentialUrl());
		Assertions.assertEquals(username, homePage.getCredentialUsername());
		Assertions.assertEquals(password, homePage.getCredentialPassword());
	}
	@Test
	public void deleteCredential() {
		String url = "defaultUrl.com";
		String username="defaultUsername";
		String password = "password";
		createCredentialParam(url, username, password);
		HomePage homePage = new HomePage(driver);
		homePage.clickCredentialDeleteButton();
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement marker = wait.until(webDriver -> webDriver.findElement(By.id("resultTitle")));
		ResultPage resultPage = new ResultPage(driver);
		resultPage.clickSuccessLink();
		String currentUrl = driver.getCurrentUrl();
		Assertions.assertEquals("http://localhost:"+this.port+"/home", currentUrl);
		homePage.navigateToCredentials();
		Assertions.assertEquals("", homePage.getCredentialUrl());
		Assertions.assertEquals("", homePage.getCredentialUsername());
		Assertions.assertEquals("", homePage.getCredentialPassword());
	}


}
