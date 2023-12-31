package coridingtest

fun main() {

}

class 멀리_뛰기 {
    fun solution(n: Int): Long {
        val dp = LongArray(n+1)
        dp[0] = 1
        dp[1] = 1
        if(n < 2)
            return dp[n]
        for(i in 2 ..n){
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1234567
        }
        return dp[n]
    }
}