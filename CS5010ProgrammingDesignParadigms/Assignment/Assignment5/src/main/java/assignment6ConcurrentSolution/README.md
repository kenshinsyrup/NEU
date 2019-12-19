### Readme

Accept 4 args, 1st is courses.csv, 2nd is studentVle.csv, 3rd is assessments.csv, 4th is threshold. For convience, the 3 csv files should in the same level with src folder as said in course.
 
Run main() and will output files in the src folder level.

The SummaryData class is combined with one course, it has Map to store all dates->clicks and dates->assessmentType. We use a concurrentmap to keep and update courseID->SummaryData and at last we will output the SummaryData to csv.

The Producer class and its subclass help us read given csv and put data into BlockQqueue shared with consumers.

The Consumer class help us read data from BlockQueue and process them and sotre into SummaryData in concurrentmap.

The CSVProcessor class is the driver class, it controls the whole stream.
