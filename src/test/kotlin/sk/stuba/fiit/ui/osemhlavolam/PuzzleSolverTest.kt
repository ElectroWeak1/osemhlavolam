package sk.stuba.fiit.ui.osemhlavolam

import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Test

class PuzzleSolverTest {
    @Test
    fun `Test generate possible states`() {
        val solver = PuzzleSolver(
            Node(State(States.basicHeuristicInitialMap), mutableListOf(Operator.RIGHT)),
            Node(State(States.basicHeuristicFinalMap), mutableListOf(Operator.RIGHT), true)
        )
        val nodes = solver.visitNode(solver.initialNode)

        val newNode1 = Node(State(States.basicRightMap), mutableListOf(Operator.RIGHT, Operator.RIGHT))
        val newNode2 = Node(State(States.basicDownMap), mutableListOf(Operator.RIGHT, Operator.DOWN))

        assertThat(nodes, hasItem(newNode1))
        assertThat(nodes, hasItem(newNode2))
    }

    @Test
    fun `Test solve basic state`() {
        val solver = PuzzleSolver(
            Node(State(States.basicMap)),
            Node(State(States.basicFinalMap), inverse = true)
        )
        val finalNode = solve(solver)
        checkSolution(State(States.basicMap), finalNode)
    }

    @Test
    fun `Test solve hard state`() {
        val solver = PuzzleSolver(
            Node(State(States.hardMap)),
            Node(State(States.hardFinalMap), inverse = true)
        )
        val finalNode = solve(solver)
        checkSolution(State(States.hardMap), finalNode)
    }

    private fun solve(solver: PuzzleSolver): Node {
        while (solver.hasUnprocessedNode()) {
            val nextNode = solver.pollNextUnprocessedNode()
            val possibleNodes = solver.visitNode(nextNode)
            val result = solver.insertUnprocessedNodes(possibleNodes)
            if (result.isSuccess) {
                println(result.getOrThrow())
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
        assertEquals(node, finalNode)
    }
}