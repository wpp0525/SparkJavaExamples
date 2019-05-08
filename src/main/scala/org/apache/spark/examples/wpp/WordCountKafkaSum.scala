package org.apache.spark.examples.wpp

import java.text.SimpleDateFormat

import kafka.serializer.StringDecoder
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.dstream.DStream
import org.apache.spark.streaming.{Durations, Seconds, StreamingContext}
import org.apache.spark.streaming.kafka.KafkaUtils

object WordCountKafkaSum {
  def main(args: Array[String]): Unit = {

    val sparkConf = new SparkConf().setAppName("WordCount2").setMaster("local[2]") //至少2个线程，一个DRecive接受监听端口数据，一个计算
//    sparkConf.setLogLevel("WARN")

    val sc = new StreamingContext(sparkConf, Durations.seconds(15));
//    sc.setLogLevel("WARN")
    // 首先要创建一份kafka参数map
    // 我们这里是不需要zookeeper节点的啊,所以我们这里放broker.list
    val kafkaParams = Map[String, String](  
//      "metadata.broker.list" -> "node1:9092,node2:9092,node3:9092"
      "metadata.broker.list" -> "10.201.1.192:9092,10.201.1.205:9092,10.201.1.231:9092"
    )

    // 然后创建一个set,里面放入你要读取的Topic,这个就是我们所说的,它给你做的很好,可以并行读取多个topic
    var topics = Set[String]("wordcount20160423");

    //kafka返回的数据时key/value形式，后面只要对value进行分割就ok了
    val linerdd = KafkaUtils.createDirectStream[String, String, StringDecoder, StringDecoder](  
      sc, kafkaParams, topics)

    val wordrdd = linerdd.flatMap { _._2.split(" ") }
    val regex="""^\d+$""".r
    val resultrdd = wordrdd.filter( regex.findFirstMatchIn(_) != None ).map { x => (x,1 ) }.reduceByKey { _ + _ }

    val resultrdd2 = resultrdd.map(item  => (item._1.toInt, item._2.toInt))
    resultrdd2.print()

    println("计算相关的和")
    val resultRdd3 =  resultrdd2.map(item=> item._1.toInt*item._2.toInt).reduce(_+_)

    resultRdd3.window(Seconds(15),Seconds(20))
    resultRdd3.print()

//    resultRdd3.reduceByWindow((s1,s2)=>{
//      s1 + ":"+s2
//    },Seconds(60),Seconds(10)).print()



//    resultRdd3.saveAsTextFiles("./wpprest")

    sc.start()
    sc.awaitTermination()
    sc.stop()

  }
}