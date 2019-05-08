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
package org.apache.spark.examples.wpp

import org.apache.spark.{HashPartitioner, SparkConf, SparkContext}

object PageRank2 {

  def showWarning() {
    System.err.println(
      """WARN: This is a naive implementation of PageRank and is given as an example!
        |Please use the PageRank implementation found in org.apache.spark.graphx.lib.PageRank
        |for more conventional use.
      """.stripMargin)
  }

  def main(args: Array[String]) {

    showWarning()

    val sparkConf = new SparkConf().setAppName("PageRank").setMaster("local[*]")

    val sc = new SparkContext(sparkConf)

    val links = sc.parallelize(List(("A",List("B","C")),("B",List("A","C")),("C",List("A","B","D")),("D",List("C")))).
      partitionBy(new HashPartitioner(100)).persist()

    var ranks = links.mapValues(v=>1.0)

    for (i <- 0 until 10) {
      val contributions=links.join(ranks).flatMap {
        case (pageId,(links,rank)) => links.map(dest => (dest,rank/links.size))
      }
      ranks=contributions.reduceByKey((x,y)=>x+y).mapValues(v=>0.15+0.85*v)
    }
    print("====result===========")
    ranks.sortByKey().collect().foreach(println)

    sc.stop()
  }
}