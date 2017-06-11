package io.skysail.api.metrics

trait Stoppable {
  def stop()
}

trait Markable {
  def mark()
}

sealed trait Metric

case class CounterMetric(cls: AnyRef, identifier: String) extends Metric
case class HistogramMetric(cls: AnyRef, identifier: String) extends Metric
case class MeterMetric(identifier: String) extends Metric

case class TimerMetric(val cls: AnyRef, identifier: String) extends Metric {
  private var stoppables = List[Stoppable]()
  def stop() = stoppables.foreach(_.stop)
  def setStoppables(stoppables: List[Stoppable]) = this.stoppables = stoppables
}

trait TimerDataProvider {
  def getName(): String
  def getCount(): Long
  def getMax(): Double
  def getMean(): Double
  def getMin(): Double
}

trait Metrics {

  def registerTimer(metric: TimerMetric)
  def time(timerMetric: TimerMetric): Stoppable

  def registerMeter(metric: MeterMetric)
  def meter(identifier: MeterMetric)

  def registerCounter(metric: CounterMetric)
  def inc(metric: CounterMetric)
  def dec(metric: CounterMetric)

  def registerHistogram(metric: HistogramMetric)
  def update(metric: HistogramMetric, value: Long)
  def getTimers(): List[TimerDataProvider]

}

trait MetricsCollector {
  def timerFor(cls: AnyRef, identifier: String): TimerMetric
  def markMeter(identifier: String): Unit
}

class NoOpMetricsCollector extends MetricsCollector {
  override def timerFor(cls: AnyRef, identifier: String) = new TimerMetric(this.getClass(), "noop");
  override def markMeter(identifier: String) = {}
}