package com.quantexa.flights.report.LongestRun

import org.apache.spark.sql.SQLContext._
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._
import org.apache.spark.sql.types._
import org.scalatest._


class LongestRunSpec extends FlatSpec with Matchers {

  def getCreateSparkSession: SparkSession = {
    SparkSession.builder
      //.appName("QuantexaFlightsAssignment")
      .master("local[*]")
      .getOrCreate()
  }

  val spark: SparkSession = getCreateSparkSession

  //val sqlContext = new org.apache.spark.sql.SQLContext(spark.sqlContext)
  val sqlContext = spark.sqlContext
  val ta = new ConsecutiveLongestRun

  "longestRun" should "get longest non-UK list of countries visited" in {

    val data = sqlContext.createDataFrame(Seq(
      (1, 1, "uk", "in", "2022-01-02"),
      (1, 0, "in", "au", "2022-01-01"),
      (1, 2, "au", "us", "2022-01-03"),
      (1, 2, "us", "uk", "2022-01-04")
    )).toDF("passengerId", "flightId", "from", "to", "date")

    val country: String = "uk"

    ta.longestRun(data, country).show(1) shouldBe
      sqlContext.createDataFrame(Seq((1, 3))).toDF("passengerId", "longestRun").show(1)


  }

}
