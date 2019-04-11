# Proposed Design and CRCs

## DataReader Class
This class takes in a PDF file, ideally from a URL, and outputs a string or .txt file using Apache PDF Box library.

### Responsibilities
- URLReader method: Opens a URL and reads contents into an ArrayList
- PDFReader method: Opens a PDF from the URL (how to connect these?) and outputs a string or .txt file with Apacha PDF Box

### Collaborators
- DataCleaner 

## DataParser Class
This class takes the string or .txt file from DataReader and parses the appropriate data into FinancialData and SentimentAnalysis objects. This class may be an *abstract base class* that extends into three subclasses that will parse data appropriately from each of the three companies, depending on their formatting.

### Responsibilities
- parseFinancialData method: reads the file for mentions of 'GAAP/IFRS net income', 'non-GAAP/adjusted/non-IFRS net income', 'revenue', 'GAAP/IFRS operating income', 'non-GAAP/adjusted/IFRS operating income'
- parseSentimentAnalysis method: reads the file and creates a hashmap of words to count, excluding common words such as "a" and "the"

### Collaborators
- DataReader (from which the data comes)
- FinancialData
- SentimentAnalysis

## FinancialData Class


## SentimentAnalysis Class

