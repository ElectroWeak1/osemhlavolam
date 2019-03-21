package sk.stuba.fiit.ui.osemhlavolam

import sk.stuba.fiit.ui.osemhlavolam.heuristic.Heuristic
import sk.stuba.fiit.ui.osemhlavolam.heuristic.HeuristicComparator
import sk.stuba.fiit.ui.osemhlavolam.heuristic.LinearHeuristic
import sk.stuba.fiit.ui.osemhlavolam.heuristic.ManhattanHeuristic
import java.util.*

/**
 * Solver for 8-puzzle. Contains methods that help solve 8-puzzle.
 */
class PuzzleSolver(
    val initialNode: Node,
    private val finalNode: Node,
    private val heuristic: Heuristic = LinearHeuristic()
) {
    private val heuristicComparator = HeuristicComparator()
    private val unprocessedNodes = PriorityQueue<Node>(heuristicComparator)
    private val visitedStates = MultiHashMap<Int, Node>()
    private var nextNormal = true

    init {
//        initialNode.heuristic = heuristic.compute(initialNode.state, finalNode.state)
//        finalNode.heuristic = heuristic.compute(finalNode.state, initialNode.state)
        unprocessedNodes.add(initialNode)
        unprocessedNodes.add(finalNode)
    }

    /**
     * Generates all possible states from provided node by trying to apply all possible operators returning those
     * states that are valid after applying operator. Puts provided node to list of visited nodes.
     * @param node node we try to apply all operators
     * @return list of all possible states by applying all operators on provided node
     */
    fun visitNode(node: Node): List<Node> {
        visitedStates[node.state.hashCode()] = node

        val nodes = mutableListOf<Node>()
        Operator.values().forEach { operator ->
            val result = node.apply(operator)
            result.onSuccess { newNode ->
                if (!visitedStates.contains(newNode.state.hashCode()) { newNode.state == it.state }) {
                    nodes.add(newNode)
                }
            }
        }

        if (visitedStates.size % 10000 == 0) {
            println("Visited: ${visitedStates.size}, Unprocessed: ${unprocessedNodes.size}, Depth: ${node.operators.size}")
        }
        return nodes
    }

    fun insertUnprocessedNodes(nodes: List<Node>): Result<Node> {
        nodes.forEach { node ->
            if (node.state == finalNode.state) {
                return Result.success(node)
            }
//            node.heuristic = heuristic.compute(node.state, if (node.inverse) initialNode.state else finalNode.state)
            unprocessedNodes.add(node)
        }
        return Result.failure(IllegalStateException("Couldn't find solution in inserted nodes"))
    }

    fun isInverseInVisited(node: Node) = visitedStates.contains(node.state.hashCode()) {
        node.state == it.state && node.inverse != it.inverse
    }

    fun getInverseVisitedNode(node: Node) = visitedStates[node.state.hashCode()]!!.find {
        node.state == it.state && node.inverse != it.inverse
    }!!

    fun combineNodes(node1: Node, node2: Node): Node {
        val combinedOperators = mutableListOf<Operator>()
        when {
            node1.inverse -> {
                combinedOperators.addAll(node2.operators)
                val operators = node1.operators.map { it.inverseOperator() }.reversed()
                combinedOperators.addAll(operators)
            }
            node2.inverse -> {
                combinedOperators.addAll(node1.operators)
                val operators = node2.operators.map { it.inverseOperator() }.reversed()
                combinedOperators.addAll(operators)
            }
        }
        return Node(finalNode.state, combinedOperators)
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
        val node = unprocessedNodes.find { if (nextNormal) !it.inverse else it.inverse }!!
        nextNormal = !nextNormal
        unprocessedNodes.remove(node)
        return node
    }
}