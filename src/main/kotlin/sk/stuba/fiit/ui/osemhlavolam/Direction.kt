package sk.stuba.fiit.ui.osemhlavolam

enum class Direction(val x: Int, val y: Int) {
    UP(1, 0), DOWN(-1, 0), LEFT(0, 1), RIGHT(0, -1);

    fun getInverseDirection(): Direction {
        return when (this) {
            Direction.UP -> Direction.DOWN
            Direction.DOWN -> Direction.UP
            Direction.LEFT -> Direction.RIGHT
            Direction.RIGHT -> Direction.LEFT
        }
    }
}