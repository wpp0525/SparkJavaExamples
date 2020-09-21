package org.apache.spark.examples.streaming

import java.util

import org.apache.kafka.clients.producer.{ProducerConfig, ProducerRecord}

// Produces some random words between 1 and 100.
object KafkaWordCountProducer {

  def main(args: Array[String]) {
    if (args.length < 4) {
      System.err.println("Usage: <metadataBrokerList> <topic> " +
        "<messagesPerSec> <wordsPerMessage>")
      System.exit(1)
    }

    // 需要注意的是这里是 broker list，为 host:port,host:port 形式

    val Array(brokers, topic, messagesPerSec, wordsPerMessage) = args

    // Zookeeper connection properties
    val props = new util.HashMap[String, Object]()
    props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokers)
    props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")
    props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
      "org.apache.kafka.common.serialization.StringSerializer")

//    val producer = new KafkaProducer[String, String](props)
//
//    // Send some messages
//    while (true) {
//      (1 to messagesPerSec.toInt).foreach { messageNum =>
//        val str = (1 to wordsPerMessage.toInt).map(x => scala.util.Random.nextInt(100).toString)
//          .mkString(" ")
//
//        val message = new ProducerRecord[String, String](topic, null, str)
//        producer.send(message)
//      }
//
//      Thread.sleep(1000)
//    }
  }
}