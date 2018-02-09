object StringTest {

    val s = "  some  interesting stuff ...   "

    // this works
    def test1 {
        val x = s.toLowerCase.
            replaceAll("[^\\w']", " ").
            split(" ").
            filter(w => w.trim.length >=1).
            groupBy(identity).
            mapValues(_.size)
        
        println(x)
    }

    // this does not work
    def test2 {
        val x = s.toLowerCase()
            .replaceAll("[^\\w']", " ")
            .split(" ")
            .filter(w => w.trim.length >=1)
            .groupBy(identity)
            .mapValues(_.size)
            
        println(x)
    }

} // end of StringTest
