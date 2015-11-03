package business

import java.io.{FilenameFilter, File}

/**
  * @author Giovanni Silva
  */
object Utils {
  /**
    * Retorna todos os arquivos de um diretório que possuem a extensão
    * @param directoryName Local ou nome do diretório
    * @param extension Extensão a ser filtrada, pode ser qualquer final de nome de arquivo
    */
  def getAllFilesThatMatchFilenameExtension(directoryName: String, extension: String): List[File] = {
    val dir = new File(directoryName);
    val files = dir.listFiles(new FilenameFilter() {
      override def accept(dir: File, name: String): Boolean = {
        return name.endsWith(extension);
      }
    });

    files.toList
  }
}
