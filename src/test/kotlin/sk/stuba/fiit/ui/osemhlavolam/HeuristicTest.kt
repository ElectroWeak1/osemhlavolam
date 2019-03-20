package sk.stuba.fiit.ui.osemhlavolam

import org.junit.Assert.*
import org.junit.Test
import sk.stuba.fiit.ui.osemhlavolam.heuristic.Heuristic
import sk.stuba.fiit.ui.osemhlavolam.heuristic.LinearHeuristic
import sk.stuba.fiit.ui.osemhlavolam.heuristic.ManhattanHeuristic

class HeuristicTest {
    @Test
    fun `Simple Manhattan heuristic test`() {
        val state = State(States.basicHeuristicInitialMap)
        val finalState = State(States.basicHeuristicFinalMap)

        val heuristic: Heuristic = ManhattanHeuristic()
        assertEquals(15, heuristic.compute(state, finalState))
    }

    @Test
    fun `Simple linear heuristics test`() {
        val state = State(States.basicHeuristicInitialMap)
        val finalState = State(States.basicHeuristicFinalMap)

        val heuristic: Heuristic = LinearHeuristic()
        assertEquals(9, heuristic.compute(state, finalState))
    }

    @Test
    fun `Bigger Manhattan heuristic test`() {
        val state = State(States.biggerHeuristicInitialMap)
        val finalState = State(States.biggerHeuristicFinalMap)

        val heuristic: Heuristic = ManhattanHeuristic()
        assertEquals(37, heuristic.compute(state, finalState))
    }

    @Test
    fun `Bigger linear heuristics test`() {
        val state = State(States.biggerHeuristicInitialMap)
        val finalState = State(States.biggerHeuristicFinalMap)

        val heuristic: Heuristic = LinearHeuristic()
        assertEquals(12, heuristic.compute(state, finalState))
    }
}