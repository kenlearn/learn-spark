// test chain method calls into multi lines

object MethodChain extends App {
    val s = "  some  sdfs sdfsdf  some sdfsdf   sdfsds  sdfwerw   "

    val x = s.toLowerCase()
        .replaceAll("[^\\w']", " ")
        .split(" ")
        .filter(w => w.trim.length >=1)
        .groupBy(identity)
        .mapValues(_.size)

    println(x)

}
