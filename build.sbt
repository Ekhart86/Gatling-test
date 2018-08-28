name := "Gatling-test"

version := "0.1"

scalaVersion := "2.12.6"

enablePlugins(GatlingPlugin)

libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.3.0" % "test"

libraryDependencies += "io.gatling" % "gatling-test-framework" % "2.3.0" % "test"

javaOptions in Gatling := overrideDefaultJavaOptions("-Xss10m", "-Xms2G", "-Xmx8G")