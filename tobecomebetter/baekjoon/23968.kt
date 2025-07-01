package com.kguard.tobecomebetter.baekjoon

// 브론즈 1 알고리즘 수업 - 버블 정렬 1
// 구현, 정렬, 시뮬레이션
private fun bubble_sort(a: MutableList<Int>, k: Int): Int {
    var count = 0
    for (last in a.size - 1 downTo 1)
        for (i in 0..last - 1)
            if (a[i] > a[i + 1]) {
                count++
                val c = a[i]
                a[i] = a[i + 1]
                a[i + 1] = c
                if (count == k)
                    return i
            }
    return -1
}

fun main() {
    val (n, k) = readln().split(" ").map { it.toInt() }
    val a = readln().split(" ").map { it.toInt() }.toMutableList()
    val count = bubble_sort(a, k)
    println(if (count >= 0) "${a[count]} ${a[count + 1]}" else -1)
}