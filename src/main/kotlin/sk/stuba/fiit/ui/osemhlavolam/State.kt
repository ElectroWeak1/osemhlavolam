package sk.stuba.fiit.ui.osemhlavolam

/**
 * Represents constant state for 8-puzzle with empty tile in two-dimensional array of ints
 */
class State(
    val map: Map
) {
    val rows = map.size
    val columns = if (map.isNotEmpty()) map[0].size else 0
    val emptyTilePosition: Position by lazy { findEmptyTile() }

    /**
     * Finds position of empty tile in map
     * @return coordinates of empty tile position in map
     */
    private fun findEmptyTile(): Position {
        if (rows <= 0 || columns <= 0) {
            throw IllegalStateException("State must have at least 1 row and 1 column (rows = $rows, columns = $columns)")
        }

        for (i in 0 until map.size) {
            for (j in 0 until map[0].size) {
                if (map[i][j] == EMPTY_TILE) {
                    return i to j
                }
            }
        }

        throw IllegalStateException("Could not find empty tile in map\n$map")
    }

    /**
     * Applies operator to 8-puzzle state and creates a result with new state. If operator cannot be applied to that
     * state, returns result with exception.
     * @return new state if operator can be applied, else result with [IllegalStateException]
     */
    fun apply(operator: Operator): Result<State> {
        val movedTilePosition = emptyTilePosition.first + operator.row to emptyTilePosition.second + operator.column
        if (movedTilePosition.first < 0 || movedTilePosition.second < 0
            || movedTilePosition.first >= rows || movedTilePosition.second >= columns
        ) {
            return Result.failure(IllegalStateException("Cannot apply operator $operator to state\n$this\n" +
                    "because new state would not be valid"))
        }

        val newState = copy()

        newState.map[emptyTilePosition.first][emptyTilePosition.second] =
            newState.map[movedTilePosition.first][movedTilePosition.second]
        newState.map[movedTilePosition.first][movedTilePosition.second] = 0

        return Result.success(newState)
    }

    private fun copy() = State(map.deepCopy())

    companion object {
        private const val EMPTY_TILE = 0
    }
}