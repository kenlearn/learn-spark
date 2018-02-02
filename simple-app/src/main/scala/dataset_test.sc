// sample code from https://spark.apache.org/docs/latest/sql-programming-guide.html

//import org.apache.spark.sql.SparkSession
//import spark.implicits._

/*
val spark = SparkSession.builder()
    .appName("Spark SQL example")
    .config("some.config.option", "config.value")
    .getOrCreate()

val df = spark.read.json("/home/vagrant/opt/spark/examples/src/main/resources/people.json")

*/


val s = "  some  sdfs sdfsdf  some sdfsdf   sdfsds  sdfwerw   "
/*
val x = s.toLowerCase.
    replaceAll("[^\\w']", " ").
    split(" ").
    filter(w => w.trim.length >=1).
    groupBy(identity).
    mapValues(_.size)
*/

val x = s.toLowerCase()
    .replaceAll("[^\\w']", " ")
    .split(" ")
    .filter(w => w.trim.length >=1)
    .groupBy(identity)
    .mapValues(_.size)

println(x)
