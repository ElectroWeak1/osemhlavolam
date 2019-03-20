package sk.stuba.fiit.ui.osemhlavolam

import org.junit.Assert.*
import org.junit.Test

class NodeTest {
    @Test
    fun `Test node complex move`() {
        val state = State(States.operatorMap)
        val node = Node(state)

        val leftNode = node.apply(Operator.LEFT).getOrThrow()
        assertArrayEquals(leftNode.state.map, States.operatorMapLeft)
        assertEquals(leftNode.operators, listOf(Operator.LEFT))

        val upNode = leftNode.apply(Operator.UP).getOrThrow()
        assertArrayEquals(upNode.state.map, States.operatorMapUp)
        assertEquals(upNode.operators, listOf(Operator.LEFT, Operator.UP))

        val rightNode = upNode.apply(Operator.RIGHT).getOrThrow()
        assertArrayEquals(rightNode.state.map, States.operatorMapRight)
        assertEquals(rightNode.operators, listOf(Operator.LEFT, Operator.UP, Operator.RIGHT))

        val downNode = rightNode.apply(Operator.DOWN).getOrThrow()
        assertArrayEquals(downNode.state.map, States.operatorMapDown)
        assertEquals(downNode.operators, listOf(Operator.LEFT, Operator.UP, Operator.RIGHT, Operator.DOWN))
    }

    @Test(expected = IllegalStateException::class)
    fun `Test invalid move`() {
        val state = State(States.basicMap)
        val node = Node(state)

        node.apply(Operator.LEFT).getOrThrow()
    }
}