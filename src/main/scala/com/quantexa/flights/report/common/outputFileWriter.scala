package com.quantexa.flights.report.common

import java.io.{BufferedWriter, File, FileWriter}

object outputFileWriter {

  def writeTo(fileName: String, header: String, outputData: String): Unit = {
    val file = new File(fileName)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(header)
    for (line <- outputData) {
      bw.write(line)
    }
    bw.close()
  }

}
