package com.quantexa.flights.report.TotalFlightsPerMonth

import org.scalatest._
import com.quantexa.flights.report.common.Flights

class TotalFlightsPerMonthSpec extends FlatSpec with Matchers {

  val ta = new TotalFlightsPerMonth

  "totalFlightsPerMonth" should "get total flights per month" in {

    val data = List(
      Flights(1, 0, "uk", "in", "2022-01-01"),
      Flights(2, 1, "uk", "in", "2022-01-02"),
      Flights(3, 2, "uk", "in", "2022-01-03")
    )

    ta.totalFlightsPerMonth(data) shouldBe List(
      OutputTotalFlightsPerMonth(1, 3)
    )

  }

}
