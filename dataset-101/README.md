# DataSet example from NewCircle video

- [NewCircle Video Tutorial](https://youtu.be/_1byVWTEK1s)
- [Databricks Community Edition](https://community.cloud.databricks.com/)
- [My spark101 notebook](https://community.cloud.databricks.com/?o=2052251428961985#notebook/3759001030556419)


## Try out

### create zip table
```scala
spark.read.json("zips.json").createOrReplaceTempView("zip")

sql("describe zip")

// change column name
spark.table("zip").withColumnRenamed("_id", "zip").createOrReplaceTempView("zip")
```

### play with SQL
```scala
sql("select count(*) from zip where state = 'IL'")
```
