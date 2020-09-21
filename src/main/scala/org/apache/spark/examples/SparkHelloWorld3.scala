package org.apache.spark.examples

import org.apache.spark.{SparkConf, SparkContext}

/**
  * This program can be downloaded at:
  *  https://github.com/lw-lin/CoolplaySpark/tree/master/Spark%20%E6%A0%B7%E4%BE%8B%E5%B7%A5%E7%A8%8B
  *
  *  spark-submit --master yarn --class org.apache.spark.examples.SparkHelloWorld --executor-memory 4g --executor-cores 2 --num-executors 4   spark-examples_2.10-1.0-SNAPSHOT.jar hdfs://10.113.9.96/input/datatestout/out4.text
 */
object SparkHelloWorld3 {

  def main(args: Array[String]) {
    val conf = new SparkConf()
    conf.setAppName("SparkHelloWorld")
    conf.setMaster("local[2]")
    conf.set("spark.default.parallelism ","8")
    val sc = new SparkContext(conf)

//    val lines = sc.parallelize(Seq("hello world", "hello tencent"))
//    val lines = sc.parallelize(Seq("hello world", "hello tencent", "hello tencent", "hello tencent", "hello tencent",
//      "hello world", "hello tencent", "hello tencent", "hello tencent", "hello tencent",
//      "hello world", "hello tencent", "hello tencent", "hello tencent", "hello tencent",
//      "hello world", "hello tencent", "hello tencent", "hello tencent", "hello tencent"
//    ))
//    val lines = sc.textFile("hdfs://10.113.9.96/input/datatest/web-Google.txt").

    val lines = sc.textFile("hdfs://10.113.9.96/input/datatest/*")
    //并行度是 6 ，每个执行内核是2，所以有12的task
    val wc = lines.flatMap(_.split(" ")).map(word => (word, 1)).reduceByKey(_ + _)
//    wc.partitions.length
//    wc.glom.collect()

    wc.saveAsTextFile(args(0))

    java.lang.Thread.sleep(10 * 60 * 1000) // 挂住 10 分钟; 这时可以去看 SparkUI: http://localhost:4040

  }
}
