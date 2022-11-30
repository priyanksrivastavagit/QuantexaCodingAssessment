package com.quantexa.flights.report.TotalNumberOfFlights

import com.quantexa.flights.report.common.{Flights, Passengers}

case class OutputFrequentFlyer(passengerId: Int, numberOfFlights: Int, firstName: String, lastName: String)

class TotalNumberOfFlights {



  def frequentFlyer(passIdList: List[Flights]) = {

    passIdList
      .groupBy(_.passengerId)
      .map(t => (t._1, t._2.length))
      .toList
      .sortBy(t => (-t._2, t._1))
      .take(100)

    //res.foreach( { case (month, total) => println(s"${month}, ${total}")})

  }

  def mergeLists(fList: List[(Int, Int)], pList: List[Passengers]) = {

    //(fList.map(f => (f._1, f._2)) zip pList.map(p => (p.firstName, p.lastName)))
    val fListMap = fList.toMap
    val passList = pList.map(  x => (x.passengerId,(x.firstName,x.lastName))).toList
    val passMap = passList.toMap[Int, (String, String)]
    //fList.flatMap{ case (ka,va) => passMap.get(ka).map(vb => (ka,va, ))}
    val mergedList = fList.flatMap{ case (ka, va) => passMap.get(ka).map(vb => (ka, va, vb._1, vb._2))}.toList
    mergedList



  }

  //def totalNumberOfFlights(mergeLists: Map[(Int, Int), (String, String)]) = {
  def totalNumberOfFlights(flights: List[Flights], pFile: List[Passengers]) = {
    val passengerCount = frequentFlyer(flights)
    val mergeListsCall = mergeLists(passengerCount, pFile)
    //val totalNumberOfFlightsOutPut = totalNumberOfFlights(mergeListsCall)

    mergeListsCall.map {
      case (k, n, f, l) =>
        OutputFrequentFlyer(
          k,
          n,
          f,
          l
        )
    }.toList

  }

  //val passengerCount = frequentFlyer(flights)
  //val mergeListsCall = mergeLists(passengerCount, pFile).toMap
  //val totalNumberOfFlightsOutPut = totalNumberOfFlights(mergeListsCall)
  //totalNumberOfFlights(mergeListsCall)

}
