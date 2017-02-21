import sbt._
import sbt.Keys._
import bintray.BintrayPlugin.autoImport.bintrayReleaseOnPublish

object Build extends sbt.Build {
  lazy val root = Project(
    id = "root",
    base = file("."),
    settings = Seq(
    name := "uri-template",
      description := "URI Template",
      organization := "no.arktekk",
      scalaVersion := "2.11.8",
      crossScalaVersions := Seq("2.12.1","2.11.8"),
      libraryDependencies ++= Seq(
        "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.5",
        "org.scalatest" %% "scalatest" % "3.0.0" % "test"
      ),
      manifestSetting,
      bintrayReleaseOnPublish in ThisBuild := false
    )
  )

  lazy val manifestSetting = packageOptions <+= (name, version, organization) map {
    (title, version, vendor) =>
      Package.ManifestAttributes(
        "Created-By" -> "Simple Build Tool",
        "Built-By" -> System.getProperty("user.name"),
        "Build-Jdk" -> System.getProperty("java.version"),
        "Specification-Title" -> title,
        "Specification-Version" -> version,
        "Specification-Vendor" -> vendor,
        "Implementation-Title" -> title,
        "Implementation-Version" -> version,
        "Implementation-Vendor-Id" -> vendor,
        "Implementation-Vendor" -> vendor
      )
  }
}
