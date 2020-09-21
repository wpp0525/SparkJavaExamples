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

package org.apache.spark.examples.sql

import java.util.Properties

import org.apache.hadoop.io.IOUtils
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

// 读取mysql的数据，并做展示，Dataframe操作。
object LoadJDBCTest {
  def main(args: Array[String]): Unit = {
    // jdbc:mysql://10.113.9.95:3306/crm_hive DBS hive 123456
    if (args.length < 4) {
      System.err.println("Usage: <conn-url> <table-for-read> <user> <passwd>")
//      System.exit(1)
    }


    val url = args(0)
    val table = args(1)
    val user = args(2)
    val passwd = args(3)


    val conf = new SparkConf().setAppName("LoadJDBCTest").setMaster("local[*]")

    val sc = new SparkContext(conf)
    val sqlContext = new SQLContext(sc)

    val props = new Properties()
    props.put("user", user)
    props.put("password", passwd)

    val df = sqlContext.read.jdbc(url, table, props)

    println("Record count: " + df.count())

    df.show()

    println("distinct show: "    )
    df.where("DB_ID =1").distinct().select("DB_ID","DB_LOCATION_URI","NAME").show()
    df.persist()

//    IOUtils.copyBytes()
    Thread.sleep(600*1000)
    sc.stop()
  }
}