name := "Simple App"

version := "1.0"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
    "org.apache.spark" %% "spark-sql" % "2.2.1",
    "org.apache.spark" %% "spark-core" % "2.2.1",
)


/* Notes
- sparl-sql only has lib for scala 2.11

*/
