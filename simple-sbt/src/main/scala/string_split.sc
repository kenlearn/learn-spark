val s = "  some  interesting stuff ...   "
/*
// this works
val x = s.toLowerCase.
    replaceAll("[^\\w']", " ").
    split(" ").
    filter(w => w.trim.length >=1).
    groupBy(identity).
    mapValues(_.size)
*/

// this does not work
val x = s.toLowerCase()
    .replaceAll("[^\\w']", " ")
    .split(" ")
    .filter(w => w.trim.length >=1)
    .groupBy(identity)
    .mapValues(_.size)

