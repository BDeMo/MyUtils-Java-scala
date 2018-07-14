import org.apache.spark.{SparkConf, SparkContext, SparkFiles}

object SparkWordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("hellospark_wordcount")
    val sc = new SparkContext(conf)
    sc.textFile(args(0)).flatMap(_.split(" ")).map((_,1)).reduceByKey(_+_).sortBy(_._2, false).saveAsTextFile(args(1))
    sc.stop()
  }
}