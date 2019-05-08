# Hope v. Reality in Financial Reporting
A project by Cryrus Cheng, Tim Culpan, and Angela Wen

## Setup - Java project FinancialReportAnalysis
- Open an IDE (Instructions below for Eclipse)
- Select the final-project-financial-reporting-analysis folder as the workspace 
- Click Launch
- You will have the FinancialReportAnalysis project available in the Package Explorer.

## Setup - PDFBox 
- Download the PDFBox .jar file here: https://pdfbox.apache.org/download.cgi 
- It is the file labeled "pdfbox-app-2.0.15.jar"
- Save it under the the Folder "FinancialReportAnalysis\lib\"
- On the Eclipse IDE, right click on the Java Project "FinancialReportAnalysis"
- Click REFRESH.
- The library "pdfbox-app-2.0.15.jar" should appear under "Referenced Libraries"
- If it does not, right click on Java Project "FinancialReportAnalysis" -> Build Path -> Configure Build Path
- Under "Libraries" tab, click "Add JARs", select "FinancialReportAnalysis/lib" and select "pdfbox-app-2.0.15.jar". Click OK and Apply to Close.

## Setup - Sentiment Analysis 
- Download the Sentiment Analysis project developed by Ruthwik from https://github.com/Ruthwik/Sentiment-Analysis
- On the Eclipse IDE, Click Files>Open Projects from File System>Directory
- Point to the "SentimentAnalysis" Folder
- Select "SentimentAnalysis" in the Folder list
- Click FINISH
- The Java project SentimentAnalysis should appear in the Package Explorer window

### Setup - Stanford NLP for Sentiment Analysis
- Download the Stanford CoreNLP v3.9.2 from https://stanfordnlp.github.io/CoreNLP/download.html
- Once download has completed, save stanford-corenlp-3.9.2.jar and stanford-corenlp-3.9.2-models.jar under the the Folder "SentimentAnalysis\lib\"
- On the Eclipse IDE, right click on the Java Project "SentimentAnalysis". Click REFRESH.
- Right click on the Java Project "SentimentAnalysis". 
- Click BuildPath>Configure BuildPath>Libraries.
- Delete the .jar of stanford-corenlp-3.8.0.jar and stanford-corenlp-3.8.0-models.jar
- Click Add JAR, point to the downloaded stanford-corenlp-3.9.2.jar and stanford-corenlp-3.9.2-models.jar under Folder "SentimentAnalysis\lib\"

## Setup - Import Java Project SentimentAnalysis into FinancialReportAnalysis
- Right click on Java Project FinancialReportAnalysis
- Click Buildpath>Configure Buildpath>Projects>Add
- Select Java Project SentimentAnalysis
- Click Apply and Close

## Setup - SuperCSV 
- Download the super-csv-distribution-2.4.0-bin.zip file here: https://github.com/super-csv/super-csv/releases
- Save the file "super-csv-2.4.0.jar" under the the Folder "FinancialReportAnalysis\lib\"
- On the Eclipse IDE, right click on the Java Project "FinancialReportAnalysis"
- Click REFRESH.
- The library "super-csv-2.4.0.jar" should appear under "Referenced Libraries"
- If it does not, right click on Java Project "FinancialReportAnalysis" -> Build Path -> Configure Build Path
- Under "Libraries" tab, click "Add JARs", select "FinancialReportAnalysis/lib" and select "super-csv-2.4.0.jar". Click OK and Apply to Close.
