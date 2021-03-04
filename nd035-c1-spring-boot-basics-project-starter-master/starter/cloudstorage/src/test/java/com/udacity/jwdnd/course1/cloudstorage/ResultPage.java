package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    @FindBy(id="resultTitle")
    WebElement resultTitle;
    @FindBy(id="successLink")
    WebElement successLink;
    @FindBy(id="failLink")
    WebElement failLink;
    @FindBy(id="errorLink")
    WebElement errorLink;

    private final JavascriptExecutor js;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        js = (JavascriptExecutor) driver;
    }
    public void clickSuccessLink(){
        js.executeScript("arguments[0].click();", successLink);
    }
    public void clickFailLink(){
        js.executeScript("arguments[0].click();", failLink);
    }
    public void clickErrorLink(){
        js.executeScript("arguments[0].click();", errorLink);
    }


}
