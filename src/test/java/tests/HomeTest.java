package tests;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import utils.Log;
import utils.Browser;
import pages.HomePage;
import pages.LoginPage;

public class HomeTest {
    private static WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;

    @BeforeAll
    public static void iniciarLog() {
        Log.criarArquivoLog("Log.teste_homepage");
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
    }

    @AfterEach
    public void encerrarDriver() {
        Browser.fecharNavegador();
    }

    @Test
    public void adicionarProduto() {
    	homePage.adicionarItens(); 
    	homePage.iconeQuantidadeCarrinho("6");
    }
    
    @Test
    public void removerProduto() {
    	homePage.adicionarItens();
    	homePage.iconeQuantidadeCarrinho("6");
    	homePage.removerItens();
    	homePage.iconeQuantidadeCarrinho("0");
    }
    
    @Test
    public void adicionarPorDetalhes() {
    	homePage.detalheProduto("add-to-cart");
    	homePage.iconeQuantidadeCarrinho("6");
    }
    
    @Test
    public void removerPorDetalhes() {
    	homePage.detalheProduto("add-to-cart");
    	homePage.iconeQuantidadeCarrinho("6");
    	homePage.detalheProduto("remove");
    	homePage.iconeQuantidadeCarrinho("0");
    }
    
    @Test
    public void removerPeloCarrinho () {
    	homePage.adicionarItens();
    	homePage.iconeQuantidadeCarrinho("6");
    	homePage.removerDentroCarrinho();
    	homePage.iconeQuantidadeCarrinho("0");
    }
}
