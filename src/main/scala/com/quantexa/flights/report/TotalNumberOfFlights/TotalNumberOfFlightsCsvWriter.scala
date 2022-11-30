package com.quantexa.flights.report.TotalNumberOfFlights

import com.quantexa.flights.report.common.outputFileWriter

object TotalNumberOfFlightsCsvWriter {

  def writeOutput(fileName: String, lines: List[OutputFrequentFlyer]): Unit = {
    val outputData = lines.map(line => s"${line.passengerId.toString}, ${line.numberOfFlights.toString}, ${line.firstName.toString}, ${line.lastName.toString}\n").mkString("")
    val header = "passengerId, numberOfFlights, firstName, lastName \n"
    outputFileWriter.writeTo(fileName, header, outputData)
  }

}
