package sk.stuba.fiit.ui.osemhlavolam

import java.util.*

class MultiHashMap<K, V>(val map: HashMap<K, MutableList<V>> = HashMap()) {
    val size: Int
        get() = map.size

    operator fun set(key: K, value: V) {
        val values = map[key]
        if (values == null) {
            val newValues = LinkedList<V>()
            newValues += value
            map[key] = newValues
            return
        }
        values += value
    }

    operator fun get(key: K) = map[key]

    fun contains(key: K, predicate: (V) -> Boolean): Boolean {
        val values = map[key]
        values?.forEach {
            if (predicate(it)) {
                return true
            }
        }
        return false
    }

    fun isNotEmpty() = map.isNotEmpty()
}