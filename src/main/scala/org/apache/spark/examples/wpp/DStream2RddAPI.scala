package org.apache.spark.examples.wpp

import java.text.SimpleDateFormat
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.streaming.{Seconds, StreamingContext, Time}

/**
  *
  */
object DStream2RddAPI {
  def main(args: Array[String]) {
    //1、创建sparkConf
    val sparkConf: SparkConf = new SparkConf()
      .setAppName("DStream2RddAPI")
      .setMaster("local[2]")
    //2、创建sparkContext
    val sc = new SparkContext(sparkConf)

    val ssc = new StreamingContext(sc,Seconds(10))

    val socketDStream: ReceiverInputDStream[String] = ssc.socketTextStream("bigdata.ibeifeng.com",9999)
    //dstream 当中有一些api是没有的（例如：sortbyKey等）
    //将DStream转换成RDD进行操作
    val resultDStream: DStream[((String, String), Int)] = socketDStream.transform((rdd,timestamp) =>{
      val sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss")
      val ts: String =sdf.format(timestamp.milliseconds)

      rdd.flatMap(_.split(" "))
        .filter(word =>word.nonEmpty)
        .map(word =>((word,ts),1))
        .reduceByKey(_ + _)
        //指定按照第二个位置上的数据类型排序，并且倒叙
        .sortBy(t =>t._2,ascending = false)
    })

    resultDStream.print()
    ssc.start()
    ssc.awaitTermination()


  }

}
