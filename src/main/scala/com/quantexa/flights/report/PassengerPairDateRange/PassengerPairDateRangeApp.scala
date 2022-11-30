package com.quantexa.flights.report.PassengerPairDateRange

//import com.quantexa.flights.report.common.Main._

import com.quantexa.flights.report.PassengerPair.{PassengerPair, PassengerPairCsvWriter}
import org.apache.spark.sql.DataFrame

object PassengerPairDateRangeApp {

  def solve(DF: DataFrame, fromDate: String, toDate: String, outputDir: String) = {

    val outputFile: String = outputDir + "PassengerPairDateRange"

    val passengerPairDateRangeMethod = new PassengerPairDateRange
    val passengerPairDateRangeDF = passengerPairDateRangeMethod.passengerPairDateRange(DF: DataFrame, fromDate: String, toDate: String)


    PassengerPairDateRangeCsvWriter.writeOutput(outputFile: String, passengerPairDateRangeDF: DataFrame)

  }
}
