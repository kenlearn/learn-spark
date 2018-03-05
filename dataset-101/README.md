# DataSet example from NewCircle video

- [NewCircle Video Tutorial](https://youtu.be/_1byVWTEK1s)
- [Databricks Community Edition](https://community.cloud.databricks.com/)
- [My spark101 notebook](https://community.cloud.databricks.com/?o=2052251428961985#notebook/3759001030556419)


## Try out

### Download sample data
download [Sample data "zips.json"](http://media.mongodb.org/zips.json), put the file in ```data``` dir

### Create zip table
```scala
spark.read.json("data/zips.json").createOrReplaceTempView("zip")

sql("describe zip")

```

### Play with Spark SQL
```scala
// change column name
spark.table("zip").withColumnRenamed("_id", "zip").createOrReplaceTempView("zip")

sql("select count(zip), sum(pop), city from zip where state = 'IL' group by city order by sum(pop) desc limit 10")

spark.table("zip").select("zip", "city", "state", "pop").write.format("csv").save("data/zip.csv")

```

### RDD example
```scala
val zipRDD = sc.textFile("data/zip.csv").map(line => {
  val fields = line.split(",")
  (fields(0), fields(1), fields(2), fields(3).toInt)
})

zipRDD.take(5).foreach(println)

/** Wrap around code block to avoid "illegal start of definition"
 *    when paste the code to console. 
 *    the starting '.' might not be recognized correctly when paste to console
 */
{
zipRDD.filter(_._3 == "IL")
  .map(r => (r._2, (1, r._4)))
  .reduceByKey((a,b) => (a._1+b._1, a._2+b._2))
  .sortBy(- _._2._2)
  .take(5).foreach(println)
}
```
