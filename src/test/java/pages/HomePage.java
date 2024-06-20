package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Actions;
import utils.Log;

public class HomePage {

    private WebDriver driver;
    private Actions actions;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(this.driver); 
    }
    
    public void adicionarItens () {
    	
    	actions.clicarBotaoPegandoPeloId("add-to-cart-sauce-labs-backpack");
    	actions.clicarBotaoPegandoPeloId("add-to-cart-sauce-labs-bike-light");
    	actions.clicarBotaoPegandoPeloId("add-to-cart-sauce-labs-bolt-t-shirt");
    	actions.clicarBotaoPegandoPeloId("add-to-cart-sauce-labs-fleece-jacket");
    	actions.clicarBotaoPegandoPeloId("add-to-cart-sauce-labs-onesie");
    	actions.clicarBotaoPegandoPeloId("add-to-cart-test.allthethings()-t-shirt-(red)");
    }

    public void detalheProduto(String acaoId) {
        int itens = 5;

        for (int i = 0; i <= itens; i++) {
        	
            try {
        	WebElement produto = driver.findElement(By.xpath("//*[@id=\"item_" + i + "_title_link\"]/div"));
            
        	produto.click();
            actions.esperar(200);
            actions.clicarBotaoPegandoPeloId(acaoId);
            
            actions.esperar(200);
            actions.clicarBotaoPegandoPeloId("back-to-products");
            
            } catch(NoSuchElementException e) {
            	String msg = "Elemento não encontrado - XPath: //*[@id=\"item_" + i + "_title_link\"]/div";
            	
            	Assert.fail(msg);
            	Log.registrar(msg);
        }
    }
  }
    
    public void removerItens () {
    	
    	actions.clicarBotaoPegandoPeloId("remove-sauce-labs-backpack");
    	actions.clicarBotaoPegandoPeloId("remove-sauce-labs-bike-light");
    	actions.clicarBotaoPegandoPeloId("remove-sauce-labs-bolt-t-shirt");
    	actions.clicarBotaoPegandoPeloId("remove-sauce-labs-fleece-jacket");
    	actions.clicarBotaoPegandoPeloId("remove-sauce-labs-onesie");
    	actions.clicarBotaoPegandoPeloId("remove-test.allthethings()-t-shirt-(red)");
    }
    
    public void removerDentroCarrinho () {
    	actions.clicarBotaoPegandoPeloId("shopping_cart_container");
    	
    	actions.clicarBotaoPegandoPeloId("remove-sauce-labs-backpack");
    	actions.clicarBotaoPegandoPeloId("remove-sauce-labs-bolt-t-shirt");
    	actions.clicarBotaoPegandoPeloId("remove-test.allthethings()-t-shirt-(red)");
    	actions.clicarBotaoPegandoPeloId("remove-sauce-labs-bike-light");
    	actions.clicarBotaoPegandoPeloId("remove-sauce-labs-fleece-jacket");
    	actions.clicarBotaoPegandoPeloId("remove-sauce-labs-onesie");
    }
    
    public void iconeQuantidadeCarrinho(String expectativa) {
        try {
            WebElement elemento = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"));
            String valor = elemento.getText();

            Assert.assertEquals(valor, expectativa);
            Log.registrar(valor);
        } catch (NoSuchElementException e) {
            if ("0".equals(expectativa)) {
                Log.registrar("Carrinho está vazio conforme esperado.");
                Assert.assertTrue(true); 
            } else {
                Log.registrar("Carrinho está vazio, mas a expectativa era: " + expectativa);
                Assert.fail("Carrinho está vazio, mas a expectativa era: " + expectativa);
            }
        }
    }
    
}

