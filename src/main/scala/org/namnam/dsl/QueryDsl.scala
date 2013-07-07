package org.namnam.dsl

/**
 * @author zutherb
 */
case class Fields(fields:Seq[String])

case class Datasets(datasets:Seq[String]){
  implicit def select(fields:String*):Fields = new Fields(fields)
}
case class Connection(url:String){
  implicit def from(datasets:String*):Datasets = new Datasets(datasets)
}

class QueryBuilder {
  implicit def connect(url:String):Connection = new Connection(url)
}
