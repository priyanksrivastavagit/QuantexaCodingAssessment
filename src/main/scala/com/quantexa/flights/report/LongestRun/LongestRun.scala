package com.quantexa.flights.report.LongestRun

//import com.quantexa.flights.report.common.Main._

import org.apache.spark.sql.expressions.{UserDefinedFunction, Window}
import org.apache.spark.sql.functions._
import org.apache.spark.sql.{DataFrame, Row}

//import spark.implicits._

//case class LongestRun(passengerId: Int, longestRun: Int)

class ConsecutiveLongestRun extends Serializable{

  def longestRun(DF: DataFrame, country: String): DataFrame = {

      val generateFlattenFromToDF = generateFlattenFromTo(DF)
      val res = longestRunFromFlightPath(generateFlattenFromToDF, country)
      res
      //val finalDF = res.map( r => LongestRun(r(0).asInstanceOf[Int],r(1).asInstanceOf[Int]))

  }

  def generateFlattenFromTo(DF: DataFrame): DataFrame = {

    val window = Window.partitionBy("passengerId").orderBy(asc("date"))

    DF.withColumn("flightPath", collect_list(struct("from", "to"))
      .over(window))
      .groupBy("passengerId")
      .agg(max(col("flightPath")) as "flightPath")

  }

  def findLongestRunOfFlightPathUDF: UserDefinedFunction = udf((flightPath: Seq[Row], country: String) => {

    FindLongestRunOfFlightPath(flightPath, country)

  })

  def FindLongestRunOfFlightPath(flightPath: Seq[Row], country: String): Int = {
    // If the source (from) country of the first flight = excluded country, then currentRun starts at 0
    var currentRun: Int = if (flightPath.head(0) == country) 0 else 1
    var longestRun: Int = currentRun

    for (flight <- flightPath) {
      currentRun = if (flight(1) == country) 0 else currentRun + 1
      longestRun = if (currentRun > longestRun) currentRun else longestRun
    }

    longestRun
  }

  def longestRunFromFlightPath(DF: DataFrame, country: String): DataFrame = {
    DF.withColumn("longestRun", findLongestRunOfFlightPathUDF(col("flightPath"), lit(country)))
      .select(col("passengerId"), col("longestRun")).sort(col("longestRun").desc)
  }

}
