package sk.stuba.fiit.ui.osemhlavolam

import java.util.*

data class Node(
    val state: State,
    val directions: LinkedList<Direction> = LinkedList(),
    val inverse: Boolean = false,
    val heuristic: ManhattanHeuristic = ManhattanHeuristic(state, state)
) : Comparable<Node> {

    fun move(direction: Direction): Result<Node> {
        return if (state.canMove(direction) && isNotSameAsLastDirection(direction)) {
            val newState = state.copy()
            moveDirect(newState.state, state.emptyPlace, direction)

            val newNode = Node(newState, LinkedList(directions), inverse, ManhattanHeuristic(newState, heuristic.finalState))
            newNode.directions.add(direction)
            Result.success(newNode)
        } else {
            Result.failure(IllegalStateException("Cannot move in direction $direction"))
        }
    }

    private fun moveDirect(state: Array<IntArray>, emptyPlace: Pair<Int, Int>, direction: Direction) {
        state[emptyPlace.first][emptyPlace.second] = state[emptyPlace.first + direction.x][emptyPlace.second + direction.y]
        state[emptyPlace.first + direction.x][emptyPlace.second + direction.y] = 0
    }

    private fun isNotSameAsLastDirection(direction: Direction) = directions.isEmpty() || directions.last != direction.getInverseDirection()

    private fun directionsToString() = directions.joinToString(prefix = "[", postfix = "]")

    override fun compareTo(other: Node) = heuristic.compareTo(other.heuristic)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Node) return false

        if (state != other.state) return false

        return true
    }

    override fun hashCode(): Int {
        return state.hashCode()
    }

    override fun toString() = "${directionsToString()}\n$state"
}