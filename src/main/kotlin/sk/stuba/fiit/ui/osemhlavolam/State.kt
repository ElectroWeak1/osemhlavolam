package sk.stuba.fiit.ui.osemhlavolam

import java.util.*

data class State(
    val state: Array<IntArray>
) {
    val rowCount = state.size
    val columnCount = if (state.isNotEmpty()) state[0].size else 0
    val emptyPlace: Pair<Int, Int> by lazy { findEmptyPlace() }

    fun move(direction: Direction): Result<State> {
        return if (canMove(direction)) {
            val newState = copy()
            newState.moveDirect(direction)
            Result.success(newState)
        } else {
            Result.failure(IllegalStateException("Cannot move in direction $direction"))
        }
    }

    fun canMove(direction: Direction): Boolean {
        val x = emptyPlace.first + direction.x
        val y = emptyPlace.second + direction.y
        return x > 0 && y > 0 && x < rowCount && y < columnCount
    }

    fun findValueIndex(value: Int): Pair<Int, Int> {
        for (i in 0 until state.size) {
            for (j in 0 until state.size) {
                if (state[i][j] == value) {
                    return i to j
                }
            }
        }
        return -1 to -1
    }

    private fun moveDirect(direction: Direction) {
        state[emptyPlace.first][emptyPlace.second] = state[emptyPlace.first + direction.x][emptyPlace.second + direction.y]
        state[emptyPlace.first + direction.x][emptyPlace.second + direction.y] = 0
    }

    private fun findEmptyPlace(): Pair<Int, Int> {
        for (i in 0 until rowCount) {
            for (j in 0 until columnCount) {
                if (state[i][j] == 0) {
                    return i to j
                }
            }
        }
        throw NoSuchElementException("Empty place not found!")
    }

    fun copy() = State(state.copy())

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is State) return false

        if (!state.contentDeepEquals(other.state)) return false

        return true
    }

    override fun hashCode(): Int {
        return state.contentDeepHashCode()
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        state.forEachIndexed { index, array ->
            stringBuilder.append(array.joinToString(postfix = if (index < rowCount - 1) "\n" else ""))
        }
        return stringBuilder.toString()
    }

}