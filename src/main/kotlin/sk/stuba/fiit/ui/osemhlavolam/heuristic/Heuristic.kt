package sk.stuba.fiit.ui.osemhlavolam.heuristic

import sk.stuba.fiit.ui.osemhlavolam.State

/**
 * Interface for heuristic that computes how close is given state to final state
 */
interface Heuristic {
    /**
     * Computes heuristic for given state
     * @param state state for which to compute heuristic
     * @param finalState state which we want to achieve
     * @return heuristic value of state, lower is better, 0 means two states are equal
     */
    fun compute(state: State, finalState: State): Int
}