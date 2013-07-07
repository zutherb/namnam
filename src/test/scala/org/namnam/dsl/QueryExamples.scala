package org.namnam.dsl

object QueryExamples extends QueryBuilder {

  connect("hdfs://192.2.2.1/test")
    .from("dataset1", "dataset2")
    .select("field1","field2","field3")

}