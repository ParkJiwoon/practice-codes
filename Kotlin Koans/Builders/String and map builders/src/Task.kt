import java.util.HashMap

/* TODO */
fun buildMutableMap(build: MutableMap<Int, String>.() -> Unit): Map<Int, String> {
    val map = mutableMapOf<Int, String>()
    map.build()
    return map
}

fun usage(): Map<Int, String> {
    return buildMutableMap {
        put(0, "0")
        for (i in 1..10) {
            put(i, "$i")
        }
    }
}
