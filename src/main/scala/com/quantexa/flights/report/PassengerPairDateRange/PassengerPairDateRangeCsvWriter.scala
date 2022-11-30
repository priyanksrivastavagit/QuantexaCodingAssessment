package com.quantexa.flights.report.PassengerPairDateRange

import com.quantexa.flights.report.common.outputFileWriterDF
import org.apache.spark.sql.DataFrame

object PassengerPairDateRangeCsvWriter {

  def writeOutput(fileName: String, longestRunDF: DataFrame): Unit = {

    outputFileWriterDF.writeTo(fileName, longestRunDF)
  }

}
