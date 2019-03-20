package sk.stuba.fiit.ui.osemhlavolam.heuristic

import sk.stuba.fiit.ui.osemhlavolam.State

/**
 * Simple heuristic which computes how many tiles are out of position in relation to final state.
 */
class LinearHeuristic : Heuristic {
    override fun compute(state: State, finalState: State): Int {
        var heuristic = 0
        for (i in 0 until state.rows) {
            for (j in 0 until state.columns) {
                val tile = state.map[i][j]
                val tilePosition = finalState.findTilePosition(tile)
                if (tilePosition != i to j) {
                    heuristic++
                }
            }
        }
        return heuristic
    }
}