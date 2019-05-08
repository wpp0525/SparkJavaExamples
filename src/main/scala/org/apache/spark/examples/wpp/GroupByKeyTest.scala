/**
 * Copyright (C) 2015 Baifendian Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.spark.examples.wpp

import org.apache.spark.{SparkConf, SparkContext}

/***
  *
  * 为了测试 spark 内部原理写的一个程序，读者可以忽略之
  * spark-submit --master yarn --class org.apache.spark.examples.SparkHelloWorld --executor-memory 4g --executor-cores 2 --num-executors 4   spark-examples_2.10-1.0-SNAPSHOT.jar hdfs://10.113.9.96/input/datatestout/out4.text
  *
  */

object GroupByKeyTest {

  def main(args: Array[String]): Unit = {


    val sparkConf = new SparkConf().setAppName("WordCountTest").setMaster("local")
    val sc = new SparkContext(sparkConf)

    val data = sc.makeRDD(List("pandas","numpy","pip","pip","pip"))
// makeRDD 和 parallelize 是等价的。
//    val data = sc.parallelize(List("pandas","numpy","pip","pip","pip"))
    //mapToPair
    val dataPair = data.map((_,1))
    //groupByKey
    val result3 = dataPair.groupByKey()
    //此时返回的是RDD:String,Iterable<Integer>,下面的代码对  x._2进行一个累加
    val result4 = result3.map(x=>(x._1,x._2.sum))

    result4.foreach(println)

    sc.stop()
  }
}