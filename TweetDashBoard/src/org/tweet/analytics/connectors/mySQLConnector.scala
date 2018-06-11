package org.tweet.analytics.connectors

import java.sql.{ Connection, DriverManager }

object mySQLConnector extends Iconnector {
  def getConnection(conDetails: Any): Connection = {

    val url = "jdbc:mysql://localhost:3306/tweetDashboard"
    val driver = "com.mysql.jdbc.Driver"
    val username = "root"
    val password = "*****"
    var conn: Connection = null

    try {
      Class.forName(driver)
      conn = DriverManager.getConnection(url, username, password)
    } catch {
      case e: Exception => e.printStackTrace
    }

    return conn
  }
}
