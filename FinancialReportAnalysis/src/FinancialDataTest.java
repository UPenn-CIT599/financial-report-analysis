import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FinancialDataTest {

	FinancialData data = new FinancialData(100, 200, 300, 2010, 3, 
			"Google", "This is my Google company statement wowza!");
	
	@Test
	void revenueTest() {
		assertEquals(data.getRevenue(), 100);
	}
	
	@Test
	void netIncomeTest() {
		assertEquals(data.getNetIncome(), 200);
	}
	
	@Test
	void adjustedNetIncomeTest() {
		assertEquals(data.getAdjustedNetIncome(), 300);
	}
	
	
	@Test
	void finYearTest() {
		assertEquals(data.getFinYear(), 2010);
	}
	
	@Test
	void finQuarterTest() {
		assertEquals(data.getFinQuarter(), 3);
	}
	
	@Test
	void companyNameTest() {
		assertEquals(data.getCompanyName(), "Google");
	}
	
	@Test
	void companyStatementTest() {
		assertTrue(data.getCompStatement().contains("company statement wowza"));
	}
	

}
