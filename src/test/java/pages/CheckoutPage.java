package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Actions;
import utils.Log;

public class CheckoutPage {

    private WebDriver driver;
    private Actions actions;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(this.driver); 
    }
 
    private void checkout() {
    	actions.clicarBotaoPegandoPeloId("shopping_cart_container");
    	actions.clicarBotaoPegandoPeloId("checkout");
    }
    
    public void informacoesPessoais (String nome, String sobrenome, String zipcode) {
    	checkout();
    	actions.escreverPegandoPeloId("first-name", nome);
    	actions.escreverPegandoPeloId("last-name", sobrenome);
    	actions.escreverPegandoPeloId("postal-code", zipcode);
    	actions.clicarBotaoPegandoPeloId("continue");
    }
    
    public void voltarCarrinho () {
    	checkout();
    	actions.clicarBotaoPegandoPeloId("cancel");
    }
    
    public void voltarPagamento (String nome, String sobrenome, String zipcode) {
    	checkout();
    	informacoesPessoais(nome,sobrenome,zipcode);
    	actions.clicarBotaoPegandoPeloId("continue");
    	actions.clicarBotaoPegandoPeloId("cancel");
    }
    
    public void voltarCheckout () {
    	checkout();
    	informacoesPessoais("","","");
    	actions.clicarBotaoPegandoPeloId("cancel");
    }
    
    public void finalizarCompra (String nome, String sobrenome, String zipcode) {
    	checkout();
    	informacoesPessoais(nome,sobrenome,zipcode);
    	actions.clicarBotaoPegandoPeloId("finish");
    }
    
    public void validarAlerta(String css, String mensagem) {
    	WebElement mensagemAtual = driver.findElement(By.cssSelector(css));
    	
    	String atual = mensagemAtual.getText();
    	String esperado = mensagem;
    	
    	if (atual.equals(esperado)) {
    		Log.registrar("Texto igual = "+ atual +" e "+ esperado +" .");
    	} else {
    		Log.registrar("Texto diferente = "+ atual +" e "+ esperado +" .");
    		Assert.fail();
    	}
    	
    }
}