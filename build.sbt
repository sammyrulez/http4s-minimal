name := "http4s-minmal"

version := "1.0"

scalaVersion := "2.12.1"

val http4sVersion = "0.15.2"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion
)

libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.6.4"
    