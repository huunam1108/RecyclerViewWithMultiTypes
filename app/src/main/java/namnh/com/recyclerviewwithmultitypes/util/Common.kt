package namnh.com.recyclerviewwithmultitypes.util

fun <E> List<E>.removeLast(n: Int): MutableList<E> {
    return dropLast(size - n).toMutableList()
}
