package sk.stuba.fiit.ui.osemhlavolam

class PuzzleSolver(
    private val initialState: State,
    private val finalState: State
) {
    val unprocessed = mutableListOf<Node>()

    fun solve(): List<Direction> {
        unprocessed.add(Node(initialState, heuristic = ManhattanHeuristic(initialState, finalState)))
        unprocessed.add(Node(finalState, inverse = true, heuristic = ManhattanHeuristic(finalState, finalState)))

        while (true) {
            val node = unprocessed.first()
            unprocessed.remove(node)
            val nodes = generateStates(node)
            for (it in nodes) {
                if (unprocessed.contains(it)) {
                    unprocessed.find { unprocessedNode -> unprocessedNode == it }?.let { secondNode ->
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
                        return allDirections
                    }
                } else {
                    unprocessed.add(it)
                    unprocessed.sort()
                }
            }
        }
    }

    fun generateStates(node: Node): List<Node> {
        val nodes = mutableListOf<Node>()
        Direction.values().forEach { direction ->
            val result = node.move(direction)
            result.onSuccess { nodes.add(it) }
        }
        return nodes
    }

    fun inverseDirections(directions: MutableList<Direction>) {
        val inversedDirections = mutableListOf<Direction>()
        directions.asReversed().forEach {
            inversedDirections.add(it.getInverseDirection())
        }
        directions.clear()
        directions.addAll(inversedDirections)
    }

}