package pub.wii.cook.scala

import com.google.protobuf.Descriptors
import pub.wii.cook.scala.Example.Planet

object TestPbReflection {
  def main(args: Array[String]): Unit = {
    // init pb message
    val planet: Planet = Planet.newBuilder()
      .setName("earth")
      .setBelongTo("galaxy")
      .build()

    val fd: Descriptors.FieldDescriptor = Planet.getDescriptor.findFieldByName("name")
    println(planet.getField(fd))

    val bfd = planet.getDescriptorForType.findFieldByName("belong_to")
    println(planet.getField(bfd))
  }
}
