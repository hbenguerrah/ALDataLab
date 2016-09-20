name := "ALDataLab"

version := "1.3.2"

scalaVersion := "2.10.5"
val sparkVersion = "1.6.2"

libraryDependencies += "org.apache.spark" %% "spark-core" % sparkVersion % "provided"
libraryDependencies += "org.apache.spark" %% "spark-sql" % sparkVersion  % "provided"
libraryDependencies += "org.apache.spark" %% "spark-hive" % sparkVersion % "provided"
libraryDependencies += "com.databricks" %% "spark-csv" % "1.4.0"
libraryDependencies += "joda-time" % "joda-time" % "2.9.2"

enablePlugins(UniversalPlugin)

mappings in Universal <+= (packageBin in Compile) map { jar =>
  jar -> ("lib/" + jar.getName)
}

mappings in Universal <+= (assembly in assembly, assemblyJarName in assembly) map { (jar,name) => {
  println(jar.toString)
  jar -> ("lib/" + name)
}}

import S3._

s3Settings

host in upload := "gedatalab.s3.amazonaws.com"

mappings in upload <+= (packageBin in Universal, packageName in Universal) map {
  (bin,name) => (bin,"binaries/"+name)
}
