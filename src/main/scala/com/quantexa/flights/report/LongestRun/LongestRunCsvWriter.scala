package com.quantexa.flights.report.LongestRun

import com.quantexa.flights.report.common.outputFileWriterDF

import org.apache.spark.sql.{DataFrame, SparkSession}

object LongestRunCsvWriter {

  def writeOutput(fileName: String, longestRunDF: DataFrame): Unit = {

    outputFileWriterDF.writeTo(fileName, longestRunDF)
  }

}
