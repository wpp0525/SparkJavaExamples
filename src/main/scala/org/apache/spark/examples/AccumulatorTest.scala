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

import org.apache.spark.{SparkConf, SparkContext}

import scala.util.Random

/**
  * 累加器
  * 累加器提供了将工作节点中的值聚合到驱动器程序中的简单方法。累加器一个常见的用途就是在调试时对作业执行过程中的事件进行计数。
  *
  *
  */


object AccumulatorTest {

  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("AccumulatorTest").setMaster("local")
    val sc = new SparkContext(sparkConf)

    //在驱动端执行
    val accum = sc.accumulator(12, "My Accumulator")

    sc.parallelize(1 to 1001000, 10).foreach(i => {
        accum += 1
    })


    println("initialValue accum: " + accum.initialValue )
    println("accum2: " + accum )

    println("======================= "  )

    val data = sc.parallelize(
      List(
        ("13909029812",("20170507","http://www.baidu.com")),
        ("13909029812",("20170507","http://www.51cto.com")),
        ("18089376778",("20170401","http://www.google.com")),
        ("18089376778",("20170508","http://www.taobao.com"))
      )
    )

   val data3 =   data.aggregateByKey(scala.collection.mutable.Set[(String, String)](), 2) (
     (set, item) =>
          { set += item}, (set1, set2) => set1 union set2
    ).mapValues(x => x.toIterable).collect


    println( data3.mkString(",,,"))
    sc.stop()
  }
}