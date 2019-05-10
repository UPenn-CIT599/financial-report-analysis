import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class FinancialDataTest {

	// Construct dummy FinancialData object
	Map<String, Integer> testMap = new HashMap<String, Integer>();

	FinancialData data = new FinancialData(100, 200, 300, 2010, 3, "Google",
			"This is my Google company statement wowza!", testMap);

	@Test
	void revenueTest() {
		assertEquals(100, data.getRevenue();
	}

	@Test
	void netIncomeTest() {
		assertEquals(200, data.getNetIncome());
	}

	@Test
	void adjustedNetIncomeTest() {
		assertEquals(300, data.getAdjustedNetIncome());
	}

	@Test
	void finYearTest() {
		assertEquals(2010, data.getFinYear());
	}

	@Test
	void finQuarterTest() {
		assertEquals(3, data.getFinQuarter());
	}

	@Test
	void companyNameTest() {
		assertEquals("Google", data.getCompanyName());
	}

	@Test
	void companyStatementTest() {
		assertTrue(data.getCompStatement().contains("company statement wowza"));
	}

	@Test
	void wordCountTest() {
		assertNotNull(data.getWordCount());
	}

	@Test
	void toStringTest() {
		assertTrue(data.toString().contains("Google,2010,3"));
	}

}
