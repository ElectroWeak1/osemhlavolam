package sk.stuba.fiit.ui.osemhlavolam

typealias Map = Array<IntArray>
typealias Position = Pair<Int, Int>

fun Array<IntArray>.copy() = Array(size) { get(it).clone() }