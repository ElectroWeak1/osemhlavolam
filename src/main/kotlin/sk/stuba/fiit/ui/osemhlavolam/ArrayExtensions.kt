package sk.stuba.fiit.ui.osemhlavolam

fun Array<IntArray>.copy() = Array(size) { get(it).clone() }