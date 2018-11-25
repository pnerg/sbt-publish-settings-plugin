lazy val `sbt-release` = project in file(".")
import org.dmonix.sbt.MavenCentralPlugin._
import sbt.Developer

enablePlugins(MavenCentralPlugin)
sbtPlugin := true

name := "sbt-publish-settings-plugin"
organization := "org.dmonix.sbt"

//info required for Maven Central
startYear := Some(2017)
homepage := Some(new URL("https://github.com/pnerg/sbt-publish-settings-plugin"))
scmInfo := Some(github("pnerg/sbt-publish-settings-plugin"))
licenses := Seq(apache)
developers := List(githubDeveloper("pnerg", "Peter Nerg"))

scalaVersion := "2.10.6"
crossScalaVersions := Seq("2.10.6", "2.12.3")
crossSbtVersions := Seq("0.13.17", "1.1.6")

//---------------------------------------
// Compiler directives
//---------------------------------------

scalacOptions := Seq("-feature",
  "-language:postfixOps",
  "-language:implicitConversions",
  "-unchecked",
  "-deprecation",
  "-encoding", "utf8")

scalacOptions in (Compile, doc) ++= Seq("-doc-title", "SBT Publish Settings Plugin")
scalacOptions in (Compile, doc) ++= Seq("-doc-root-content", baseDirectory.value+"/src/main/scaladoc/overview.txt")
scalacOptions in (Compile, doc) ++= Seq("-doc-footer", "Copyright (c) 2018 Peter Nerg, Apache License v2.0.")
