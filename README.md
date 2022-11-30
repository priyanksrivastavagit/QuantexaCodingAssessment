# QuantexaCodingAssessment - Flight Data Exploration

This repo is created for the coding assessment - performing data exploration on 2 csv files(flighData.csv & passengers.csv). Included in the rpo is the questionnaire document highlighting the questions and suggestions around the coding style.

Please note, the questions are answered in a mix format i.e. 
--> Question 1 & Question 2: SCALA ONLY code
--> Question 3 & Question 4: SPARK-SCALA code

## Directory Structure:


```
QuantexaCodingAssessment
|
|--src
   |
   |--main
   |   |
   |   |--outputSolutions
   |   |       |
   |   |       |--LongestRun
   |   |       |       part-xxxx-xxxx.csv
   |   |       |--PassengerPair
   |   |       |       part-xxxx-xxxx.csv
   |   |       |--PassengerPairDateRange
   |   |       |       part-xxxx-xxxx.csv
   |   |       |--TotalFlightsPerMonth
   |   |       |       part-xxxx-xxxx.csv
   |   |       |--TotalNumberOfFlights
   |   |              part-xxxx-xxxx.csv
   |   |
   |   |--resource
   |   |      flightData.csv
   |   |      passengers.csv
   |   |
   |   |--scala
   |       |--com
   |          |--quantexa
   |             |--flights
   |                  |--report      
   |                          |--common  
   |                          |     Flights.scala
   |                          |     flightsFileReader.scala
   |                          |     Main.scala
   |                          |     outputFileWriter.scala
   |                          |     outputFileWriterDF.scala
   |                          |     passengerFileReader.scala
   |                          |     Passengers.scala
   |                          |   
   |                          |--LongestRun  
   |                          |     LongestRun.scala
   |                          |     LongestRunApp.scala
   |                          |     LongestRunCsvWriter.scala
   |                          |
   |                          |--PassengerPair  
   |                          |     PassengerPair.scala
   |                          |     PassengerPairApp.scala
   |                          |     PassengerPairCsvWriter.scala
   |                          |
   |                          |--PassengerPairDateRange
   |                          |     PassengerPairDateRange.scala
   |                          |     PassengerPairDateRangeApp.scala
   |                          |     PassengerPairDateRangeCsvWriter.scala
   |                          |
   |                          |--TotalFlightsPerMonth  
   |                          |     TotalFlightsPerMonth.scala
   |                          |     TotalFlightsPerMonthApp.scala
   |                          |     TotalFlightsPerMonthCsvWriter.scala
   |                          |
   |                          |--TotalNumberOfFlights  
   |                          |     TotalNumberOfFlights.scala
   |                          |     TotalNumberOfFlightsApp.scala
   |                          |     TotalNumberOfFlightsCsvWriter.scala
   |       
   |--test
        |
        |--scala
              |
              |--com
                  |
                  |--quantexa
                        |
                        |--report
                              |
                              |--common
                              |
                              |--LongestRun
                              |       LongestRunspec.scala
                              |
                              |--PassengerPair
                              |       PassengerPairSpec.scala
                              |
                              |--PassengerPairDateRange
                              |       PassengerPairDateRangeSpec.scala
                              |
                              |--TotalFlightsPerMonth
                              |       TotalFlightsPerMonthSpec.scala
                              |
                              |--TotalNumberOfFlights
                              |       TotalNumberOfFlightsSpec.scala 
```
                
## Code Overview:

In the aforementioned tree, under "/src/main/scala/com/quantexa/flights/report/common", 
        1. Main.scala(Spark-Scala) is the executing script which will execute the code for all the questions sequentially. 
        2. Flights - Creates a case class to store the flights data
        3. Passengers - Creates a case class to store the passengers data
        4. flightsFileReader - Reads the flights csv data and stores the data in the case class
        5. passengersFileReader - Reads the passengers csv data and stores the data in the case class
        6. outFileWriter - This file writes the result set from a List[Case Class] into a output csv file (Question - 1,2)
        7. outFileWriterDF - This file writes the result set from a dataframe into a output csv file(Question - 3,4,4.1)

Inside the report directory, there's a folder for each problem statement with the scala code. 
Each folder contain 3 scala files --
        1. The file suffixed with "App" is called by the main function and the "App" file executes the other 2 files. 
        2. The scala file suffixed with "CsvWriter" writes the output of the problem statement in a csv file and stores the data under "/src/main/outputSolutions/<ProblemStatementFolder>/
        3. The file with no prefix & suffix is the one with the main logic to solve the problems.
        
#### _SCALA ONLY_
Question - 1: This problem was solved using basic scala functional programming.
Question - 2: This problem was solved using basic scala functional programming. 

#### _SPARK-SCALA_
Question - 3: This problem was solved using Spark-Scala programming using dataframes.The solution is provided based on the assumption that we needed to report the complete list of countries and NOT only the distinct list of countries.
Question - 4: This problem was solved using Spark-Scala programming using dataframes.
Question - 4.1: This problem was solved using Spark-Scala programming using dataframes. This is an extension of "Question - 4" with date range parameters added.


## Test:

The test code is placed under "src/test/scala/com.quantexa/flights/report/<ProblemStatementFolder>/. Due to time constraint, I have covered basic positive test cases for all the problems using scalatest(https://www.scalatest.org/).



## Assumptions:

        1. Passengers fly only once in a given day  as the flight date do not store any timestamp value for intra-day travel.
        2. The only mode of travel is flight i.e. once the data is arranged by date for a passenger, the preceding "to" value should be in the "from" column for the next record.
        
        
## Experience and self-reflection:

Coming from a ETL/SQL/database background, deep-dive into a completely new world of programming language is certainly challenging and interesting. I have been exposed to some functional programming using Python, but to solve the problems using scala was both interesting and challenging. Even though all the problems are solved and yielding the desirec outcome, these may be fine tuned more in terms of time ans space complexity.

