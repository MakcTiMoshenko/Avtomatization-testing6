package ru.skillbox.patterns.screen_object.screens;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import ru.skillbox.patterns.builder.action.Direction;

public class CatalogScreen extends Tabbar {

    @AndroidFindBy(id = "search_title")
    MobileElement searchTitle;

    @AndroidFindBy(id = "search_src_text")
    MobileElement searchText;

    @AndroidFindBy(uiAutomator = "resourceIdMatches(\".*id/title\").textContains(\"товаров\")")
    MobileElement foundTitle;

    @AndroidFindBy(id = "filter_label")
    MobileElement filterLabel;

    public CatalogScreen swipeSpecifyCategoryPager() {
        swipeHelper.swipe(Direction.LEFT, specifyCategoryPager);
        return this;
    }

    @AndroidFindBy(id = "specify_category_pager")
    MobileElement specifyCategoryPager;

    public CatalogScreen(AndroidDriver<?> driver) {
        super(driver);
    }

    public String getFoundTitleText() {
        return foundTitle.getText();
    }

    public CatalogScreen search(String text) {
        searchTitle.click();
        searchText.sendKeys(text);
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        return this;
    }

    public String getFoundTitle(){
        return foundTitle.getText();
    }

    public FilterScreen clickFilters() {

        filterLabel.click();
        return new FilterScreen(driver);

    }

    public String getSearchText() {
        return searchText.getText();
    }

    public CatalogScreen swipeSpecyfyCategory(){
        swipeHelper.swipe(Direction.LEFT, specifyCategoryPager);
        return this;
    }
}
