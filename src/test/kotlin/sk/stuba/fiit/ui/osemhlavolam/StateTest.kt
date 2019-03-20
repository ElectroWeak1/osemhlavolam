package sk.stuba.fiit.ui.osemhlavolam

import org.junit.Assert.*
import org.junit.Test

class StateTest {
    @Test
    fun `Test map size basic`() {
        val state = State(States.basicMap)
        assertEquals(3, state.rows)
        assertEquals(3, state.columns)
    }

    @Test
    fun `Test map size bigger`() {
        val state = State(States.biggerMap)
        assertEquals(4, state.rows)
        assertEquals(6, state.columns)
    }

    @Test
    fun `Test finding empty tile basic`() {
        val state = State(States.basicMap)
        assertEquals(2 to 2, state.emptyTilePosition)
    }

    @Test
    fun `Test finding empty tile bigger`() {
        val state = State(States.biggerMap)
        assertEquals(3 to 5, state.emptyTilePosition)
    }

    @Test
    fun `Test finding empty tile center`() {
        val state = State(States.basicCenterMap)
        assertEquals(1 to 1, state.emptyTilePosition)
    }
}