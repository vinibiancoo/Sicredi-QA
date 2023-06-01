package test.api;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Tests {

	private String baseURL = "http://5b847b30db24a100142dce1b.mockapi.io/api/v1/simulador";

	public String[] valuesCompare = new String[] { "2.802", "3.174", "3.564", "3.971" };
	public String[] monthsCompare = new String[] { "112", "124", "136", "148" };

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/*
	 * Verifica o ID
	 */
	@Test
	public void Test001() throws Exception {
		ValidateStatusService();

		given().when().get(baseURL).then().contentType(ContentType.JSON).statusCode(200).assertThat().body("id",
				equalTo(new Integer("1")));
	}

	/*
	 * Verifica os valores
	 */
	@Test
	public void Test002() {
		ValidateValue(valuesCompare, "valor");
	}

	/*
	 * Verifica os meses
	 */
	@Test
	public void Test003() {
		ValidateValue(monthsCompare, "meses");
	}

	// Valida o retorno dos itens
	public void ValidateValue(String[] values, String path) {
		ValidateStatusService();

		Response resp = given().when().get(baseURL).then().contentType(ContentType.JSON).statusCode(200).extract()
				.response();

		for (int i = 0; i < values.length; i++) {
			String value = resp.jsonPath().get(path + "[" + i + "]");
			Assert.assertTrue((value).contains(values[i]));
		}
	}

	// Verifica status do servi�o
	public void ValidateStatusService() {
		given().when().get(baseURL).then().contentType(ContentType.JSON);
	}
}
