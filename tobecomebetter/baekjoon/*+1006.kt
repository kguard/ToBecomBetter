package com.kguard.tobecomebetter.baekjoon

import kotlin.math.min

// 플래티넘 3 습격자 초라기
// 다이나믹 프로그래밍(dp)
// 3가지의 경우의 수를 저장하는 방식으로 문제 해결 (DP)
// 1. 위 아래가 모두 채워져야 할 경우 (마지막과 처음이 연결되지 않을 경우)
// 2. 위만 채워져야 할 경우 (마지막 아래와 처음 아래가 연결될 경우)
// 3. 아래만 채워져야 할 경우 (마지막 위와 처음 위가 연결될 경우)
// n번쨰 행까지 고려했을 때 dp[0][n] : 위 아래가 모두 채워졌을 때, dp[1][n] : 위만 채워졌을 떄, dp[2][n] : 아래만 채워졌을 때
// 자세한 코드 설명은 아래 참고
fun main() {
    val t = readln().toInt()
    repeat(t) {
        val (n, w) = readln().split(" ").map { it.toInt() }
        val dp = MutableList(3) { MutableList(n+1) { 0 } } // n번쨰 행까지 고려했을 때 dp[0][n] : 위 아래가 모두 채워졌을 때, dp[1][n] : 위만 채워졌을 떄, dp[2][n] : 아래만 채워졌을 때
        val section = MutableList(2) { mutableListOf<Int>() }
        repeat(2) { times ->
            section[times].add(0)
            readln().split(" ").map { section[times].add(it.toInt()) }
        }
        // n번쨰 행까지 고려했을 때 dp[0][n] : 위 아래가 모두 채워졌을 때, dp[1][n] : 위만 채워졌을 떄, dp[2][n] : 아래만 채워졌을 때
        fun solve(){ // 1번째 부터 끝까지 채워주기 위한 함수
            for (i in 2 .. n) {
                val up = if (section[0][i - 1] + section[0][i] <= w) 1 else 2 // i, i-1 번쨰 위에 2개를 연결했을 때 w를 넘지 않으면 연결가능해서 1개 아니면 따로따로 2개
                val down = if (section[1][i - 1] + section[1][i] <= w) 1 else 2 // i, i-1 번째 아래 2개를 연결했을 때 w를 넘지 않으면 연결가능해서 1개 아니면 따로따로 2개
                val vertical = if (section[0][i] + section[1][i] <= w) 1 else 2 // i번째 위 아래 1개씩 연결했을 때 w를 넘지 않으면 연결가능해서 1개 아니면 따로따로 2개

                // i번째 위 아래가 둘다 채워져 있는 경우의 수는
                // 1. (i-1번째 까지 위 아래가 채워져 있는 경우의 수) + (i번째 위 아래를 연결했을 때의 경우의 수)
                // 2. (i-2번째 까지 위 아래가 채워져 있는 경우의 수) + (i, i-1 번째 위에 2개를 연결했을 때 경우의 수) + (i, i-1 번째 아래 2개를 연결했을 때 경우의 수)
                // 3. (i-1번째 에서 아래만 채워져 있는 경우의 수) + (i, i-1 번째 위에 2개를 연결했을 떄 경우의 수) + 1 // i번째 아래를 채우기 위해서 1
                // 4. (i-1번째 에서 위에만 채워져 있는 경우의 수) + (i, i-1 번째 아래 2개를 연결했을 떄 경우의 수) + 1 // i번째 위를 채우기 위해서 1
                dp[0][i] = minOf(dp[0][i - 1] + vertical, dp[0][i - 2] + up + down, dp[2][i - 1] + up + 1, dp[1][i - 1] + down + 1)
                // i번째 위에만 채워져 있는 경우의 수는
                // 1. (i-1번째 에서 아래만 채워져 있는 경우의 수) + (i, i-1 번째 위에 2개를 연결했을 떄 경우의 수)
                // 2. (i번째 까지 위 아래가 채워져 있는 경우의 수) + 1 // i번째 위를 채우기 위해서 1
                dp[1][i] = min(dp[2][i - 1] + up, dp[0][i - 1] + 1)
                // i번째 아래만 채워져 있는 경우의 수는
                // 1. (i-1번째 에서 위에만 채워져 있는 경우의 수) + (i, i-1 번째 아래 2개를 연결했을 떄 경우의 수)
                // 2. (i번째 까지 위 아래가 채워져 있는 경우의 수) + 1 // i번째 아래를 채우기 위해서 1
                dp[2][i] = min(dp[1][i - 1] + down, dp[0][i - 1] + 1)
            }
        }
        var answer = 30000 // n 최대 갯수가 10000 까지 인데 그럼 소대의 최대 수는 20000까지 나올 수 있기 때문에 30000으로 설정
        // 선형 구조가 아니라 원형 구조 이기 때문에 마지막과 처음이 연결되어 있는지 확인해야 됨
        // 1. 마지막과 처음이 연결되지 않았을 때 초기화
        dp[0][1] = if (section[0][1] + section[1][1] <= w) 1 else 2
        dp[1][1] = 1
        dp[2][1] = 1
        solve()
        answer = min(answer, dp[0][n])

        // 2. 처음 위와 마지막 위가 연결되었을 때
        if (section[0][1] + section[0][n] <= w) {
            dp[0][1] = 2 // 처음 위가 마지막 위랑 연결되어 있어서 처음 위가 처음 아래와 연결될 수 없으니 2개로 시작
            dp[1][1] = 1
            dp[2][1] = 1
            val origin = section[0][1]
            section[0][1] = 30000 // 처음 위는 마지막 위와 연결 되어 있으니 계산할 수 없도록 Max값 삽입
            solve()
            answer = min(answer, dp[2][n]) // 마지막번째 에서 아래만 채워져 있는 경우를 구하면됨 -> 처음 위 와 마지막 위가 연결된 경우의 수는 처음에 추가함
            section[0][1] = origin
        }
        // 3. 처음 아래와 마지막 아래가 연결되었을 때
        if(section[1][1] + section[1][n] <= w){
            dp[0][1] = 2 // 처음 아래가 마지막 아래랑 연결되어 있어서 처음 아래가 처음 위와 연결될 수 없으니 2개로 시작
            dp[1][1] = 1
            dp[2][1] = 1
            val origin = section[1][1]
            section[1][1] = 30000 // 처음 아래는 마지막 아래와 연결 되어 있으니 계산할 수 없도록 Max값 삽입
            solve()
            answer = min(answer, dp[1][n]) // 마지막번째 에서 위만 채워져 있는 경우를 구하면됨 -> 처음 아래 와 마지막 아래가 연결된 경우의 수는 처음에 추가함
            section[1][1] = origin
        }
        // 4. 처음 아래와 마지막 아래가 연결되고, 처음 위와 마지막 위가 연결되었을 때
        if(section[0][1] + section[0][n] <= w && section[1][1] + section[1][n] <= w){
            dp[0][1] = 2 // 처음 아래와 마지막 아래가 연결되어 있고, 처음 위와 마지막 위가 연결되어 있으니 2개로 시작
            dp[1][1] = 1
            dp[2][1] = 1
            val origin0 = section[0][1]
            val origin1 = section[1][1]
            section[0][1] = 30000 // 처음 위는 마지막 위와 연결 되어 있으니 계산할 수 없도록 Max값 삽입
            section[1][1] = 30000 // 처음 아래는 마지막 아래와 연결 되어 있으니 계산할 수 없도록 Max값 삽입
            solve()
            answer = min(answer, dp[0][n-1]) // 마지막 -1 번째 에서 위 아래 둘다 채워져 있는 경우를 구하면됨 -> 처음 아래와 마지막 아래가 연결되어 있고, 처음 위가 마지막 위가 연결되어 있기 때문에 -> 두 상황은 모두 처음에 계산함
            section[0][1] = origin0
            section[1][1] = origin1
        }
        // 5. n이 1개 알떄 (위 아래 딱 2개 있을 때)
        if (n == 1)
            answer = if (section[0][1] + section[1][1] <= w) 1 else 2
        print("$answer\n")
    }
}