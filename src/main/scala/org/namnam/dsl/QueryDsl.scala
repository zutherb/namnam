package org.namnam.dsl

/**
 * @author zutherb
 */
case class Fields(val fields:Seq[String])
case class Datasets(val datasets:Seq[String])
case class Connection(val url:String)
case class Criterion()

case class SelectIdentifier(queryBuilder:QueryBuilder) {
  def select(fields:String*) = {
    queryBuilder.queryString += s"select $fields\n"
    this
  }

  override def toString: String = {
    queryBuilder toString
  }
}

case class FromIdentifier(queryBuilder:QueryBuilder) {
  def from(datasets:String*) = {
    queryBuilder.queryString += s"from $datasets\n"
    SelectIdentifier(queryBuilder)
  }

  override def toString: String = {
    queryBuilder toString
  }
}

case class ToIdentifier(queryBuilder:QueryBuilder){
  def to(url:String) = {
    queryBuilder.queryString += s" to $url\n"
    FromIdentifier(queryBuilder)
  }
}

case class ConnectionIdentifier(queryBuilder:QueryBuilder){
  def connection() = {
    queryBuilder.queryString += " connection"
    ToIdentifier(queryBuilder)
  }
}

class QueryBuilder {
  var queryString:String = null
  var connection:Connection = null

  def create() = {
    queryString = "create"
    ConnectionIdentifier(this)
  }

  override def toString: String = {
    queryString
  }
}
