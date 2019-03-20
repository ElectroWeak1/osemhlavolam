package sk.stuba.fiit.ui.osemhlavolam

object States {
    val emptyMap = arrayOf<IntArray>()

    val basicMap = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 0)
    )

    val biggerMap = arrayOf(
        intArrayOf( 1,  2,  3,  4,  5,  6),
        intArrayOf( 7,  8,  9, 10, 11, 12),
        intArrayOf(13, 14, 15, 16, 17, 18),
        intArrayOf(19, 20, 21, 22, 23,  0)
    )

    val basicCenterMap = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 0, 6),
        intArrayOf(7, 5, 8)
    )

    val basicWithoutEmptyTile = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 9)
    )
}