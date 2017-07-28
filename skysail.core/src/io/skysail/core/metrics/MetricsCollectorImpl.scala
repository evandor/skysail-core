package io.skysail.core.metrics

import io.skysail.api.metrics._
import org.osgi.service.component.annotations._
import kamon.Kamon
import org.slf4j.LoggerFactory

@Component(immediate = true)
class MetricsCollectorImpl extends MetricsCollector {
  
  private val log = LoggerFactory.getLogger(this.getClass)

  private val metricsImpls = scala.collection.mutable.ListBuffer[Metrics]()

  private val timers = scala.collection.mutable.ListBuffer[TimerMetric]()
  private val meters = scala.collection.mutable.ListBuffer[MeterMetric]()

//  log info s"starting Kamon..."
//  Kamon.start()
//
//  val someHistogram = Kamon.metrics.histogram("some-histogram")
//  val someCounter = Kamon.metrics.counter("some-counter")
//
//  someHistogram.record(42)
//  someHistogram.record(50)
//  someCounter.increment()

  @Reference(policy = ReferencePolicy.DYNAMIC, cardinality = ReferenceCardinality.MULTIPLE)
  def addMetricsImplementation(mi: Metrics): Unit = {
    metricsImpls += mi
  }

  def removeMetricsImplementation(mi: Metrics): Unit = {
    metricsImpls -= mi
  }

  def timerFor(cls: AnyRef, identifier: String): TimerMetric = {
    val metric = new TimerMetric(cls, identifier)
    var indexOf = timers.indexOf(metric)
    if (indexOf < 0) {
      timers += metric
      metricsImpls.foreach(m => m.registerTimer(metric))
    }

    indexOf = timers.indexOf(metric)
    val timerMetric = timers(indexOf)
    metric.setStoppables(metricsImpls.map(m => m.time(timerMetric)).toList)
    metric
  }

  def markMeter(identifier: String): Unit = {
    val metric = new MeterMetric(identifier)
    var indexOf = meters.indexOf(metric)
    if (indexOf < 0) {
      meters += metric
      metricsImpls.foreach(m => m.registerMeter(metric))
    }
    indexOf = meters.indexOf(metric)
    val meterMetric = meters(indexOf)
    metricsImpls.foreach(m => m.meter(meterMetric))
  }

}