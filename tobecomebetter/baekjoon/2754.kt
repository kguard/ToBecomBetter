package com.kguard.tobecomebetter.baekjoon

// 브론즈 3 학점계산
// 구현, 문자열
fun main() {
    val n = readln()
    when (n) {
        "A+" -> {
            println(4.3)
        }

        "A0" -> {
            println(4.0)
        }

        "A-" -> {
            println(3.7)
        }

        "B+" -> {
            println(3.3)
        }

        "B0" -> {
            println(3.0)
        }

        "B-" -> {
            println(2.7)
        }

        "C+" -> {
            println(2.3)
        }

        "C0" -> {
            println(2.0)
        }

        "C-" -> {
            println(1.7)
        }

        "D+" -> {
            println(1.3)
        }

        "D0" -> {
            println(1.0)
        }

        "D-" -> {
            println(0.7)
        }

        "F" -> {
            println(0.0)
        }
    }
}