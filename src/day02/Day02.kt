package day02

import readInput

fun main() {
    val data = readInput("day02/day02_data")

    fun totalScore(list:List<String>): Int {
        val myMap: HashMap<String, String> = hashMapOf("X" to "Rock", "Y" to "Paper", "Z" to "Scissors")
        val oppMap: HashMap<String, String> = hashMapOf("A" to "Rock", "B" to "Paper", "C" to "Scissors")
        val choiceStack = listOf("Paper", "Rock", "Scissors")

        val pickPoints = mapOf("X" to 1, "Y" to 2, "Z" to 3)

        val results = arrayOf(arrayOf("t", "c", "u"), arrayOf("u", "t", "c"), arrayOf("c", "u", "t"))

        var score = 0
        list.forEach { pair ->
            val myChoice = pair.split(' ')[1]
            val oppChoice = pair.split(' ')[0]
            score += pickPoints[myChoice]!!

            when(results[choiceStack.indexOf(oppMap[oppChoice])][choiceStack.indexOf(myMap[myChoice])]) {
                "c" -> score+=0
                "t" -> score+=3
                "u" -> score+=6
            }
        }
        return score
    }

    fun totalScoreWithStrat(list:List<String>): Int {
        val myMap: HashMap<String, String> = hashMapOf("X" to "c", "Y" to "t", "Z" to "u")
        val oppMap: HashMap<String, String> = hashMapOf("A" to "Rock", "B" to "Paper", "C" to "Scissors")
        val choiceStack = listOf("Paper", "Rock", "Scissors")

        val pickPoints = mapOf("Rock" to 1, "Paper" to 2, "Scissors" to 3)

        val results = arrayOf(arrayOf("t", "c", "u"), arrayOf("u", "t", "c"), arrayOf("c", "u", "t"))

        var score = 0
        list.forEach { pair ->
            val myChoice = pair.split(' ')[1]
            val oppChoice = pair.split(' ')[0]
            score += pickPoints[choiceStack[(results[choiceStack.indexOf(oppMap[oppChoice])].toList().indexOf(myMap[myChoice]))]]!!

            when(myMap[myChoice]) {
                "c" -> score+=0
                "t" -> score+=3
                "u" -> score+=6
            }
        }
        return score
    }

    fun part1(list:List<String>) = totalScore(list)
    fun part2(list:List<String>) = totalScoreWithStrat(list)


    println(part1(data))
    println(part2(data))
}



