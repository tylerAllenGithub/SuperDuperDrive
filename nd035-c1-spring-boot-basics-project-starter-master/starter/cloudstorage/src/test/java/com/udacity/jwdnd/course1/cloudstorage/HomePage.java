package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
    //Logout Element
    @FindBy(id = "logoutButton")
    private WebElement logout;
    //Note Elements
    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;
    @FindBy(id="addNewNoteButton")
    private WebElement addNewNoteButton;
    @FindBy(id="note-title")
    private WebElement noteTitle;
    @FindBy(id="note-description")
    private WebElement noteDescription;
    @FindBy(id="noteTitleList")
    private WebElement noteTitleList;
    @FindBy(id="noteDescriptionList")
    private WebElement noteDescriptionList;
    @FindBy(id="note-id")
    private WebElement noteID;
    @FindBy(id="noteSubmit")
    private WebElement noteSubmitButton;
    @FindBy(id="noteEdit")
    private WebElement noteEditButton;
    @FindBy(id="noteDelete")
    private WebElement noteDeleteButton;
    //Credential elements
    @FindBy(id="nav-credentials-tab")
    private WebElement credentialsTab;
    @FindBy(id="addNewCredentialButton")
    private WebElement addNewCredentialButton;
    @FindBy(id="credentialSubmit")
    private WebElement credentialSubmit;
    @FindBy(id="credential-url")
    private WebElement credentialUrl;
    @FindBy(id="credential-username")
    private WebElement credentialUsername;
    @FindBy(id="credential-password")
    private WebElement credentialPassword;
    @FindBy(id="urlList")
    private WebElement urlList;
    @FindBy(id="usernameList")
    private WebElement usernameList;
    @FindBy(id="passwordList")
    private WebElement passwordList;
    @FindBy(id="credEditButton")
    private WebElement credentialEditButton;
    @FindBy(id="credDeleteButton")
    private WebElement credentialDeleteButton;

    private final JavascriptExecutor js;
    private WebDriverWait wait;
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, 1000);
    }

    public void logout(){
        js.executeScript("arguments[0].click();", logout);
    }
    public void navigateToNotes(){
        js.executeScript("arguments[0].click();", noteTab);
    }
    public void addNewNote(){
        js.executeScript("arguments[0].click();",addNewNoteButton);
    }
    public void setNoteTitle(String title){
        js.executeScript("arguments[0].value='"+ title +"';",noteTitle);
    }
    public void setNoteDescription(String description){
        js.executeScript("arguments[0].value='"+ description +"';",noteDescription);
    }
    public void clickNoteSubmitButton(){
        js.executeScript("arguments[0].click();",noteSubmitButton);
    }
    public String getNoteTitle(){
        String title = wait.until(ExpectedConditions.elementToBeClickable(noteTitle)).getText();
        return title;
    }
    public String getNoteDescription(){
        String description = wait.until(ExpectedConditions.elementToBeClickable(noteDescription)).getText();
        return description;
    }
    public String getNoteTitleList(){
        String title = wait.until(ExpectedConditions.elementToBeClickable(noteTitleList)).getText();
        return title;
    }
    public String getNoteDescriptionList(){
        String description = wait.until(ExpectedConditions.elementToBeClickable(noteDescriptionList)).getText();
        return description;
    }
    public Integer getNoteID(){
        Integer i = Integer.valueOf(noteID.getText());
        return i;
    }
    public void clickNoteEditButton(){
        js.executeScript("arguments[0].click();",noteEditButton);
    }
    public void clickNoteDeleteButton(){
        js.executeScript("arguments[0].click();",noteDeleteButton);
    }
    public void navigateToCredentials(){
        js.executeScript("arguments[0].click();", credentialsTab);
    }
    public void clickaddNewCredentialButton(){
        js.executeScript("arguments[0].click();", addNewCredentialButton);
    }
    public void enterCredentials(String url, String username, String password){
        js.executeScript("arguments[0].value='"+ url +"';", credentialUrl);
        js.executeScript("arguments[0].value='"+ username +"';", credentialUsername);
        js.executeScript("arguments[0].value='"+ password +"';", credentialPassword);
    }
    public void clickCredentialSubmitButton()
    {
        js.executeScript("arguments[0].click();", credentialSubmit);
    }

    public String getCredentialUrlList(){
        String url = wait.until(ExpectedConditions.elementToBeClickable(urlList)).getText();
        return url;
    }
    public String getCredentialUsernameList(){
        String username = wait.until(ExpectedConditions.elementToBeClickable(usernameList)).getText();
        return username;
    }
    public String getCredentialPasswordList(){
        String password = wait.until(ExpectedConditions.elementToBeClickable(passwordList)).getText();
        return password;
    }
    public void clickCredentialEditButton(){
        js.executeScript("arguments[0].click();", credentialEditButton);
    }
    public void clickCredentialDeleteButton(){
        js.executeScript("arguments[0].click();", credentialDeleteButton);
    }
    public String getCredentialUrl(){
        String url = (String) js.executeScript("return arguments[0].value", credentialUrl);
        return url;
    }
    public String getCredentialUsername(){
        String username = (String) js.executeScript("return arguments[0].value", credentialUsername);
        return username;
    }
    public String getCredentialPassword(){
        String password = (String) js.executeScript("return arguments[0].value", credentialPassword);
        return password;
    }

}
