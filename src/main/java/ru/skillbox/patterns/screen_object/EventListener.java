public class EventListener implements AppiumWebDriverEventListener{
    public class  final logger LOGGER = Logger.getLogger(EventListener.class.getName());

    public void beforeClickOn(WebElement element, WebDriver driver){
        LOGGER.log(Level.INFO, "Click on element" + element.getAttribute("resource-id"));
    }
}