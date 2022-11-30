package com.quantexa.flights.report.PassengerPair

//import com.quantexa.flights.report.common.Main._

import org.apache.spark.sql.DataFrame

object PassengerPairApp {

  def solve(DF: DataFrame, outputDir: String) = {

    val outputFile: String = outputDir + "PassengerPair"

    val passengerPairMethod = new PassengerPair
    val passengerPairDF = passengerPairMethod.passengerPair(DF: DataFrame)


    PassengerPairCsvWriter.writeOutput(outputFile: String, passengerPairDF: DataFrame)

  }
}
