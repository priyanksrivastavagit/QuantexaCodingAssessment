package com.quantexa.flights.report.common

import scala.io.Source

object passengerFileReader {

  def from(fileName: String): List[Passengers] = {
    val passengerFile = Source.fromFile(fileName)
    try {
      // Drop the header row
      val passengerLines = passengerFile.getLines().drop(1)
      passengerLines.map { line =>
        val fields = line.split(',')
        Passengers(fields(0).toInt, fields(1), fields(2))
      }.toList
    } finally {
      passengerFile.close()
    }
  }
}
