package pages;

import org.openqa.selenium.WebDriver;
import utils.Actions;


public class ShoppingPage {

    private WebDriver driver;
    private Actions actions;

    public ShoppingPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(this.driver); 
    }
    
    public void adicionarItem() {
    	
    }
}