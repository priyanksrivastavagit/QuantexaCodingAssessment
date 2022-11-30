package com.quantexa.flights.report.PassengerPair

import org.apache.spark.sql.SparkSession
import org.scalatest._
//import com.quantexa.flights.report.PassengerPair


class PassengerPairSpec extends FlatSpec with Matchers {

  def getCreateSparkSession: SparkSession = {
    SparkSession.builder
      //.appName("QuantexaFlightsAssignment")
      .master("local[*]")
      .getOrCreate()
  }

  val spark: SparkSession = getCreateSparkSession

  //val sqlContext = new org.apache.spark.sql.SQLContext(spark.sqlContext)
  val sqlContext = spark.sqlContext
  val ta = new PassengerPair


  "passengerPair" should "get passenger pairs with greater than 3 shared flights" in {

    val data = sqlContext.createDataFrame(Seq(
      (1, 1, "uk", "in", "2022-01-01"),
      (2, 1, "in", "au", "2022-01-01"),
      (1, 2, "au", "us", "2022-01-03"),
      (2, 2, "us", "uk", "2022-01-03"),
      (1, 3, "au", "us", "2022-01-04"),
      (2, 3, "us", "uk", "2022-01-04"),
      (1, 4, "au", "us", "2022-01-05"),
      (2, 4, "us", "uk", "2022-01-05")
    )).toDF("passengerId", "flightId", "from", "to", "date")


    ta.passengerPair(data).show(1) shouldBe
      sqlContext.createDataFrame(Seq((1, 2, 4))).toDF("Passenger 1 ID", "Passenger 2 ID", "Number of Flights Together").show(1)


  }

}
