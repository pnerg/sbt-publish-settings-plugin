lazy val `sbt-release` = project in file(".")

sbtPlugin := true

name := "sbt-publish-settings-plugin"
organization := "org.dmonix.sbt"

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

//----------------------------
//info for where and how to publish artifacts
//----------------------------
import org.dmonix.sbt.MavenCentralSettings._
import org.dmonix.sbt.CredentialsSettings._
publishTo <<= version {publishURL(_)}
credentials ++= publishCredentials

//----------------------------
//needed to create the proper pom.xml for publishing to mvn central
//----------------------------
publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := { _ => false }
pomExtra := (
  <url>https://github.com/pnerg/sbt-publish-settings-plugin</url>
  <licenses>
    <license>
      <name>Apache</name>
      <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:pnerg/sbt-publish-settings-plugin.git</url>
    <connection>scm:git:git@github.com/pnerg/sbt-publish-settings-plugin.git</connection>
  </scm>
  <developers>
    <developer>
      <id>pnerg</id>
      <name>Peter Nerg</name>
      <url>http://github.com/pnerg</url>
    </developer>
  </developers>)
