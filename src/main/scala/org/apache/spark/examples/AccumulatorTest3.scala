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

/**
  * 累加器
  * 累加器提供了将工作节点中的值聚合到驱动器程序中的简单方法。累加器一个常见的用途就是在调试时对作业执行过程中的事件进行计数。
  *
  *
  */

object AccumulatorTest3 {
  def main(args: Array[String]): Unit = {
    val sparkConf = new SparkConf().setAppName("AccumulatorTest3").setMaster("local")
    val ctx = new SparkContext(sparkConf)

    //在驱动端执行
    val files = ctx.textFile("E://java//ClickHousePreparedStatementImpl.java")
    var blankLines = ctx.accumulator(2,"qwpp")//创建accumulator[0]并初始化为0

    val blankLines2 = ctx.accumulator(0,"qwpp2")//创建accumulator[0]并初始化为0
    println("blankLines:" + blankLines)

    println("files_line:" + files.count())

    println(" files.collect()" +  files.collect())

    val callSigns = files.flatMap(line => {

      if(line.contains("String") ){
        blankLines += 1
      }
      line
    })

    callSigns.count()
    println("accumate :" + blankLines)

    Thread.sleep(600000)
    ctx.stop()
  }
}