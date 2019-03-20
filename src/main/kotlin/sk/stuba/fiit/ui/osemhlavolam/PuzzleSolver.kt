package sk.stuba.fiit.ui.osemhlavolam

import sk.stuba.fiit.ui.osemhlavolam.heuristic.Heuristic
import sk.stuba.fiit.ui.osemhlavolam.heuristic.ManhattanHeuristic

class PuzzleSolver(
    val initialNode: Node,
    private val finalNode: Node,
    private val heuristic: Heuristic = ManhattanHeuristic()
) {
    /**
     * Generates all possible states from provided node by trying to apply all possible operators returning those
     * states that are valid after applying operator.
     * @param node node we try to apply all operators
     * @return list of all possible states by applying all operators on provided node
     */
    fun generatePossibleNodes(node: Node): List<Node> {
        val states = mutableListOf<Node>()
        Operator.values().forEach { operator ->
            val result = node.apply(operator)
            result.onSuccess { states.add(it) }
        }
        return states
    }
}