package sk.stuba.fiit.ui.osemhlavolam

import java.util.*

/**
 * Node that represents point in state space. Contains state and list of operators used to get to this state. Using
 * inverted operators we can backtrack to original state. This means we don't need to keep track of previous states as
 * we can create them with operators.
 */
class Node(
    val state: State,
    val operators: MutableList<Operator> = LinkedList()
) {
    /**
     * Creates new node with by copying this state and operators and applying provided operator to new state and adds
     * operator to list of operators.
     * @param operator to apply to state and add to list of operators
     * @return new node with new state and operators
     */
    fun apply(operator: Operator): Node {
        val newState = state.apply(operator).getOrThrow()
        val newOperators = LinkedList(operators)
        newOperators.add(operator)
        return Node(newState, newOperators)
    }
}