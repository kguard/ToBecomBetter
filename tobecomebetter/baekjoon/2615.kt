package com.kguard.tobecomebetter.baekjoon

// 실버 1 오목
// 구현, 브루트포스 알고리즘
// 왼쪽 먼저 출력 -> 위에 있는거 출력하기 때문에 우하향, 우상향, 좌우, 상하 이렇게 4개를 확인
// 찾는 순서를 왼->오 , 위->아래, 우하향, 우상향 순으로 검색함
fun main() {
    val list = mutableListOf<MutableList<Int>>()
    list.add(MutableList(21) { -1 }) // 바둑판 주변으로 -1을 채워 넣음으로써 육목을 검사할때 편하게 함
    repeat(19) {
        val l = readln().split(" ").map { it.toInt() }.toMutableList()
        l.add(0, -1)
        l.add(-1)
        list.add(l)
    }
    list.add(MutableList(21) { -1 })

    fun checkLtoR(i: Int, j: Int, check: Int): Boolean {
        if (j <= 15) { // 왼쪽에서 오른쪽으로 찾으려면 j가 15 보다 작아야 됨
            for (t in 0..4) { // 좌 -> 우
                if (list[i][j + t] != check) // 1이 아니면 break
                    return false
                if (t == 4) { // 마지막 까지 갔을 때
                    return if (list[i][j + 5] == check || list[i][j - 1] == check) // 제일 왼쪽이나 제일 오른쪽에 1이 있으면 육목이 되므로 break
                        false
                    else  // 모든 조건을 통과 하면 출력 후 return
                        true
                }
            }
        }
        return false
    }

    fun checkUtoD(i: Int, j: Int, check: Int): Boolean {
        if (i <= 15) { // 위에서 아래로 찾으려면 i가 15 보다 작아야 됨
            for (t in 0..4) { // 위 -> 아래
                if (list[i + t][j] != check) // check 가 아니면 break
                    return false
                if (t == 4) {
                    return if (list[i + 5][j] == check || list[i - 1][j] == check) // 제일 위에나 제일 아래에 check가 있으면 육목이 되므로 break
                        false
                    else  // 모든 조건을 통과 하면 출력 후 return
                        true
                }
            }
        }
        return false
    }

    fun checkRightDown(i: Int, j: Int, check: Int): Boolean {
        if (i <= 15 && j <= 15) { // 우하향으로 찾이 위한 범위
            for (t in 0..4) { // 우하향
                if (list[i + t][j + t] != check) // 내려가며 check가 아니면 break
                    return false
                if (t == 4) {
                    return if (list[i + 5][j + 5] == check || list[i - 1][j - 1] == check) // 제일 왼쪽 위에나 제일 오른쪽 아래에 check가 있으면 육목이 되므로 break
                        false
                    else  // 모든 조건을 통과 하면 출력 후 return
                        true
                }
            }
        }
        return false
    }

    fun checkRightUp(i: Int, j: Int, check: Int): Boolean {
        if (5 <= i && j <= 15) { // 우상향으로 찾기 위한 범위
            for (t in 0..4) { // 우상향
                if (list[i - t][j + t] != check) // 올라가며 check가 아니면 break
                    return false
                if (t == 4) {
                    return if (list[i - 5][j + 5] == check || list[i + 1][j - 1] == check) // 제일 오른쪽 위에나 제일 왼쪽 아래에 check가 있으면 육목이 되므로 break
                        false
                    else  // 모든 조건을 통과 하면 출력 후 return
                        true
                }
            }
        }
        return false
    }
    loop1@ for (i in 1..19) {
        loop2@ for (j in 1..19) {
            if (list[i][j] == 0) // 0이면 넘어감
                continue
            else if (list[i][j] == 1){  // 1일때
                if (checkLtoR(i, j, 1) || checkUtoD(i, j, 1) || checkRightDown(i, j, 1) || checkRightUp(i, j, 1)) {
                    println(1)
                    println("$i $j")
                    return
                }
            }
            else if (list[i][j] == 2){  // 2일때
                if(checkLtoR(i,j,2) || checkUtoD(i,j,2)  || checkRightDown(i,j,2) ||checkRightUp(i,j,2)) {
                    println(2)
                    println("$i $j")
                    return
                }
            }
        }
    }
    println(0) // 모든 조건을 만족하지 않아서 for문들이 종료되면 무승부 이므로 0 출력
}