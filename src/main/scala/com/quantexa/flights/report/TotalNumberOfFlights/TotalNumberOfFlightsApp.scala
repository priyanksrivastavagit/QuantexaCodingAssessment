package com.quantexa.flights.report.TotalNumberOfFlights

import com.quantexa.flights.report.common.{flightsFileReader,passengerFileReader}

import java.util.Date
import java.text.SimpleDateFormat

object TotalNumberOfFlightsApp {

  //def main(args: Array[String]): Unit = {

    def solve(flightFile: String, passengersFile: String) = {

      val DATE_FORMAT = "YYYYMMddHHmmss"
      val currDate = new Date()
      val formatterDate = new SimpleDateFormat(DATE_FORMAT)
      val dateStr = formatterDate.format(currDate)


      val flightsFilePath = flightFile
      val passengerFilePath = passengersFile

      val outputFile: String = s"src/main/outputSolutions/TotalNumberOfFlights/frequentFlights_${dateStr}.csv"

      val flights = flightsFileReader.from(flightsFilePath)
      val passengers = passengerFileReader.from(passengerFilePath)

      val res = new TotalNumberOfFlights
      val totals = res.totalNumberOfFlights(flights, passengers)

      TotalNumberOfFlightsCsvWriter.writeOutput(outputFile, totals)
  }
}
