package sk.stuba.fiit.ui.osemhlavolam

import java.util.*

// RIGHT, DOWN, LEFT, UP
val startState = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 5, 6), intArrayOf(7, 8, 0))
val endState = arrayOf(intArrayOf(1, 2, 3), intArrayOf(4, 6, 8), intArrayOf(7, 5, 0))

val rowCount = startState.size
val columnCount = if (endState.isNotEmpty()) endState[0].size else 0

val unprocessed = mutableSetOf<Node>()

data class Node(
    val state: Array<IntArray>,
    val directions: LinkedList<Direction> = LinkedList(),
    val inverse: Boolean = false,
    val heuristic: Int = 0
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Node

        if (!state.contentDeepEquals(other.state)) return false

        return true
    }

    override fun hashCode() = state.contentDeepHashCode()

    override fun toString() = directionsToString(directions) + "\n" + stateToString(state)
}

fun main() {
    unprocessed.add(Node(startState))
    unprocessed.add(Node(endState, inverse = true))
    while (true) {
        val node = unprocessed.elementAt(findBestNode())
        unprocessed.remove(node)
        val nodes = generateStates(node)
        for (it in nodes) {
            if (!unprocessed.add(it)) {
                unprocessed.find { unproccesedNode -> unproccesedNode == it }?.let { secondNode ->
                    val allDirections = mutableListOf<Direction>()
                    if (it.inverse) {
                        inverseDirections(it.directions)
                        allDirections.addAll(secondNode.directions)
                        allDirections.addAll(it.directions)
                    } else {
                        inverseDirections(secondNode.directions)
                        allDirections.addAll(it.directions)
                        allDirections.addAll(secondNode.directions)
                    }
                    println("Found same state\n$allDirections")
                    return
                }
            }
        }
    }
}

fun findBestNode(): Int {
    var bestHeuristicIndex = -1
    unprocessed.forEachIndexed { index, value ->
        if (bestHeuristicIndex == -1 ||
                value.heuristic < unprocessed.elementAt(bestHeuristicIndex).heuristic) {
            bestHeuristicIndex = index
        }
    }
    return bestHeuristicIndex
}

fun inverseDirections(directions: MutableList<Direction>) {
    val inversedDirections = mutableListOf<Direction>()
    directions.asReversed().forEach {
        inversedDirections.add(inverse(it))
    }
    directions.clear()
    directions.addAll(inversedDirections)
}

fun generateStates(node: Node): List<Node> {
    val nodes = mutableListOf<Node>()
    val position = findEmptyPlace(node.state)
    Direction.values().forEach { direction ->
        val result = move(node, position, direction)
        result.onSuccess { nodes.add(it) }
    }
    return nodes
}

enum class Direction(val x: Int, val y: Int) {
    UP(1, 0), DOWN(-1, 0), LEFT(0, 1), RIGHT(0, -1)
}

fun moveDirect(state: Array<IntArray>, emptyPlace: Pair<Int, Int>, direction: Direction) {
    state[emptyPlace.first][emptyPlace.second] = state[emptyPlace.first + direction.x][emptyPlace.second + direction.y]
    state[emptyPlace.first + direction.x][emptyPlace.second + direction.y] = 0
}

fun canMove(state: Array<IntArray>, emptyPlace: Pair<Int, Int>, direction: Direction): Boolean {
    val x = emptyPlace.first + direction.x
    val y = emptyPlace.second + direction.y
    return x > 0 && y > 0 && x < rowCount && y < columnCount
}

fun isNotSameAsLastDirection(node: Node, direction: Direction) = node.directions.isEmpty() || node.directions.last != inverse(direction)

fun move(node: Node, emptyPlace: Pair<Int, Int>, direction: Direction): Result<Node> {
    return if (canMove(node.state, emptyPlace, direction) && isNotSameAsLastDirection(node, direction)) {
        val newState = node.state.copy()
        moveDirect(newState, emptyPlace, direction)

        val newNode = Node(newState, LinkedList(node.directions), heuristic = computeHeuristic(newState, endState))
        newNode.directions.add(direction)
        Result.success(newNode)
    } else {
        Result.failure(IllegalStateException("Cannot move in direction $direction"))
    }
}

fun findEmptyPlace(state: Array<IntArray>): Pair<Int, Int> {
    for (i in 0 until rowCount) {
        for (j in 0 until columnCount) {
            if (state[i][j] == 0) {
                return i to j
            }
        }
    }
    throw NoSuchElementException("Empty place not found!")
}

fun inverse(direction: Direction): Direction {
    return when (direction) {
        Direction.UP -> Direction.DOWN
        Direction.DOWN -> Direction.UP
        Direction.LEFT -> Direction.RIGHT
        Direction.RIGHT -> Direction.LEFT
    }
}

fun directionsToString(directions: List<Direction>) = directions.joinToString(prefix = "[", postfix = "]")

fun computeHeuristic(state: Array<IntArray>, finalState: Array<IntArray>): Int {
    var heuristic = 0
    for (i in 0 until finalState.size) {
        for (j in 0 until finalState.size) {
            val value = finalState[i][j]
            val (x, y) = findValueIndex(value, state)
            heuristic += Math.abs(i - x) + Math.abs(j - y)
        }
    }
    return heuristic
}

fun findValueIndex(value: Int, state: Array<IntArray>): Pair<Int, Int> {
    for (i in 0 until state.size) {
        for (j in 0 until state.size) {
            if (state[i][j] == value) {
                return i to j
            }
        }
    }
    return -1 to -1
}

fun stateToString(state: Array<IntArray>): String {
    val stringBuilder = StringBuilder()
    state.forEachIndexed { index, array ->
        stringBuilder.append(array.joinToString(postfix = if (index < rowCount - 1) "\n" else ""))
    }
    return stringBuilder.toString()
}

fun Array<IntArray>.copy() = Array(size) { get(it).clone() }