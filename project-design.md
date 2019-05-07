# Design and CRCs

## PDFManager Class
This class uses the Apache PDF Box Library to return the String output of the PDF File.

## Responsibilities
- Has PDF filepath, file to open
- toTxt() method: returns String output of PDF file
- setFilePath() method: sets the file path of the PDF File

### Collaborators
- PDFBoxReaderFromFile

## PDFBoxReaderFromFile Class
This class takes in a PDF file and outputs a .txt file or String using Apache PDF Box library, into an output folder called "txt"

### Responsibilities
- Has String fileName to open
- getString() method: returns String output of PDF file
- printToTxt() method: scan the pdf from filename and export as a txt file with file name fileName_converted.txt

### Collaborators
- PDFManager
- Runner class 

*## URLToTxt Class (Current iteration of project does not run with opening PDFs from URLs)
This class allows the user to access a PDF from a URL and access a .txt file with the contents of the PDF.

*### Responsibilities
- Has String urlToOpen
*- readPDF() method: opens the URl and PDF Document contained and returns the String content of the PDF
*- printToTxt() method: writes the String output of the PDF to the new .txt file

*### Collaborators
*- N/A  (Current iteration of project does not run with opening PDFs from URLs)

## DataParser Class
This class takes the string or .txt file from DataReader and parses the appropriate data into FinancialData and SentimentAnalysis objects. This class is an *abstract base class* that extends into three subclasses that will parse data appropriately from each of the three companies, depending on their formatting.

### Responsibilities
- Constructor: will call all the below methods, then construct the appropriate FinancialData object. 
- parseRevenue()
- parseNetIncome()
- parseAdjustedNetIncome()
- parseFinYear()
- parseFinQuarter
- parseCompanyName() - this may not be necessary if there are subclasses for each company
- parseCompStatement()
- createWordCount method: reads the file and creates a hashmap of words to count, excluding common words such as "a" and "the" for the FinancialData class. This will also help populate the FinancialData object.
- outputFullStatementText() 

### Collaborators
- Runner, which retrieves the .txt files from PDFBoxFromReader and parses
- FinancialData, which holds all the information parsed
- (ParserBaba and ParserBaidu which extend this class)

## ParserBaba Class
This class extends DataParser and implements the methods required to parse data for Alibaba PDF Files

### Responsibilities
- Has currRevenue, currNetIncome, currNonGAAPNetIncome
- Has prevRevenue, prevNetIncome, numNetIncomeChange
- Has prevNonGAAPNetIncome, numNonGAAPNetIncomeChange
- implements parseRevenue(), parseNetIncome(), parseAdjustedNetIncome(), parseFinYear(), parseFinQuarter(), parseCompanyName(), parseCompStatement() 

### Collaborators 
- Runner, which retrieves the .txt files from PDFBoxFromReader and parses
- FinancialData, which holds all the information parsed
- (DataParser from which this class extends)

## FinancialData Class
This Class stores the financial data read from the pdf or url. Each object represent 1 quarter result of 1 company. It also include a hashmap to store word count read from a company statement / financial statements in order to feed into a Sentiment Analysis class

### Responsibilities
- Have "Revenue"
- Have "Net Income"
- Have "Adjused Net Income"
- Have "Financial Year", "Financial Quarter", "Company Name"
- Have a HashMap "Word Count"
- Have a String "compStatement"

### Collaborators
- Runner, which helps populate these fields
- WordCounter, which helps populate the HashMap Word Count
- SentimentAnalysis

## SentimentAnalysisResult Class
This class conducts sentiment analysis on a company statement. Ideally we would find an existing java library that perform the analysis on the HashMap "Words Count" or the txt in "compStatement" in Financial Data Class. A candidate library we found is https://github.com/Ruthwik/Sentiment-Analysis which is using Stanford CoreNLP and gives sentiment score of 5 classes very negative, negative, neutral, positive, and very positive.

### Responsibilities
- the Constructor: use the Ruthwik Sentiment Analysis tool to calculate sentiment score on txt in "compStatement" from FinancialData. 
- getSentimentScore() method: return the sentiment score
- getSentimentType() method: return the highest sentiment type
- getVeryPositive, getPositive, getNeutral, getNegative, getVeryNegative: return the percentage of the text that correponds to each sentiment type

### Collaborators
- Runner, which calls the sentiment analysis on the results in FinancialData

## Runner Class

## Visualization Class
This class presents the results of the cross-reference and sentiment analysis. First it would output a cleanly formatted .txt file report. To expand, it could output a .csv file which could be opened on Excel and include instructions for the viewers to generate graphs and visualizations on Excel themselves. Another expansion would be to create a word cloud or other graphics within the report itself and output a PDF report so the viewer would not need to generate their own visualization. 

### Responsibilities

### Collaborators
- SentimentAnalysis
- CrossReferece
