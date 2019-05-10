import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


// a test being run on only one of the financial statements
class ParserBabaTest {


	DataParser parser = new ParserBaba("txt/BABA201412_converted.txt");

	@Test
	void revenueTest() {
		assertEquals(26179.0, parser.getCurrRevenue());
	}

	@Test
	void netIncomeTest() {
		assertEquals(5983.0, parser.getCurrNetIncome());
	}

	@Test
	void adjustedNetIncomeTest() {
		assertEquals(13115.0, parser.getCurrAdjustedNetIncome());
	}

	@Test
	void financialYearTest() {
		assertEquals(2014, parser.getFinancialYear());
	}

	@Test
	void financialQuarterTest() {
		assertEquals(12, parser.getFinQuarter());
	}

	@Test
	void companyNameTest() {
		assertEquals("Alibaba", parser.getCompanyName());
	}

}
