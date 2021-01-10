package ru.dengap.testyoucan

enum class LexicalCount {
    ONE,
    SOME,
    MANY
}

object Lexical {
    fun lexiсalCount(number: Int): LexicalCount {
        val number100 = number % 100
        return when {
            number100 in 5..20 -> LexicalCount.MANY
            number100 % 10 in 2..4 -> LexicalCount.SOME
            number100 % 10 == 1 -> LexicalCount.ONE
            else -> LexicalCount.MANY
        }
    }
}