package pub.wii.cook.scala

import pub.wii.cook.scala.Example.Planet

object TestProtobuf {
  def main(args: Array[String]): Unit = {
    val planet = Planet.newBuilder()
      .setName("earth")
      .setBelongTo("galaxy")
      .build()
    val fields = ("name", "belong_to")
    fields.productIterator.foreach(field => {
      // field name -> field descriptor
      val fieldDescriptor = Planet.getDescriptor.findFieldByName(field.toString)
      // field value
      val value = planet.getField(fieldDescriptor)
      println(value) // earth
    })
  }
}
