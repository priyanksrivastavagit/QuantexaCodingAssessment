package com.quantexa.flights.report.TotalNumberOfFlights

import com.quantexa.flights.report.common.Flights
import com.quantexa.flights.report.common.Passengers
import org.scalatest._

class TotalNumberOfFlightsSpec extends FlatSpec with Matchers {

  val ta = new TotalNumberOfFlights

  "totalNumberOfFlights" should "get frequent flyer with names" in {

    val flightData = List(
      Flights(1, 0, "uk", "in", "2022-01-01"),
      Flights(1, 2, "uk", "in", "2022-01-03")
    )

    val passengerData = List(
      Passengers(1, "A", "B")
    )

    ta.totalNumberOfFlights(flightData, passengerData) shouldBe List(
      OutputFrequentFlyer(1, 2, "A", "B")
    )

  }

}
