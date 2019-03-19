package sk.stuba.fiit.ui.osemhlavolam

class ManhattanHeuristic(val state: State, val finalState: State) : Comparable<ManhattanHeuristic> {
    private val value: Int by lazy { computeHeuristic(state, finalState) }

    override fun compareTo(other: ManhattanHeuristic): Int {
        return when {
            value > other.value -> 1
            value < other.value -> -1
            else -> 0
        }
    }

    private fun computeHeuristic(state: State, finalState: State): Int {
        var heuristic = 0
        for (i in 0 until finalState.rowCount) {
            for (j in 0 until finalState.rowCount) {
                val value = finalState.state[i][j]
                val (x, y) = state.findValueIndex(value)
                heuristic += Math.abs(i - x) + Math.abs(j - y)
            }
        }
        return heuristic
    }
}