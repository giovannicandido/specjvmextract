package business

import java.nio.file.{Files, Paths}

/**
 * @author Giovanni Silva
 */
object ExtractTest extends App {
  val FOLDER = "testesjvm"
  // Executa a transformação
  // Verifica se o diretório existe
  if(Files.isDirectory(Paths.get(FOLDER))){
    // Extrai o resultado para cada arquivo ".raw" e transforma para Um Array[String] que será as linhas do resultado
    val linhas: List[Array[String]] = Utils.getAllFilesThatMatchFilenameExtension(FOLDER, ".raw") map { file =>
      Input.extrairResultado(file).transformToCSVLine()
    }
    // Cabeçalho do excel
    val cabecalho = SpecJVMResult.cabecalho()
    val output = cabecalho :: linhas
    Output.write(output)
    println(s"Arquivo ${Output.OUTPUT_FILE} criado")
  }else{
    println(s"Não há diretorio $FOLDER no diretório corrente")
  }

}
