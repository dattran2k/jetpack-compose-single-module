package com.dat.base_compose.common

import com.google.gson.Gson


fun Any?.toJson(): String {
    return Gson().toJson(this)
}

fun <E> List<E>.toArrayList(): ArrayList<E> {
    return ArrayList(this)

}

fun List<String>.contains(s: String?, ignoreCase: Boolean = false): Boolean {
    return any { it.equals(s, ignoreCase) }
}

fun <E> ArrayList<E>.filterNull(): ArrayList<E> {
    val list = filterNotNull().toList()
    clear()
    addAll(list)
    return this
}

fun <T : Any> Iterable<T?>.notNullAndNotEmpty(): List<T>? {
    val filterNotNull = filterNotNull()
    return filterNotNull.ifEmpty { null }
}

fun <T> MutableList<T>.remove(isRemoveAll: Boolean, predicate: (T) -> Boolean) {
    if (isRemoveAll)
        removeAll(predicate)
    else {
        val firstIndexOf = indexOfFirst(predicate)
        if (firstIndexOf > -1)
            removeAt(firstIndexOf)
    }
}
