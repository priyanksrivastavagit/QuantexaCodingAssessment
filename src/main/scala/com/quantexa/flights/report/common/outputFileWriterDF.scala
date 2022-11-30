package com.quantexa.flights.report.common

import java.io.{BufferedWriter, File, FileWriter}
import scala.language.implicitConversions
import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

object outputFileWriterDF {

  def writeTo(fileName: String, outputDataDF: DataFrame): Unit = {

    outputDataDF.coalesce(1)
      .write
      .format("csv")
      .option("header", "true")
      .mode("append")
      .save(fileName)

  }

}
