package ru.skillbox.patterns.screen_object.screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import ru.skillbox.patterns.builder.action.SwipeHelper;

public class Screen {

    AndroidDriver<?> driver;

    SwipeHelper swipeHelper;

    Screen(AndroidDriver<?> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        swipeHelper = new SwipeHelper(driver);
    }

}
