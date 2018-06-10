package org.tweet.analytics.connectors

import com.lucidworks.spark.rdd.SelectSolrRDD
import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object solrConnector extends Iconnector {

  def getConnection(conDetails: Any): SparkSession = {

    val spark = SparkSession.builder
      .master("local")
      .appName("Spark Report")
      .getOrCreate

    return spark
  }
}