import static org.junit.jupiter.api.Assertions.assertTrue;

public class Runner {

	public static void main(String[] args) {
		PDFBoxReadFromFile PDFReader = new PDFBoxReadFromFile("201409.pdf");
		assertTrue(PDFReader.getString().contains("Alibaba Group") && 
				PDFReader.getString().contains("September 30, 2014"));
		
		
		
		}
}
