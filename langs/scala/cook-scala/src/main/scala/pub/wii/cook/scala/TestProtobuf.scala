package pub.wii.cook.scala

import pub.wii.cook.scala.Example.Planet

import java.util.Base64

object TestProtobuf {
  def main(args: Array[String]): Unit = {
    // init pb message
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
      println(value) // earth, galaxy
    })

    // encode
    val base64Data = new String(Base64.getEncoder.encode(planet.toByteArray))
    println(base64Data)

    // decode
    val parsed = Planet.parseFrom(Base64.getDecoder.decode(base64Data))
    println(parsed) // name: "earth", belong_to: "galaxy"
  }
}
