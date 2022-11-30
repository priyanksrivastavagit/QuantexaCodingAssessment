package com.quantexa.flights.report.TotalFlightsPerMonth

import com.quantexa.flights.report.common.outputFileWriter

object TotalFlightsPerMonthCsvWriter {

  def writeOutput(fileName: String, lines: List[OutputTotalFlightsPerMonth]): Unit = {
    val outputData = lines.map(line => s"${line.month.toString}, ${line.numberOfFlights.toString}\n").mkString("")
    val header = "Month, Number of Flights \n"
    outputFileWriter.writeTo(fileName, header, outputData)
  }

}
