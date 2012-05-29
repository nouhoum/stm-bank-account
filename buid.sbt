name := "stm-bank-account"

organization := "com.arolla"

version := "0.0-SNAPSHOT"

// Compile dependencies
libraryDependencies ++= {
        Seq(
           "org.scala-tools" %% "scala-stm" % "0.5" withSources()
        )
}

// Test dependencies
libraryDependencies ++= {
        Seq(
           "org.scalatest" %% "scalatest" % "1.7.2" % "test"
        )
}

