package org.tweet.analytics.connectors

trait Iconnector {
  def getConnection(conDetails : Any) : Any
  //test
}