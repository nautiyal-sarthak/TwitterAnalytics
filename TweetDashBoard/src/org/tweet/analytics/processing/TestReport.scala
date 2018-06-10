package org.tweet.analytics.processing

import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.DataFrame
import java.sql.Connection
import org.apache.spark.sql.execution.datasources.jdbc.JdbcUtils
import org.apache.spark.sql.execution.datasources.jdbc.JDBCOptions

class TestReport(conObj: SparkSession) extends Iprocessor {

  var processedData: DataFrame = null
  var spark: SparkSession = null
  var jdbcCon: Connection = null

  def getCon() = {
    spark = conObj
  }

  def closeCon() = {
    jdbcCon.close()
    spark.close()
  }

  def readData(): Boolean = {
    val options = Map(
      "collection" -> "tweets",
      "zkhost" -> "localhost:2181")

    try {
      processedData = spark.read.format("solr")
        .options(options)
        .load
      return true
    } catch {
      case ex: Exception => {
        println("got an exception while connecting to solr : " + ex.getMessage)
        return false
      }
    }
  }

  def writeData(): Boolean = {
    println("Writing Data")

    val url = "jdbc:mysql://localhost:3306/tweetDashboard"
    val driver = "com.mysql.jdbc.Driver"
    val username = "root"
    val password = "her0zer0"

    val options = Map(
      "url" -> "jdbc:mysql://localhost:3306/tweetDashboard",
      "driver" -> "com.mysql.jdbc.Driver",
      "dbtable" -> "test",
      "user" -> "root",
      "password" -> "her0zer0")

    //    val props = new java.util.Properties()
    //    props.setProperty("user", "root")
    //    props.setProperty("password", "her0zer0")

    //JdbcUtils.saveTable(processedData, tableSchema, false, options)

    //
    //    val option = Map("url" -> "jdbc:postgresql:postgres", "dbtable" -> "tx", "user" -> "postgres", "password" -> "test")
    //
    processedData.printSchema()
    processedData.write.mode("overwrite").format("jdbc").options(options).save()
    return true
  }

  def processdata() = {
    processedData.printSchema()

    processedData.createTempView("tweet")
    processedData = spark.sql("SELECT created_at from tweet limit 10")

  }
}