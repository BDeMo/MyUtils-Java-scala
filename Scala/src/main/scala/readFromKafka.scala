import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.kafka.KafkaUtils


object ReadFromKafka {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("ReadFromKafka").setMaster("spark://hdp-node-01:7077")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(10))

    val zkQuorum = "hdp-node-01:2181,hdp-node-02:2181,hdp-node-03:2181,hdp-node-04:2181"
    var group :String = ""
    if(args.length >= 1){
      group += args(0)
    }else{
      group += "test-group"
    }
    val topics = "kafkatest,test"
    val numThread = 1
    val topicpMap = topics.split(",").map((_,numThread.toInt)).toMap   //这个是有可能有好几个topic同时提供数据，那么我们要把它用空格分割开，然后映射成(topic,2),再转换成map集合
    ssc.checkpoint("checkpoint")

    val lines: DStream[String] = KafkaUtils.createStream(ssc,zkQuorum,group,
      Map[String, Int]("kafkatest"->0,"kafkatest"->1),StorageLevel.MEMORY_ONLY).map(_._2)    //创建流
//    val kafkaStream = KafkaUtils.createStream(
//      ssc,
//      "hxf:2181,cfg:2181,jqs:2181,jxf:2181,sxtb:2181", // Kafka集群使用的zookeeper
//      "launcher-streaming", // 该消费者使用的group.id
//      Map[String, Int]("launcher_click" -> 0, "launcher_click" -> 1), // 日志在Kafka中的topic及其分区
//      StorageLevel.MEMORY_AND_DISK_SER).map(_._2) // 获取日志内容
//
//    kafkaStream.foreachRDD((rdd: RDD[String], time: Time) => {
//      val result = rdd.map(log => parseLog(log)) // 分析处理原始日志
//        .filter(t => StringUtils.isNotBlank(t._1) && StringUtils.isNotBlank(t._2))
//      // 存入hdfs
//      result.saveAsHadoopFile(HDFS_DIR, classOf[String], classOf[String], classOf[LauncherMultipleTextOutputFormat[String, String]])
//    })
    println("------------------startingMainBusiness-------------\n")
    lines.foreachRDD(println(_));
    println("------------------"+lines.print()+"-------------\n")
    println("------------------"+lines.map(_.split(" "))+"-------------\n")
    println("------------------"+lines+"-------------\n")

    ssc.start()
    // 等待实时流
    ssc.awaitTermination()
  }
}
