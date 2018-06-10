package org.tweet.analytics.processing

import org.tweet.analytics.connectors.Iconnector

trait Iprocessor {
  def readData(): Boolean
  def writeData(): Boolean
  def processdata()
  def getCon()
  def closeCon()
}