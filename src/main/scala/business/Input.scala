package business

import java.io.File

import scala.xml.XML

/**
  * @author Giovanni Silva
  */
object Input {
  /**
    * Extrai o arquivo XML para o formato definido
    */
  def extrairResultado(file: File): SpecJVMResult = {
    // Carregando o arquivo
    val test = XML.loadFile(file)
    // (test \ "configs") retorna NodeSeq
    // Transforma em Seq[String]
    val configs = (test \ "configs" \ "config").map(_.text.trim)

    // Busca por benchmak-results, exclui os com nome "check"
    val xmlResults = (test \ "benchmark-results" \ "benchmark-result").filter(!_.attribute("name").exists(_.text == "check"))
    // Transforma em Seq[Result]
    val results = xmlResults.map(xmlResult => {
      // Nome do resultado
      val name = xmlResult.attribute("name")
        .map(_.text.trim).getOrElse("")
      // Iteration Time
      val iterationTime = xmlResult.attribute("iterationTime")
        .map(_.text.trim).getOrElse("")
      // Operations
      val operations:Double = (xmlResult \ "iterations" \ "iteration-result")
        .head.attribute("operations").map(_.text.toDouble).getOrElse(0.0)

      val threads: Int = try {
        val config = configs.filter(_.contains("specjvm.benchmark.threads"))
        val thread = config.head.split("=")(1)
        Integer.parseInt(thread)
      }catch {
        case ex: Exception =>
          println(ex.getMessage)
          0
      }
      // Retorna Result
      Result(name, operations, iterationTime, threads)
    })

    // Extrai commandLine e filtra deixando apenas o que começa com -XX
    val commandLine = (test \ "jvm-info" \ "spec.jvm2008.report.jvm.command.line")
      .head.text.split(" ").filter(_.startsWith("-XX")).mkString(" ")
    // Retorna SpecJVMResult Usa apenas o primeiro resultado, já que cada arquivo só tem um resultado
    SpecJVMResult(configs, results.head, commandLine)
  }
}
