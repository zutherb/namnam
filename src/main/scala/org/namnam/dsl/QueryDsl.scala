package org.namnam.dsl

/**
 * @author zutherb
 */
case class Fields(val fields:Seq[String])
case class Datasets(val datasets:Seq[String])
case class Connection(val url:String)
case class Criterion()

case class ConnectionIdentifier(queryBuilder:QueryBuilder) {
  private var connection:Option[Connection] = None

  def to(url : String) : QueryBuilder = {
    this.connection = Some(Connection(url))
    queryBuilder
  }
}


class QueryBuilder() {
  var fields:Option[Fields] = None
  var dataSets:Option[Datasets] = None
  var connectionIdentifier:Option[ConnectionIdentifier] = None

  def create () : QueryBuilder = {
    this
  }

  def connection (): ConnectionIdentifier = {
    connectionIdentifier = Some(ConnectionIdentifier(this))
    connectionIdentifier.get
  }



  def from(datasets:String*):QueryBuilder = {
    this.dataSets = Some(Datasets(datasets))
    this
  }

  def select(fields:String*):QueryBuilder = {
    this.fields = Some(Fields(fields))
    this
  }

  def where(Criterion:Criterion*) = {
    this
  }

  override def toString: String = {
    val url: String = "mongodb://localhost/test"
    s"""
        create  connection to \"$url\"
        from    collection1, collection2
        where   collection1.a.b = 123 AND
                collection2.b.c = 123
        select  collection1.a, collection2.b
    """
  }
}
