package sk.stuba.fiit.ui.osemhlavolam

import org.hamcrest.CoreMatchers.*
import org.junit.Assert.*
import org.junit.Test

class PuzzleSolverTest {
    @Test
    fun `Test generate possible states`() {
        val solver = PuzzleSolver(
            Node(State(States.basicHeuristicInitialMap), mutableListOf(Operator.UP)),
            Node(State(States.basicHeuristicFinalMap), mutableListOf(Operator.UP))
        )
        val nodes = solver.generatePossibleNodes(solver.initialNode)

        val newNode1 = Node(State(States.basicRightMap), mutableListOf(Operator.UP, Operator.RIGHT))
        val newNode2 = Node(State(States.basicDownMap), mutableListOf(Operator.UP, Operator.DOWN))

        assertThat(nodes, hasItem(newNode1))
        assertThat(nodes, hasItem(newNode2))
    }
}