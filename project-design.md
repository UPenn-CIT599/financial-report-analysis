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
This Class stores the financial data read from the pdf or url. Each object represent 1 quarter result of 1 company. It also include a hashmap to store word count read from a company statement / financial statements in order to feed into a Sentiment Analysis class

### Responsibilities
- Have "Net Income", "EBITDA", "EBIT"
- Have "Adjusted EBITDA", "Adjusted EBITA", "nonGAAP Net Income"
- Have "Financial Year", "Financial Quarter", "Company Name"
- Have a HashMap "Words Count"
- Have a String "compStatement"
- Have an array of 3 integer "adjustedHigher" to store the result of checking if the adjusted/non-GAAP is higher than the unadjusted EBITA, EBITDA and Net Income
- Check EBITDA method(): Check if the "Adjusted EBITDA" is higher than "EBITDA", return the percentage difference (+ means adjusted higher)
- Check EBITA method(): Check if the "Adjusted EBITA" is higher than "EBITA" , return the percentage difference (+ means adjusted higher)
- Check Net Income method(): Check if the "nonGAAP Net Income" is higher than "Net Income" , return the percentage difference (+ means adjusted higher)


### Collaborators
- DataParser
- DataReader
- SentinmentAnalysis

## SentimentAnalysis Class
This class conduct sentiment analysis on a company statement. Ideally we would find an existing java library that perform the anlysis on the HashMap "Words Count" or the txt in "compStatement" in Financial Data Class. A candidate library we found is https://github.com/Ruthwik/Sentiment-Analysis which is using Stanford CoreNLP and gives sentiment score of 5 classes very negative, negative, neutral, positive, and very positive.

### Responsibilities
- calSentiment() method: use the Ruthwik Sentiment Analysis tool to calculate sentiment score on txt in "compStatement"

### Collaborators
- Financial Data
- Visualization 

## Cross Reference Class
This class cross check the result of the sentiment analysis and the Financial Data class array "adjustedHigher" to check for correlation. The idea is to see if the adjusted net income is higher woudl result in the sentiment being more optimistics. This is more of a binary test. An expanded scope would be to assess the magnitude, e.g. if adjusted net income is X% higher, it would translate into the sentiment of "positive" being over Y % and "very positivie" being over Z%.


