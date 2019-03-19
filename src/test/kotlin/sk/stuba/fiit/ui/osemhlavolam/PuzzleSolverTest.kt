package sk.stuba.fiit.ui.osemhlavolam

import org.junit.Assert.assertTrue
import org.junit.Test

class PuzzleSolverTest {
    @Test
    fun `Basic test`() {
        // RIGHT, DOWN, LEFT, UP
        val initialState = State(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 0)))
        val finalState = State(arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 6, 8), intArrayOf(7, 5, 0)))

        val puzzle = PuzzleSolver(initialState, finalState)
        val result = puzzle.solve()

        println(result)

        assertTrue(testResult(initialState, finalState, result))
    }

    @Test
    fun `Medium test`() {
        // DOWN, RIGHT, UP, UP, LEFT, DOWN
        val initialState = State(arrayOf(intArrayOf(1, 3, 4), intArrayOf(8, 0, 5), intArrayOf(7, 2, 6)))
        val finalState = State(arrayOf(intArrayOf(1, 2, 3), intArrayOf(8, 0, 4), intArrayOf(7, 6, 5)))

        val puzzle = PuzzleSolver(initialState, finalState)
        val result = puzzle.solve()

        println(result)

        assertTrue(testResult(initialState, finalState, result))
    }

    private fun testResult(initialState: State, finalState: State, directions: List<Direction>): Boolean {
        var node = Node(initialState)
        directions.forEach {
            node = node.move(it).getOrThrow()
        }
        return finalState == node.state
    }
}