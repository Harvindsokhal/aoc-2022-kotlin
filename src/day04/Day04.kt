package day04

import readInput

fun main() {
    val data = readInput("day04/day04_data")

    fun pairFullOverlap(list: List<String>): Int {
        var count = 0
        list.forEach { pair ->
             val pairOne = pair.split(',')[0].split('-')
             val pairTwo = pair.split(',')[1].split('-')
             val firstRange = pairOne[0].toInt()..pairOne[1].toInt()
             val secondRange = pairTwo[0].toInt()..pairTwo[1].toInt()

             if (firstRange.all { secondRange.contains(it)} || secondRange.all { firstRange.contains(it)}) {
                 count +=1
             }
        }
        return count
    }

    fun pairPartOverlap(list: List<String>): Int {
        var count = 0
        list.forEach { pair ->
            val pairOne = pair.split(',')[0].split('-')
            val pairTwo = pair.split(',')[1].split('-')
            val firstRange = pairOne[0].toInt()..pairOne[1].toInt()
            val secondRange = pairTwo[0].toInt()..pairTwo[1].toInt()

            if (firstRange.any { secondRange.contains(it)} || secondRange.any { firstRange.contains(it)}) {
                count +=1
            }
        }
        return count
    }
    fun part1 (list:List<String>) = pairFullOverlap(list)
    fun part2 (list:List<String>) = pairPartOverlap(list)

    println(part1(data))
    println(part2(data))
}


