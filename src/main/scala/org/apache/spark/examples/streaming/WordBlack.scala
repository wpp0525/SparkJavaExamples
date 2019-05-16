package org.apache.spark.examples.streaming

import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.dstream.{DStream, ReceiverInputDStream}
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  *  单词黑名单
  *
  *  nc -lk 9999
  *  (@, 3)
  *  (hadoop,32)
  *
  */
object WordBlack {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("WordBlack")

    val sc: SparkContext = new SparkContext(conf)
    val ssc = new StreamingContext(sc, Seconds(2))
    /**
      * 数据的输入
      */
    val dstream: ReceiverInputDStream[String] = ssc.socketTextStream("hadoop04", 9999)

    /**
      * 自己模拟一个黑名单（正常是用mysql，hbase，redis数据库读取出来的
      */
    //直接转化为RDD
    val wordBlackList: RDD[(String, Boolean)] = ssc.sparkContext.parallelize(List("?", "!", "*"))
      .map(param => (param, true))
    /**
      * (?,true)
      * (!,true)
      * (*,true)
      */
    val balckList: Array[(String, Boolean)] = wordBlackList.collect()
    //broadcast广播出去
    val blackListBroadcast: Broadcast[Array[(String, Boolean)]] = ssc.sparkContext.broadcast(balckList)

    /**
      * 数据的处理
      */
    val wordOneDStream: DStream[(String, Int)] = dstream.flatMap(_.split(","))
      .map((_, 1))
    //transform把DStream转换成RDD,需要又返回值，并且类型为RDD
    val wordCountDStream: DStream[(String, Int)] = wordOneDStream.transform(rdd => {
      val filterRDD: RDD[(String, Boolean)] = rdd.sparkContext.parallelize(blackListBroadcast.value)
      val resultRDD: RDD[(String, (Int, Option[Boolean]))] = rdd.leftOuterJoin(filterRDD)

      /**
        * String, (Int, Option[Boolean])
        * String：word
        * Int：1
        * Option：有可能join上也有可能join不上
        *
        * 思路：我们要的是join不上的，说白了要的是Option[Boolean]=None
        * filter:
        * true代表我们要
        */
      resultRDD.filter(tuple => {
        tuple._2._2.isEmpty
      }).map(_._1)
    }).map((_, 1)).reduceByKey(_ + _)

    /**
      * 数据的输出
      */
    wordCountDStream.print()

    ssc.start()
    ssc.awaitTermination()
    ssc.stop()
  }
}
