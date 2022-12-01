package day01

import readInput

fun main() {
    val data = readInput("day01/day1_data")
    fun maxCalories(list: List<String>): Int {
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
        }.max()

    }

    fun part1(input: List<String>) = maxCalories(input)
    println(part1(data))
}
