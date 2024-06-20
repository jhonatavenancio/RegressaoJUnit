package pages;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utils.Actions;
import utils.Log;

public class LoginPage {

    private WebDriver driver;
    private Actions actions;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(this.driver); 
    }

    public void signIn(String login, String senha) {
        actions.escreverPegandoPeloId("user-name", login);
        actions.escreverPegandoPeloId("password", senha);
        actions.clicarBotaoPegandoPeloId("login-button");
    }

    public void signInApenasUsuario(String usuario) {
        actions.escreverPegandoPeloId("user-name", usuario);
        actions.clicarBotaoPegandoPeloId("login-button");
    }

    public void signInApenasSenha(String senha) {
        actions.escreverPegandoPeloId("password", senha);
        actions.clicarBotaoPegandoPeloId("login-button");
    }
    
    public void logout() {
    	actions.esperar(1200);
    	actions.clicarBotaoPegandoPeloId("react-burger-menu-btn");
        actions.clicarBotaoPegandoPeloId("logout_sidebar_link");
        
    }

    public void validarTextoIgual(String css, String mensagem) {
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
