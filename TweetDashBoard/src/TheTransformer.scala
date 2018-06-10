import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession.builder
import org.tweet.analytics.processing.Iprocessor
import org.tweet.analytics.connectors.mySQLConnector
import org.tweet.analytics.processing.TestReport
import org.tweet.analytics.connectors.solrConnector

object TheTransformer {
  def main(args: Array[String]): Unit = {

    val reportStr = "TestReport"
    var con:Any = null
    var  report: Iprocessor = null
    
    reportStr match {
      case "TestReport" => {
   
        val solrCon = solrConnector.getConnection()
        report = new TestReport(solrCon)
      }
      case _ => println("could not find the report type!!")
    }

    report.getCon()
    report.readData()
    report.processdata()
    report.writeData()
    report.closeCon()

  }
}