package ru.skillbox.patterns;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.skillbox.patterns.screen_object.DriverFactory;
import ru.skillbox.patterns.screen_object.action.Direction;
import ru.skillbox.patterns.screen_object.action.SwipeHelper;
import ru.skillbox.patterns.screen_object.screens.CatalogScreen;
import ru.skillbox.patterns.screen_object.screens.FilterScreen;
import ru.skillbox.patterns.screen_object.screens.Tabbar;

import java.net.MalformedURLException;
import java.time.Duration;

public class ScreenObject {
    private final DriverFactory driverFactory = new DriverFactory();
    private AndroidDriver<?> driver;

    @Before
    @Step("Настройка драйвера")
    public void setDriver() throws MalformedURLException {
        driver = driverFactory.setUp(Platform.ANDROID);
    }

    @Test
    public void scOb() {
        Tabbar tabbar = new Tabbar(driver);
        //проверка, что вкладка “Каталог” в нижнем таббаре не выбрана
        SwipeHelper swipeHelper = new SwipeHelper(driver);
        //в поле “Название товара” ввод “Телевизор” и переход к результатам поиска.
        CatalogScreen catalogScreen = new CatalogScreen(driver);
        FilterScreen filterScreen = new FilterScreen(driver);
        String television = "Телевизор";
//сохранение в переменной foundTitleText текста “Найдено ...телевизоров”

        Assert.assertFalse(tabbar.isCatalogSelected());
        //переход в таббар на вкладку Каталог
        String foundTitleText = tabbar.clickCatalog().search(television).getFoundTitleText();
        //проверка, что теперь вкладка “Каталог” выбрана
        Assert.assertTrue(tabbar.isCatalogSelected());
        //одинарный скролл вверх и одинарный скролл вниз

        String foundSaleTitleText = catalogScreen.clickFilters().turnOnDiscountedGoods().applyFilters().getFoundTitleText();
        //активация переключателя “Товары со скидкой” и нажатие на красную кнопку внизу “Показать..”.
        //сохранение в переменную countSaleTitle текста “Найдено ...телевизоров”.
        //проверка, что переменная countTitle не равна countSaleTitle.
        Assert.assertNotEquals(foundTitleText, foundSaleTitleText);
        //блокировка экрана эмулятора на 3 секунды.
        ((AndroidDriver<?>) driver).lockDevice(Duration.ofSeconds(3));
        //проверка, что после разблокировки экрана в поисковом поле остался текст “Телевизор”.
        Assert.assertEquals(television, catalogScreen.getSearchText());
        //свайп влево/вправо не по всему экрану, а по виджету “Уточните категорию”
        catalogScreen.swipeSpecifyCategory();
    }
}