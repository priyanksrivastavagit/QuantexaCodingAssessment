package com.quantexa.flights.report.TotalFlightsPerMonth

import com.quantexa.flights.report.common.flightsFileReader

import java.text.SimpleDateFormat
import java.util.Date

object TotalFlightsPerMonthApp {

  def solve(flightFile: String): Unit = {

    val DATE_FORMAT = "YYYYMMddHHmmss"
    val currDate = new Date()
    val formatterDate = new SimpleDateFormat(DATE_FORMAT)
    val dateStr = formatterDate.format(currDate)

    val flightsFilePath = flightFile
    //val passengerFilePath = args(1)

    val outputFile: String = s"src/main/outputSolutions/TotalFlightsPerMonth/totalFlightsPerMonth_${dateStr}.csv"

    val flights = flightsFileReader.from(flightsFilePath)
    //val passengers = passengerFileReader.from(passengerFilePath)

    val res = new TotalFlightsPerMonth
    val totals = res.totalFlightsPerMonth(flights)

    TotalFlightsPerMonthCsvWriter.writeOutput(outputFile, totals)
  }
}
