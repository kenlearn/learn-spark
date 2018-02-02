// Sample code from: https://spark.apache.org/docs/latest/rdd-programming-guide.html

/*
    run from spark-shell:
    spark-shell --master local[2]

    load this script in spark-shell:
    :load src/main/scala/rdd_test.sc
*/

// create array
val data = Array(1,2,3,4,5)

// parallelize data
val distdata = sc.parallelize(data)

// sum the data
distdata.reduce((a,b) => a+b)

// read from file
val distfile = sc.textFile("/home/vagrant/opt/spark/README.md")

// count lines
distfile.map(s => 1) reduce((a,b) => a+b)

// count characters
distfile.map( s => s.length).reduce((a,b) => a+b)

// the length of longest line
distfile.map(s => s.length).reduce((a,b) => if(a>b) a else b)

// persist to memory
val lengths = distfile.map(s => s.length)
lengths.persist

// reduce by key
val lenWithKey = distfile.flatMap(s => s.split(" ")).map(s => (s, 1)).reduceByKey((a,b) => a+b)

