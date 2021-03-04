package com.udacity.jwdnd.course1.cloudstorage;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignupPage {

    @FindBy(id = "login-link")
    private WebElement loginLink;
    @FindBy(id = "submitButton")
    private WebElement submitButton;
    @FindBy(id = "inputUsername")
    private WebElement inputUserName;
    @FindBy(id = "inputPassword")
    private WebElement inputPassword;
    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;
    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    private final JavascriptExecutor js;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }
    public void clickLoginLink(){
        js.executeScript("arguments[0].click();", loginLink);
    }


    public void signUp() {
        js.executeScript("arguments[0].click();", submitButton);
    }

    public void setFirstName(String firstName) {
        js.executeScript("arguments[0].value='"+ firstName +"';", inputFirstName);
    }

    public void setLastName(String lastName) {
        js.executeScript("arguments[0].value='"+ lastName +"';", inputLastName);
    }

    public void setUserName(String userName) {
        js.executeScript("arguments[0].value='"+ userName +"';", inputUserName);
    }

    public void setPassword(String password) {
        js.executeScript("arguments[0].value='"+ password +"';", inputPassword);
    }


}