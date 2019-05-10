# Design and CRCs

## URLToTxt Class
This class allows the user to access a PDF from a URL and print a .txt file with the contents of the PDF.

### Responsibilities
- Has String urlToOpen
- readPDF() method: opens the URl and PDF Document contained and returns the String content of the PDF
- printToTxt() method: writes the String output of the PDF to the new .txt file in a "txt" folder

### Collaborators
- Runner

## DataParser Class
This class takes the string or .txt file from DataReader and holds the appropriate data in its instance variables. This class is an *abstract base class*. Currently the ParserBaba class extends from this class, but other classes for other companies may extend from it as well. It is the responsibility of the classes that extend from this base class to work out how to parse to instance variables. 

### Responsibilities
- Has revenue, netIncome, adjustedNetIncome, financialYear, finQuarter, companyName, compStatement
- Has accessor methods for all of these instance variables.

### Collaborators
- Runner, which retrieves the .txt files and parses
- (ParserBaba which extends this class)

## ParserBaba Class
This class extends DataParser and implements the methods required to parse data for Alibaba PDF Files

### Responsibilities
- parseRevenue(), parseNetIncome(), parseAdjustedNetIncome(), parseFinancialYearQuarterCompany(), and parseCompStatement() which run through the .txt file and input the appropriate data into the instance variables.

### Collaborators 
- Runner, which retrieves the .txt files and parses
- (DataParser from which this class extends)

## WordCounter Class
Grabs a .txt file, then count how many occurrences of each word, input to hashmap

### Responsibilities
- Has wordCount HashMap
- countOfWords() method builds and returns wordCount HashMap of all words and their count
- Has additional method, topWordCount, that builds another HashMap of topN most frequently occurring words, excluding common words such as "the" and "and"

### Collaborators
- Runner Class to store HashMap within FinancialData object

## FinancialData Class
This Class stores the financial data read from the pdf or url. Each object represents 1 quarter result of 1 company. 

### Responsibilities
- Have "Revenue"
- Have "Net Income"
- Have "Adjusted Net Income"
- Have "Financial Year", "Financial Quarter", "Company Name"
- Have a String "compStatement"
- Have a HashMap "WordCount"

### Collaborators
- Runner, which helps populate these fields from the data from the Parser and WordCounter classes
- SentimentAnalysisResult

## SentimentAnalysisResult Class
This class conducts sentiment analysis on a company statement. A library we found is https://github.com/Ruthwik/Sentiment-Analysis which is using Stanford CoreNLP and gives sentiment score of 5 classes very negative, negative, neutral, positive, and very positive.

### Responsibilities
- the Constructor: use the Ruthwik Sentiment Analysis tool to calculate sentiment score on txt in "compStatement" from FinancialData. 
- getSentimentScore() method: return the sentiment score
- getSentimentType() method: return the highest sentiment type
- getVeryPositive, getPositive, getNeutral, getNegative, getVeryNegative: return the percentage of the text that correponds to each sentiment type

### Collaborators
- Runner, which calls the sentiment analysis on the results in FinancialData

## Runner Class
Runs everything and calls the ReportGenerator class

### Responsibilities
- Has finDataHM, a HashMap mapping a FinancialData object to the key (name, year, quarter)
- Has senResultHM, a HashMap mapping a SentimentAnalysis object to the key (name, year, quarter)
- PDFtoTxt method: Exports PDF files to .txt files (Currently unused in favor of..)
- convertURLToTxt method: Exports PDF files from URL to .txt files
- readTxt method: Loop through all .txt files to populate above hashmaps for each file
- generateReports method: Call ReportGenerator class

### Collaborators
- PDFBoxReadFromFile
- PDFFileFilter
- TxtFileFilter
- FinancialData
- ParserBaba (and DataParser)
- SentimentAnalysis
- ReportGenerator
- WordCounter

## PDFFileFilter Class
This class implements the FileFilter interface to determine if a selected file is a PDF or not.

### Responsibilities
- accept() method: returns true if file is PDF

### Collaborators
- Runner Class

## TxtFileFilter Class
This class implements the FileFilter interface to determine if a selected file is a .txt file or not.

### Responsibilities
- accept() method: returns true if file is .txt

### Collaborators
- Runner Class

## ReportGenerator Class
This class presents the results of the financial data parsing and sentiment analysis. It outputs: an allData .csv file that contains all data from the financial data and sentiment analysis hashmaps, excluding the comp statement and word counts; as well as, for each PDF, a .txt file with the comp statement and top 10 words and count; and a .csv file with the top 10 words and count.

### Responsibilities
- Has finDataHM, a HashMap mapping a FinancialData object to the key (name, year, quarter)
- Has senResultHM, a HashMap mapping a SentimentAnalysis object to the key (name, year, quarter)
- generateCSV() method: output a cleanly formatted .csv file report with the name, year, quarter, adjusted net income, net income, revenue, sentiment analysis results.
- generateTxts() method: output cleanly formatted txt reports for each PDF including the compStatement and most-commonly used words.
- generateWordCountCSVS() method: output a cleanly formatted csv report for each PDF including the top 10 most commonly used words and count

### Collaborators
- Runner

# Unused Classes
The following classes were created but currently are not used because of the use of the URLtoTxt class. It is easier for the user to run with URLs hard-coded in main (or in the future, as user input) so they do not have to download PDFs prior. Nevertheless, the following classes would turn downloaded PDF files into txt files.

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
