package test

import java.util.Date

trait Currency {
  val symbol: String
  val name: String
}

// unfortunately Objects is not supported by json4s (or not yet)

case class Dollar() extends Currency {
  val symbol = "$"
  val name = "dollar"
}

case class Euro() extends Currency {
  val symbol = "€"
  val name = "euro"
}

trait Place

case class Address(
                    address: String,
                    town: String
                  ) extends Place

case class GpsCoords(
                      latitude: Double,
                      north: Boolean,
                      longitude: Double,
                      east: Boolean
                    ) extends Place

case class Receipt(
                    amount: Double,
                    currency: Currency,
                    when: Date,
                    where: Place,
                    what: String,
                    keywords: Set[String]
                  )

case class ReceiptsArchive(
                            receipts: List[Receipt],
                            description: Option[String] = None
                          )

object Dummy {

  import org.json4s._
  import org.json4s.native.Serialization
  import org.json4s.native.Serialization.{read, write, writePretty}

  def today() = new Date()

  def main(args: Array[String]) {
    //implicit val formats = Serialization.formats(NoTypeHints)
    implicit val formats = Serialization.formats(
      ShortTypeHints(
        List(
          classOf[Euro],
          classOf[Dollar],
          classOf[Address],
          classOf[GpsCoords]
        )
      )
    )

    val receipts = ReceiptsArchive(
      Receipt(15, Euro(), today(), Address("XIII", "Paris"), "meal", Set("food", "4work")) ::
        Receipt(1, Euro(), today(), Address("I", "Paris"), "bread", Set("food")) :: Nil,
      Some("2013 archives")
    )

    val json: String = write(receipts)

    println(writePretty(receipts))

    val decoded: ReceiptsArchive = read[ReceiptsArchive](json)

    println(decoded)

    assert(json == write(decoded))
  }
}
