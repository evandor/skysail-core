package io.skysail.core

import java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY

import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.Paths

// https://github.com/csokol/vraptor-twirl/blob/master/src/main/java/br/com/caelum/vraptor/twirl/DefaultFileChangeWatcher.java
class TwirlFileWatcher(dir: String) {

  val path: Path = Paths.get(dir)
  val watcher = FileSystems.getDefault.newWatchService
  path.register(watcher, ENTRY_MODIFY)

  def work(): Unit = {
    while ( {
      true
    }) try {
      val key = watcher.take
      val dir = key.watchable.asInstanceOf[Nothing]
      val events = key.pollEvents
      import scala.collection.JavaConversions._
      for (watchEvent <- events) {
        val path = watchEvent.context.asInstanceOf[Nothing]
        //System.out.println(dir.resolve(path).toFile.getAbsolutePath)
        //runnable.execute(dir.resolve(path).toFile)
      }
      key.reset
    } catch {
      case e: Exception =>
        throw new RuntimeException(e)
    }
  }
}
