/**
 * Copyright (C) 2015 Baifendian Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.spark.examples

import org.apache.hadoop.fs.Path
import org.apache.spark.{SparkConf, SparkContext}

object LocalFileTest {
  def main(args: Array[String]): Unit = {
//    if (args.length < 1) {
//      System.err.println("Usage: <file>")
//      System.exit(1)
//    }

    val sparkConf = new SparkConf().setAppName("LocalFileTest")
    sparkConf.setMaster("local[*]")
    val sc = new SparkContext(sparkConf)

    val hadoopConf = sc.hadoopConfiguration
    val hdfs = org.apache.hadoop.fs.FileSystem.get(hadoopConf)

    // 不建议采用这种方案，本地文件需要在集群上的所有节点存在，一般是比较难做到的
//    val inputfile = sc.textFile(s"file://${args(0)}") // for example: "/etc/sysconfig/network"

//    val inputfile = sc.textFile(s"file:///E://java//SparkDemoNew//pom.xml") // for example: 本地文件

    val inputfile = sc.textFile(s"hdfs://crm-master2:8020/input/datatest/web-Google.txt") // for example: "/etc/sysconfig/network"

    println("count_line_ " +inputfile.count())
//    inputfile.collect.foreach(println)

//    inputfile.collect.foreach(println)
   val numOutputFiles = 128
//   val counts = inputfile.flatMap(line => line.split(" ")).map(word => (word, 1)).reduceByKey(_ + _, numOutputFiles)

//    val pairs  = inputfile.flatMap(line => line.split(" ")).map(word => (word, 1))
//    val flatMap1  = inputfile.flatMap(line => line.split("\t"))  //所有列
    val flatMap1  = inputfile.flatMap(line => line.split("\t")(1)) //第二列的数据
    flatMap1.take(5).foreach(println)
    val  pairs = flatMap1.map(word => (word, 1))
    pairs.take(5).foreach(println)
//    val wordCounts = pairs.reduceByKey(_+_)
    val wordCounts = pairs.reduceByKey(_+_)
//    val wordCounts = pairs.reduceByKey((x,y) => (x+y))


//    val path2 ="file://input/datatest/web-Google.txt.count"
//    val hadoopPath =new Path(path2)
//    if(hdfs.exists(hadoopPath)){
//      //为防止误删，禁止递归删除
//      hdfs.delete(hadoopPath,false)
//    }

    //wordCount
//    val path ="hdfs://crm-master2:8020/input/datatest/web-Google.txt.count2"
//    wordCounts.saveAsTextFile(path)

   // topN的计算。
   val topN =  wordCounts.map(x => (x._2,x._1)).sortByKey(false).map(x => (x._2,x._1)).take(20)
    topN.foreach(println)

    sc.stop()
  }
}