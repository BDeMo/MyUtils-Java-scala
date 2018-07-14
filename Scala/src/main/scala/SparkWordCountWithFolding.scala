import org.apache.spark.{SparkConf, SparkContext}

object SparkWordCountWithFolding {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("hellospark_wordcount")
    val sc = new SparkContext(conf)
    sc.textFile(args(0)).flatMap(_.split(" ")).map((_,1)).foldByKey(0)(_+_).sortBy(_._2, false).saveAsTextFile(args(1))
    sc.stop()
  }
}
