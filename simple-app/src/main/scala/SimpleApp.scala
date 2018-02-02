// code from: https://spark.apache.org/docs/latest/quick-start.html

import org.apache.spark.sql.SparkSession

object SimpleApp {

    def main(args: Array[String]) {
        val logF = "/home/vagrant/opt/spark/README.md"
        val spark = SparkSession.builder.appName("Simple Application").getOrCreate()
        val logData = spark.read.textFile(logF).cache()

        val countA = logData.filter(line => line.contains("a")).count()

        println(s"Lines with \'a\': $countA")

        spark.stop()
    }    
}
