package com.quantexa.flights.report.PassengerPair

import com.quantexa.flights.report.common.outputFileWriterDF
import org.apache.spark.sql.DataFrame

object PassengerPairCsvWriter {

  def writeOutput(fileName: String, longestRunDF: DataFrame): Unit = {

    outputFileWriterDF.writeTo(fileName, longestRunDF)
  }

}
