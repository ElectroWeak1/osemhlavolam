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

    val biggerMapSimpleDown = arrayOf(
        intArrayOf( 1,  2,  3,  4,  5,  6),
        intArrayOf( 7,  8,  9, 10, 11, 12),
        intArrayOf(13, 14, 15, 16, 17,  0),
        intArrayOf(19, 20, 21, 22, 23, 18)
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

    val operatorMap = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 0, 6),
        intArrayOf(7, 5, 8)
    )

    val operatorMapLeft = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 6, 0),
        intArrayOf(7, 5, 8)
    )

    val operatorMapUp = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 6, 8),
        intArrayOf(7, 5, 0)
    )

    val operatorMapRight = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 6, 8),
        intArrayOf(7, 0, 5)
    )

    val operatorMapDown = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 0, 8),
        intArrayOf(7, 6, 5)
    )

    val basicHeuristicInitialMap = arrayOf(
        intArrayOf(1, 2, 3),
        intArrayOf(4, 5, 6),
        intArrayOf(7, 8, 0)
    )

    val basicHeuristicFinalMap = arrayOf(
        intArrayOf(7, 8, 6),
        intArrayOf(5, 4, 3),
        intArrayOf(2, 0, 1)
    )

    val biggerHeuristicInitialMap = arrayOf(
        intArrayOf( 1,  2,  3,  4,  5,  6),
        intArrayOf( 7,  8,  9, 10, 11, 12),
        intArrayOf(13, 14, 15, 16, 17, 18),
        intArrayOf(19, 20, 21, 22, 23,  0)
    )

    val biggerHeuristicFinalMap = arrayOf(
        intArrayOf(11,  2, 3, 15,  6,  5),
        intArrayOf( 7, 21, 9, 10,  1, 12),
        intArrayOf(17, 14, 4, 16, 13, 18),
        intArrayOf(0 , 20, 8, 22, 23, 19)
    )
}