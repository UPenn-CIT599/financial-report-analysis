import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParserBabaTest {

	DataParser parser = new ParserBaba("txt/BABA201412_converted.txt");
	
	@Test
	void revenueTest() {
		assertEquals(parser.getCurrRevenue(), 26179.0);
	}
	
	@Test
	void netIncomeTest() {
		assertEquals(parser.getCurrNetIncome(), 5983.0);
	}
	
	@Test
	void adjustedNetIncomeTest() {
		assertEquals(parser.getCurrAdjustedNetIncome(), 13115.0);
	}
	
	@Test
	void financialYearTest() {
		assertEquals(parser.getFinancialYear(), 2014);
	}
	
	@Test
	void financialQuarterTest() {
		assertEquals(parser.getFinQuarter(), 12);
	}
	
	@Test
	void companyNameTest() {
		assertEquals(parser.getCompanyName(), "Alibaba");
	}

}
