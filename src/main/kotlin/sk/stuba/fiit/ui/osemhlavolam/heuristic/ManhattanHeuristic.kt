package sk.stuba.fiit.ui.osemhlavolam.heuristic

import sk.stuba.fiit.ui.osemhlavolam.State
import sk.stuba.fiit.ui.osemhlavolam.distance

class ManhattanHeuristic : Heuristic {
    override fun compute(state: State, finalState: State): Int {
        var heuristic = 0
        for (i in 0 until state.rows) {
            for (j in 0 until state.columns) {
                val tile = state.map[i][j]
                if (tile == State.EMPTY_TILE) continue
                val tilePosition = finalState.findTilePosition(tile)
                heuristic += tilePosition.distance(i to j)
            }
        }
        return heuristic
    }
}