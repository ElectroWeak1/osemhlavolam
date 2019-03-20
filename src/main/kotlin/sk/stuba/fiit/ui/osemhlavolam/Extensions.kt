package sk.stuba.fiit.ui.osemhlavolam

typealias Map = Array<IntArray>
typealias Position = Pair<Int, Int>

fun Pair<Int, Int>.distance(point: Pair<Int, Int>) = Math.abs(first - point.first) + Math.abs(second - point.second)

fun Array<IntArray>.deepCopy() = Array(size) { get(it).clone() }