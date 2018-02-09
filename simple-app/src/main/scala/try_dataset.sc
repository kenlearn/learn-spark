// sample code from https://spark.apache.org/docs/latest/sql-programming-guide.html

import org.apache.spark.sql.SparkSession
//import spark.implicits._


// estalish SparkSession
val spark = SparkSession.builder()
    .appName("Spark SQL example")
    .config("some.config.option", "config.value")
    .getOrCreate()

// create dataframe
val df = spark.read.json("/home/vagrant/opt/spark/examples/src/main/resources/people.json")

// basic datafrom operation
df.show()

df.printSchema()

df.select("name").show

df.select($"name", $"age"+1).show

df.filter($"age">21).show

df.groupBy("age").count().show

// get a column from DF as DS
val ages = df.select("age").as[Long]

// convert to DS
case class Person(name: String, age: Long)

val personDS = df.as[Person]

// User defined Aggregate function
import org.apache.spark.sql.expressions.Aggregator
import org.apache.spark.sql.Encoder
import org.apache.spark.sql.Encoders
import org.apache.spark.sql.SparkSession

case class Employee(name: String, salary: Long)
case class Average(var sum: Long, var count: Long)

object MyAverage extends Aggregator[Employee,Average,Double] {
    def zero: Average = Average(0L, 0L)

    def reduce(buffer: Average, employee: Employee): Average = {
        buffer.sum += employee.salary
        buffer.count += 1
        buffer
    }

    def merge(b1: Average, b2: Average): Average = {
        b1.sum += b2.sum
        b1.count += b2.count
        b1
    }

    def finish(reduction: Average): Double = reduction.sum.toDouble/reduction.count

    def bufferEncoder: Encoder[Average] = Encoders.product

    def outputEncoder: Encoder[Double] = Encoders.scalaDouble
}

val ds = spark.read.json("/home/vagrant/opt/spark/examples/src/main/resources/employees.json").as[Employee]

val avgSalary = MyAverage.toColumn.name("average_salary")

ds.select(avgSalary)
