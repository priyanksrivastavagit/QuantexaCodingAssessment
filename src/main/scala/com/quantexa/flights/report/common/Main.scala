package com.quantexa.flights.report.common

import com.quantexa.flights.report.LongestRun._
import com.quantexa.flights.report.PassengerPair._
import com.quantexa.flights.report.PassengerPairDateRange._
import com.quantexa.flights.report.TotalFlightsPerMonth._
import com.quantexa.flights.report.TotalNumberOfFlights._
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, SparkSession}

import scala.language.implicitConversions

object Main {

  def main(args:Array[String]): Unit = {


    def getCreateSparkSession: SparkSession = {
      SparkSession.builder
        .appName("QuantexaFlightsAssignment")
        .master("local[*]")
        .getOrCreate()
    }

    def processSourceCSV(spark: SparkSession, fileName: String): DataFrame = {

      spark.read
        .option("header", "true")
        .option("inferSchema", "true")
        .format("CSV")
        .load(fileName)

    }

    val spark: SparkSession = getCreateSparkSession

    import spark.implicits._

    val dataDir: String = "src/main/resource/"
    val outputDir: String = "src/main/outputSolutions/"
    val country: String = "uk"

    val flightData: String = dataDir + "flightData.csv"
    val passengersData: String = dataDir + "passengers.csv"

    val flightDataDF: DataFrame = processSourceCSV(spark, flightData)
    val passengersDataDF: DataFrame = processSourceCSV(spark, passengersData)

    // Question - 1: Find the total number of flights for each month(SCALA ONLY)
    val totalFlightsPerMonth = TotalFlightsPerMonthApp.solve(flightData: String)

    // Question - 2: Most Frequent Flyer(SCALA ONLY)
    val totalNumberOfFlights = TotalNumberOfFlightsApp.solve(flightData: String, passengersData: String)

    // Question - 3: Find Longest Consecutive Runs(SPARK-SCALA)
    val longestRun = LongestRunApp.solve(flightDataDF: DataFrame, country: String, outputDir: String)
    //longestRun


    // Question - 4: FFind the passengers who have been on more than 3 flights together(SPARK-SCALA)
    val passengerPair = PassengerPairApp.solve(flightDataDF: DataFrame, outputDir: String)

    // Question - 4.1: FFind the passengers who have been on more than 3 flights together in a given date range(SPARK-SCALA)
    val fromDate = "1900-11-01"
    val toDate = "2022-12-01"
    println(s"From Date: ${fromDate}")
    println(s"To Date: ${toDate}")
    val passengerPairDateRange = PassengerPairDateRangeApp.solve(flightDataDF: DataFrame, fromDate: String, toDate: String, outputDir: String)



  }
}
