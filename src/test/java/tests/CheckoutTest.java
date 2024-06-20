package tests;


import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import utils.Log;
import utils.Browser;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;

public class CheckoutTest {
    private static WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private CheckoutPage checkout;

    @BeforeAll
    public static void iniciarLog() {
        Log.criarArquivoLog("Log.teste_checkout");
    }

    @AfterAll
    public static void encerrarLog() {
        Log.encerrarLog();
    }

    @BeforeEach
    public void iniciaDriver() {
        driver = Browser.iniciarNavegador("FIREFOX");
        driver.get("https://www.saucedemo.com/");
        
        loginPage = new LoginPage(driver);
        loginPage.signIn("standard_user", "secret_sauce");
        
        homePage = new HomePage(driver);
        checkout = new CheckoutPage(driver);
    }

    @AfterEach
    public void encerrarDriver() {
        Browser.fecharNavegador();
    }
    
    @Test
    public void apenasNome() {
    	String expectativa = "Error: Last Name is required";
    	checkout.informacoesPessoais("Joãozinho", "", "");
    	checkout.validarAlerta("#checkout_info_container > div > form > div.checkout_info > div.error-message-container.error > h3", expectativa);
    	
    }
   
    @Test
    public void apenasSobrenome() {
    	String expectativa = "Error: First Name is required";
    	checkout.informacoesPessoais("", "Souza", "");
    	checkout.validarAlerta("#checkout_info_container > div > form > div.checkout_info > div.error-message-container.error > h3", expectativa);
    	
    }
    
    @Test
    public void nomeSobrenome() {
    	String expectativa = "Error: Postal Code is required";
    	checkout.informacoesPessoais("Joãozinho", "Souza", "");
    	checkout.validarAlerta("#checkout_info_container > div > form > div.checkout_info > div.error-message-container.error > h3", expectativa);
    	
    }
    
    @Test
    public void finalizarPedido() {
    	String expectativa = "Thank you for your order!";
    	checkout.finalizarCompra("Joãozinho", "Souza", "1001");
    	checkout.validarAlerta("#checkout_complete_container > h2", expectativa);
    	
    }
    
    @Test
    public void finalizarCarrinhoCheio() {
    	String expectativa = "Thank you for your order!";
    	
    	homePage.adicionarItens();
    	checkout.finalizarCompra("Joãozinho", "Souza", "1001");
    	checkout.validarAlerta("#checkout_complete_container > h2", expectativa);
    	
    }
    
    @Test
    public void carrinhoVoltar() {
    	String urlInicial = driver.getCurrentUrl();
    	checkout.voltarCarrinho();
    	String urlAposLogin = driver.getCurrentUrl();
    	
    	String mensagem = "Login realizado com sucesso!";
        assertNotEquals(urlInicial, urlAposLogin, mensagem);
        Log.registrar(mensagem);
    }
    
    @Test
    public void checkoutVoltar() {
    	String urlInicial = driver.getCurrentUrl();
    	checkout.voltarCheckout();
    	String urlAposLogin = driver.getCurrentUrl();
    	
    	String mensagem = "Login realizado com sucesso!";
        assertNotEquals(urlInicial, urlAposLogin, mensagem);
        Log.registrar(mensagem);
    }
}
