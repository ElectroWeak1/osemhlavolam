package sk.stuba.fiit.ui.osemhlavolam.heuristic

import sk.stuba.fiit.ui.osemhlavolam.Node

class HeuristicComparator : Comparator<Node> {
    override fun compare(node1: Node, node2: Node) = when {
        node1.heuristic > node2.heuristic -> 1
        node1.heuristic < node2.heuristic -> -1
        else -> 0
    }
}