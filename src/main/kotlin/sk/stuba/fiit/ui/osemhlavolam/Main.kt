package sk.stuba.fiit.ui.osemhlavolam

fun main() {
    // RIGHT, DOWN, LEFT, UP
    val initialState = State(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 0)))
    val finalState = State(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 6, 8), intArrayOf(7, 5, 0)))

    val puzzle = PuzzleSolver(initialState, finalState)
    puzzle.solve()
}