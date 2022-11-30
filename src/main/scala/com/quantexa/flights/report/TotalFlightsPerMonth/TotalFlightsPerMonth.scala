package com.quantexa.flights.report.TotalFlightsPerMonth

import com.quantexa.flights.report.common.Flights

case class OutputTotalFlightsPerMonth(month: Int, numberOfFlights: Int)

class TotalFlightsPerMonth {

  def totalFlightsPerMonth(flights: List[Flights]): List[OutputTotalFlightsPerMonth] = {

      flights
        .map(line => (line.date.split('-')(1), line.flightId))
        .distinct
        .groupBy(_._1)
        .map(e => (e._1.toInt, e._2.size))
        .toSeq
        .sortBy(_._1)
        .toList
        .sortBy(_._1)
        .map( line => OutputTotalFlightsPerMonth(line._1, line._2))

  }

}
