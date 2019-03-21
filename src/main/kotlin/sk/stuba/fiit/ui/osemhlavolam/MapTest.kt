package sk.stuba.fiit.ui.osemhlavolam

val hardMap = arrayOf(
    intArrayOf(12,  7,  9,  4),
    intArrayOf(15,  6,  8,  5),
    intArrayOf( 0, 14, 10, 11),
    intArrayOf(13,  2,  3,  1)
)

val hardFinalMap = arrayOf(
    intArrayOf( 1,  2,  3,  4),
    intArrayOf( 5,  6,  7,  8),
    intArrayOf( 9, 10, 11, 12),
    intArrayOf(13, 14, 15,  0)
)

fun main() {
    val solver = PuzzleSolver(
        Node(State(hardMap)),
        Node(State(hardFinalMap), inverse = true)
    )
    val startTime = System.currentTimeMillis()
    val finalNode = solve(solver)
    println("Time taken: ${System.currentTimeMillis() - startTime}")
    checkSolution(State(hardMap), finalNode)
}

private fun solve(solver: PuzzleSolver): Node {
    while (solver.hasUnprocessedNode()) {
        val nextNode = solver.pollNextUnprocessedNode()
        if (solver.isInverseInVisited(nextNode)) {
            val visitedNode = solver.getInverseVisitedNode(nextNode)
            val finalNode = solver.combineNodes(nextNode, visitedNode)
            println("Solution found (used operators = ${finalNode.operators.size})")
            println(finalNode.operators)
            return finalNode
        }
        val possibleNodes = solver.visitNode(nextNode)
        val result = solver.insertUnprocessedNodes(possibleNodes)
        if (result.isSuccess) {
            println("Found result but not taking into account ${result.getOrThrow()}")
            return result.getOrThrow()
        }
    }
    throw IllegalStateException("No more unprocessed nodes to move to")
}

private fun checkSolution(initialState: State, finalNode: Node) {
    var node = Node(initialState)
    finalNode.operators.forEach {
        node = node.apply(it).getOrThrow()
    }
    assert(node == finalNode)
}