package org.namnam.dsl

object QueryExamples extends QueryBuilder {

  def main(args: Array[String]) {
    println(
      create.connection to "mongodb://localhost/database"
      from("collection1", "collection2")
      select("collection1", "collection2")
    )
  }


}