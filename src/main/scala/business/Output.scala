package business
import java.io.{BufferedWriter, FileWriter}

import au.com.bytecode.opencsv.CSVWriter

import scala.collection.JavaConversions._
/**
  * Cria o output
  * @author Giovanni Silva
  */
object Output {
  val OUTPUT_FILE = "output.csv"

  def write(records: List[Array[String]]):Unit = {
    val out = new BufferedWriter(new FileWriter(OUTPUT_FILE));
    val writer = new CSVWriter(out)
    writer.writeAll(records)
    out.close()
  }

}
