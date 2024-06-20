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
import pages.LoginPage;

public class LoginTest {
    private static WebDriver driver;
    private LoginPage loginPage;

    @BeforeAll
    public static void iniciarLog() {
        Log.criarArquivoLog("Log.teste_Login");
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
    }

    @AfterEach
    public void encerrarDriver() {
        Browser.fecharNavegador();
    }

    @Test
    public void loginComSucesso() {
        String urlInicial = driver.getCurrentUrl();
        loginPage.signIn("standard_user", "secret_sauce");
        String urlAposLogin = driver.getCurrentUrl();

        String mensagem = "Login realizado com sucesso!";
        assertNotEquals(urlInicial, urlAposLogin, mensagem);
        Log.registrar(mensagem);
    }

    @Test
    public void loginComSenhaInvalida() {
    	String mensagemEsperada = "Epic sadface: Username and password do not match any user in this service";
    	
        loginPage.signIn("standard_user", "123");
        loginPage.validarTextoIgual("#login_button_container > div > form > div.error-message-container.error > h3", mensagemEsperada);

    }
    
    @Test
    public void loginComUsuarioInvalido() {
    	String mensagemEsperada = "Epic sadface: Username and password do not match any user in this service";
    	
        loginPage.signIn("usuarioo", "secret_sauce");
        loginPage.validarTextoIgual("#login_button_container > div > form > div.error-message-container.error > h3", mensagemEsperada);

    }
    
    @Test
    public void loginComApenasUsuario() {
    	String mensagemEsperada = "Epic sadface: Password is required";
    	
        loginPage.signIn("usuarioo", "");
        loginPage.validarTextoIgual("#login_button_container > div > form > div.error-message-container.error > h3", mensagemEsperada);

    }
    
    @Test
    public void loginComApenasSenha() {
    	String mensagemEsperada = "Epic sadface: Username is required";
    	
        loginPage.signIn("", "secret_sauce");
        loginPage.validarTextoIgual("#login_button_container > div > form > div.error-message-container.error > h3", mensagemEsperada);

    }
    
    @Test
    public void loginComUsuarioBloqueado() {
    	String mensagemEsperada = "Epic sadface: Sorry, this user has been locked out.";
    	
        loginPage.signIn("locked_out_user", "secret_sauce");
        loginPage.validarTextoIgual("#login_button_container > div > form > div.error-message-container.error > h3", mensagemEsperada);

    }
    
    @Test
    public void loginLogout() {
    	loginPage.signIn("standard_user", "secret_sauce");
    	String urlInicial = driver.getCurrentUrl();
    	
    	loginPage.logout();
    	String urlAposLogout = driver.getCurrentUrl();
    	
        String mensagem = "Logout realizado com sucesso!";
        assertNotEquals(urlInicial, urlAposLogout, mensagem);
        Log.registrar(mensagem);
    }
}
