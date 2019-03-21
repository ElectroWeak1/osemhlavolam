package sk.stuba.fiit.ui.osemhlavolam

import sk.stuba.fiit.ui.osemhlavolam.heuristic.Heuristic
import sk.stuba.fiit.ui.osemhlavolam.heuristic.ManhattanHeuristic

/**
 * Solver for 8-puzzle. Contains methods that help solve 8-puzzle.
 */
class PuzzleSolver(
    val initialNode: Node,
    private val finalNode: Node,
    private val heuristic: Heuristic = ManhattanHeuristic()
) {
    private val unprocessedNodes = MultiHashMap<Int, Node>()
    private val visitedStates = HashSet<State>()

    init {
        unprocessedNodes[heuristic.compute(initialNode.state, finalNode.state)] = initialNode
    }

    /**
     * Generates all possible states from provided node by trying to apply all possible operators returning those
     * states that are valid after applying operator. Puts provided node to list of visited nodes.
     * @param node node we try to apply all operators
     * @return list of all possible states by applying all operators on provided node
     */
    fun visitNode(node: Node): List<Node> {
        val nodes = mutableListOf<Node>()
        visitedStates.add(node.state)
        Operator.values().forEach { operator ->
            val result = node.apply(operator)
            result.onSuccess {
                if (!visitedStates.contains(it.state)) {
                    nodes.add(it)
                }
            }
        }
        if (visitedStates.size % 10000 == 0) {
            println("Visited: ${visitedStates.size}, Unprocessed: ${unprocessedNodes.size}, Depth: ${node.operators.size})")
        }
        return nodes
    }

    fun insertUnprocessedNodes(nodes: List<Node>): Result<Node> {
        nodes.forEach {
            if (it.state == finalNode.state) {
                return Result.success(it)
            }
            unprocessedNodes[heuristic.compute(it.state, finalNode.state)] = it
        }
        return Result.failure(IllegalStateException("Couldn't find solution in inserted nodes"))
    }

    /**
     * Whether we have generated but unprocessedNodes nodes.
     * @return true if there are some unprocessedNodes nodes
     */
    fun hasUnprocessedNode() = unprocessedNodes.isNotEmpty()

    /**
     * Gets and removes next unprocessedNodes node
     */
    fun pollNextUnprocessedNode(): Node {
        var heuristicValue = 0
        while (true) {
            val nodeList = unprocessedNodes[heuristicValue]
            if (nodeList != null && nodeList.isNotEmpty()) {
                val node = nodeList[0]
                nodeList.remove(node)
                return node
            }
            heuristicValue++
        }
    }
}