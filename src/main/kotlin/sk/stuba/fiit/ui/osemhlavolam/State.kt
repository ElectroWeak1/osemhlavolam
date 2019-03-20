package sk.stuba.fiit.ui.osemhlavolam

/**
 * Represents constant state for 8-puzzle with empty tile in two-dimensional array of ints
 */
class State(
    private val map: Map
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

    companion object {
        private const val EMPTY_TILE = 0
    }
}