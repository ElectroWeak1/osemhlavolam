package sk.stuba.fiit.ui.osemhlavolam

import java.util.*

/**
 * Node that represents point in state space. Contains state and list of operators used to get to this state. Using
 * inverted operators we can backtrack to original state. This means we don't need to keep track of previous states as
 * we can create them with operators.
 */
data class Node(
    val state: State,
    val operators: MutableList<Operator> = LinkedList()
) {
    /**
     * Creates new node with by copying this state and operators and applying provided operator to new state and adds
     * operator to list of operators.
     * @param operator to apply to state and add to list of operators
     * @return new node with new state and operators
     * @throws IllegalStateException when operator cannot be applied to current node state
     */
    fun apply(operator: Operator): Result<Node> {
        val newState = state.apply(operator)
        newState.onFailure {
            return Result.failure(IllegalStateException("Cannot apply operator $operator to state\n$this\n" +
                "because new state would not be valid"))
        }
        val newOperators = LinkedList(operators)
        newOperators.add(operator)
        return Result.success(Node(newState.getOrThrow(), newOperators))
    }
}