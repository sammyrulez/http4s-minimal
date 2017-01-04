name := "http4s-minmal"

version <<= version in ThisBuild

scalaVersion := "2.12.1"

val http4sVersion = "0.15.2"

libraryDependencies ++= Seq(
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion
)

libraryDependencies +="io.circe" %% "circe-generic" % "0.6.1"

libraryDependencies += "org.slf4j" % "slf4j-simple" % "1.6.4"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.1"

publishTo := Some(Resolver.file("file",  new File( Path.userHome.absolutePath+"/dev/projects/my-maven-repo" )) )

coverageMinimum := 80

coverageFailOnMinimum := true
    