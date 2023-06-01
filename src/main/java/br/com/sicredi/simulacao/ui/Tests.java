package test.ui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tests {

	// Configura��es
	private WebDriver driver;
	private int timeout;

	// R�tulos
	private By lblValorAplicarError = By.id("valorAplicar-error");

	// Campos
	private By txtValorAplicar = By.id("valorAplicar");
	private By txtValorInvestir = By.id("valorInvestir");
	private By txtTempo = By.id("tempo");

	// Combos
	private By cmbPeriodo = By.xpath("//div[@class=\"blocoFormulario blocoMeses blocoSelect\"]");

	// Itens Combo
	private By itmAnos = By.xpath("//a[contains(text(), \"Anos\")]");

	// Bot�es
	private By btnSimularButton = By.xpath("//button[@class=\"btn btnAmarelo btnSimular\"]");
	private By btnRefazerButton = By.xpath("//a[@class=\"btn btnAmarelo btnRefazer\"]");

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.sicredi.com.br/html/ferramenta/simulador-investimento-poupanca/");
		timeout = 60;
	}

	@After
	public void tearDown() throws Exception {
		driver.close();
	}

	@Test
	public void Test1() {

		this.WebDriverWaitElement(txtValorAplicar);

		driver.findElement(txtValorAplicar).sendKeys("20,00");

		driver.findElement(txtValorInvestir).sendKeys("20,00");

		driver.findElement(txtTempo).sendKeys("5");

		driver.findElement(cmbPeriodo).click();

		this.WebDriverWaitElement(itmAnos);

		driver.findElement(itmAnos).click();

		driver.findElement(btnSimularButton).click();

		this.WebDriverWaitElement(btnRefazerButton);

		driver.findElement(btnRefazerButton).click();
	}

	@Test
	public void Test2() {

		this.WebDriverWaitElement(txtValorAplicar);

		driver.findElement(txtValorAplicar).sendKeys("19,99");

		driver.findElement(txtValorAplicar).sendKeys(Keys.TAB);

		this.ValidateValue();
	}

	// Espera o elemento ficar acess�vel e vis�vel na tela
	public void WebDriverWaitElement(By element) {
		new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(element));
		new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	// Valida o valor aplicado
	public void ValidateValue() {
		String message;

		// Define a mensagem de valida��o
		message = "Valor m�nimo de 20.00".trim().toUpperCase();

		this.WebDriverWaitElement(lblValorAplicarError);

		// Valida a mensagem
		assert (driver.findElement(lblValorAplicarError).getText().trim().toUpperCase().equals(message));
	}
}
