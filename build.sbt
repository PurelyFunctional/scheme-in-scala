lazy val commonSettings = Seq(
  name := "schemin-scala",
  version := "1.0",
  scalaVersion := "2.11.4"
)

lazy val scalazVersion = "7.1.0"

lazy val root = (project in file(".")).
  settings(commonSettings: _*).
  settings(
    scalaSource in Compile := baseDirectory.value / "src",
    // scalaSource in Test := baseDirectory.value / "test"

    // scalaz library
    libraryDependencies += "org.scalaz" %% "scalaz-core" % scalazVersion,
    libraryDependencies += "org.scalaz" %% "scalaz-effect" % scalazVersion
  )
