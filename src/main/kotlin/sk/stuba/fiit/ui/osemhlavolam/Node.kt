package sk.stuba.fiit.ui.osemhlavolam

import java.util.*

/**
 * Node that represents point in state space. Contains state and list of operators used to get to this state. Using
 * inverted operators we can backtrack to original state. This means we don't need to keep track of previous states as
 * we can create them with operators.
 */
open class Node(
    val state: State,
    val operators: MutableList<Operator> = LinkedList(),
    val inverse: Boolean = false,
    var heuristic: Int = 0
) {
    /**
     * Creates new node with by copying this state and operators and applying provided operator to new state and adds
     * operator to list of operators.
     * @param operator to apply to state and add to list of operators
     * @return new node with new state and operators
     * @throws IllegalStateException when operator cannot be applied to current node state
     */
    fun apply(operator: Operator): Result<Node> {
        if (operators.isNotEmpty() && operators.last() == operator.inverseOperator()) {
            return Result.failure(IllegalStateException("Cannot apply previous operator $operator to state\n$this\n"))
        }
        val newState = state.apply(operator)
        newState.onFailure {
            return Result.failure(IllegalStateException("Cannot apply operator $operator to state\n$this\n" +
                "because new state would not be valid"))
        }
        val newOperators = LinkedList(operators)
        newOperators.add(operator)
        return Result.success(Node(newState.getOrThrow(), newOperators, inverse))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Node) return false

        if (state != other.state) return false
        if (operators != other.operators) return false
        if (inverse != other.inverse) return false
        if (heuristic != other.heuristic) return false

        return true
    }

    override fun hashCode(): Int {
        var result = state.hashCode()
        result = 31 * result + operators.hashCode()
        result = 31 * result + inverse.hashCode()
        result = 31 * result + heuristic
        return result
    }

    override fun toString() = "Node($state, $operators, $inverse, $heuristic)"
}