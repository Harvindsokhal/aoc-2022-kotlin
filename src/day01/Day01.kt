package day01

import readInput

fun main() {
    val data = readInput("day01/day1_data")
    fun maxCalories(list: List<String>): List<Int> {
        var total = 0
        return mutableListOf(0).apply {
            list.forEach { calorie ->
                if (calorie.isBlank()) {
                    this.add(total)
                    total = 0
                } else {
                    total += calorie.toInt()
                }
            }
            this.add(total)
        }
    }

    fun part1(input: List<String>) = maxCalories(input).max()

    fun part2(input: List<String>) = maxCalories(input).sortedDescending().slice(0..2).sum()

    println(part1(data))
    println(part2(data))

}
