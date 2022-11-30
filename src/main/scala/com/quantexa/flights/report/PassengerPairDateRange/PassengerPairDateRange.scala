package com.quantexa.flights.report.PassengerPairDateRange

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.{col, count, max, min}


class PassengerPairDateRange {

  def passengerPairDateRange(df: DataFrame, fromDate: String, toDate: String): DataFrame = {

    val pairedPassengersDateRangeDF = df.alias("df1")
      .where(col("date") >= fromDate && col("date") < toDate)
      .join(df.alias(("df2")),col("df1.passengerId") < col("df2.passengerId") && col("df1.flightId") === col("df2.flightId") && col("df1.date") === col("df2.date") , "inner")
      .groupBy(col("df1.passengerId"), col("df2.passengerId"))
      .agg(count("*").alias("numberOfPairedFlights"), min(col("df1.date")).substr(1,10).alias("From Date"), max(col("df1.date")).substr(1,10).alias("To Date"))
      .where(col("numberOfPairedFlights") > 3)
      .sort(col("df1.passengerId"),col("df2.passengerId").asc)
      .withColumnRenamed("df1.passengerId", "Passenger 1 ID")
      .withColumnRenamed("df2.passengerId", "Passenger 2 ID")

    val pairedPassengersDateRangeFinalDF = pairedPassengersDateRangeDF.toDF("Passenger 1 ID", "Passenger 2 ID", "Number of Flights Together", "From", "To")

    pairedPassengersDateRangeFinalDF



  }

}
