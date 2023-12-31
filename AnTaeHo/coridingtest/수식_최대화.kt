package coridingtest

import java.lang.Exception
import kotlin.math.abs

fun main() {
    val sol = Sol()
    println(sol.maximize("100-200*300-500+20"))
}

class Sol {
    fun maximize(expression: String): Long {
        var answer: Long = 0

        val priority = arrayOf(
            arrayOf('+', '-', '*'),
            arrayOf('+', '*', '-'),
            arrayOf('-', '+', '*'),
            arrayOf('-', '*', '+'),
            arrayOf('*', '+', '-'),
            arrayOf('*', '-', '+')
        )

        for (case in priority) {
            println(case.contentToString())
            answer = dfs(expression, 0, case).let {
                if (abs(it) > answer) {
                    abs(it)
                } else answer
            }
        }
        return answer
    }

    private fun dfs(expression: String, depth: Int, case: Array<Char>): Long {
        if (expression.isLong()) return expression.toLong()
        val tokens = expression.split(case[depth])
        var result: Long? = null
        for (token in tokens) {
            val value = dfs(token, depth + 1, case)
            result = if (result == null) {
                value
            } else {
                calc(result, value, case[depth])
            }
        }
        return result!!
    }

    private fun String.isLong(): Boolean {
        return try {
            this.toLong()
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun calc(a: Long, b: Long, op: Char): Long {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            else -> throw IllegalArgumentException()
        }
    }
}
