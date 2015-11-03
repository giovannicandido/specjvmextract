package business

/**
  * @author Giovanni Silva
  */
case class SpecJVMResult(configs: Seq[String], result: Result,
                         commandLine: String) {
  /**
    * Transforma um SpecJVMResult em um array de strings, esse array é utilizado para gerar cada linha do CSV
    * @return Um Array string, cada um correspondendo a um campo
    */
  def transformToCSVLine(): Array[String] = {
    val array: Array[String] = Array(result.name, result.iterationTime, result.operations.toString,
      configs.mkString("\n"), commandLine)
    array
  }
}
object SpecJVMResult {
  /**
    * Especifica o a primeira linha do CSV
    * @return
    */
  def cabecalho(): Array[String] = {
    Array("Benchmark","Iteration Time", "Operations","Configs","Command Line")
  }
}
