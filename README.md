# Hope v. Reality in Financial Reporting
A project by Cryrus Cheng, Tim Culpan, and Angela Wen

*NOTE:* this project will take 1min 30 sec per PDF to run, depending on your CPU & Memory. Currently the project works on statements from Alibaba's September 2014 and December 2014 quarters. If you prefer to run it on more files, you may uncomment lines 52 - 55 in Runner class (but computing time be much longer). 
NOTE: If you've already run the code, please make sure that the txt/ folder is empty

You may view our project design and CRCs at: [project-design.md](https://github.com/UPenn-CIT599/final-project-financial-reporting-analysis/blob/master/project-design.md)

You may view our project summary (RTF format) at: [summary.txt](https://github.com/UPenn-CIT599/final-project-financial-reporting-analysis/blob/master/summary.txt)

This project requires **three libraries and one external package**:
- Libraries: PDFBox, Sentiment Analysis, Apache CommonsCSV
- External package: Stanford NLP for Sentiment Analysis

Once you have set up the libraries and package (see instructions below), you may click "Run" on your Eclipse IDE or your IDE of choice. You will be able to view the dataset output under the folder "dataset" afterwards. 

## Setup - Java project FinancialReportAnalysis
- Open an IDE (Instructions below for Eclipse)
- Select the final-project-financial-reporting-analysis folder as the workspace 
- Click Launch
- You will have the FinancialReportAnalysis project available in the Package Explorer.

## Setup - PDFBox 
- Download the PDFBox .jar file here: https://pdfbox.apache.org/download.cgi 
- It is the file labeled "pdfbox-app-2.0.15.jar"
- Save it under the the Folder "FinancialReportAnalysis\lib\"
- In Eclipse, RIGHT CLICK on the Java Project "FinancialReportAnalysis"
- Click REFRESH.
- The library "pdfbox-app-2.0.15.jar" should appear under "Referenced Libraries"
- If it does not, right click on Java Project "FinancialReportAnalysis" -> Build Path -> Configure Build Path
- Under "Libraries" tab, click "Add JARs", select "FinancialReportAnalysis/lib" and select "pdfbox-app-2.0.15.jar"
-Click OK and Apply to Close.

## Setup - Sentiment Analysis 
- Download the Sentiment Analysis project developed by Ruthwik from https://github.com/Ruthwik/Sentiment-Analysis
- On the Eclipse IDE, Click Files>Open Projects from File System>Directory
- Point to the "SentimentAnalysis" Folder
- Select "SentimentAnalysis" in the Folder list
- Click FINISH
- The Java project SentimentAnalysis should appear in the Package Explorer window (note, you may need to scroll down your Eclipse Project Explorer window to find it)

### Setup - Stanford NLP for Sentiment Analysis
- Download the Stanford CoreNLP v3.9.2 from https://stanfordnlp.github.io/CoreNLP/download.html
- Once download has completed, save stanford-corenlp-3.9.2.jar and stanford-corenlp-3.9.2-models.jar within the the Folder "SentimentAnalysis\lib\"
- In Eclipse IDE, right click on the Java Project "SentimentAnalysis". Click REFRESH.
- Right click on the Java Project "SentimentAnalysis". 
- Click BuildPath>Configure BuildPath>Libraries.
- DELETE the .jar of stanford-corenlp-3.8.0.jar and stanford-corenlp-3.8.0-models.jar (these are OLD versions, we want the new ones .. next step)
- Click Add JAR, point to the downloaded stanford-corenlp-3.9.2.jar and stanford-corenlp-3.9.2-models.jar within the Folder "SentimentAnalysis\lib\"

## Setup - Import Java Project SentimentAnalysis into FinancialReportAnalysis
- Right click on Java Project FinancialReportAnalysis
- Click Buildpath>Configure Buildpath>
	- on the tab "Projects" click > Add
- Select Java Project SentimentAnalysis
- Apply and Close

## Setup - Apache CommonsCSV 
- Go to the Download Apache Commons CSV page, here:
http://commons.apache.org/proper/commons-csv/download_csv.cgi
- Look for and DOWNLOAD this file (note, it will automatically download from your nearest mirror site): commons-csv-1.6-bin.zip
- UNZIP that file once it's downloaded (we recommend unzipping to a folder next to where you saved the above libraries)
- COPY the file commons-csv-1.6.jar, go to Eclipse, and PASTE it within the folder "FinancialReportAnalysis\lib\"
- Click REFRESH
- in Eclipse, right-click on the Java Project "FinancialReportAnalysis" go to:
	- Build Path > Configure BuildPath > click tab "Libraries"
	-if commons-csv-1.6.jar is not already there:
		-Add JARs> and look for it in "FinancialReportAnalysis/lib" 
- Apply and Close
- In Eclipse - click REFRESH
