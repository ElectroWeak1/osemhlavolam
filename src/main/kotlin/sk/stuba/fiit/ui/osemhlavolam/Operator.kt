package sk.stuba.fiit.ui.osemhlavolam

/**
 * Operator that can be applied to state of 8-puzzle and creates a new state with change made by operator. Operator
 * represents offset of tile which will be moved in operators direction. Supported operators are [LEFT], [UP],
 * [RIGHT], [DOWN].
 */
enum class Operator(
    val row: Int,
    val column: Int
) {
    LEFT(0, 1),
    UP(1, 0),
    RIGHT(0, -1),
    DOWN(-1, 0);
}