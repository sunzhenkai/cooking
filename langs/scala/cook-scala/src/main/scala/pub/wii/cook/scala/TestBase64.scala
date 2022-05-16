package pub.wii.cook.scala

import pub.wii.cook.scala.Example.Planet

import java.util.Base64

object TestBase64 {
  def main(args: Array[String]): Unit = {
    val v = "GjpCATBKATBSATFaCUFwcHNmbHllcmICLTFqHWNvbS5jYXJuaXZhbC5zcGluLnNsb3RzLmx1Y2t5egEy"
    //val res = Base64.getDecoder.decode(v)
    val res = Base64.getMimeDecoder.decode(v)
    println(new String(res))
  }
}
