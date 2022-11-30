package com.quantexa.flights.report.LongestRun

//import com.quantexa.flights.report.common.Main._

import org.apache.spark.sql.DataFrame

object LongestRunApp {

  def solve(DF: DataFrame, country: String = "uk", outputDir: String) = {

    val outputFile: String = outputDir + "LongestRun"

    val mergeFromToMethod = new ConsecutiveLongestRun
    val mergeFromTo = mergeFromToMethod.longestRun(DF: DataFrame, country: String)


    LongestRunCsvWriter.writeOutput(outputFile: String, mergeFromTo: DataFrame)

  }
}
