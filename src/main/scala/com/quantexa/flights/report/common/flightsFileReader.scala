package com.quantexa.flights.report.common

import scala.io.Source

object flightsFileReader {

  def from(fileName: String): List[Flights] = {
    val flightsFile = Source.fromFile(fileName)
    try {
      // Drop the header row
      val flightsLines = flightsFile.getLines().drop(1)
      flightsLines.map { line =>
        val fields = line.split(',')
        Flights(fields(0).toInt, fields(1).toInt, fields(2), fields(3), fields(4))
      }.toList
    } finally {
      flightsFile.close()
    }
  }
}
