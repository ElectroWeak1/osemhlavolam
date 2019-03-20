package sk.stuba.fiit.ui.osemhlavolam.heuristic

import sk.stuba.fiit.ui.osemhlavolam.State

interface Heuristic {
    fun compute(state: State, finalState: State): Int
}