package sk.stuba.fiit.ui.osemhlavolam

typealias Map = Array<IntArray>
typealias Position = Pair<Int, Int>

fun Array<IntArray>.deepCopy() = Array(size) { get(it).clone() }