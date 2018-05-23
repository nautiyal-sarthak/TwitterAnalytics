package org.tweet.analytics.processing

import org.tweet.analytics.connectors.Iconnector

trait Iprocessor {
  def getData
  def setData
  def process
}