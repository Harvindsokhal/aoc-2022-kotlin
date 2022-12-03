package day03

import readInput

fun main() {
    val data = readInput("day03/day03_data")
    val alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    fun sortRucksack(list: List<String>): Int {
        var sum = 0
        list.forEach { item ->
            val half = item.length / 2
            val pt1 = item.slice(0 until half).toCharArray()
            val pt2 = item.slice(half until item.length).toCharArray()

            val common = pt1.intersect(pt2.toSet()).take(1).first()
            sum += alphabet.indexOf(common) + 1

        }
        return sum
    }

    fun sortRucksackGroup(list: List<String>): Int {
        val grouped = list.chunked(3)
        var sum = 0
        grouped.forEach {
            val group = it.map { it.toCharArray() }
            val first = group[0]
            val second = group[1]
            val third = group[2]

            val common = first.intersect(second.toSet()).intersect(third.toSet()).take(1).first()
            sum+= alphabet.indexOf(common) + 1
        }
        return sum
    }

    fun part1(list:List<String>) = sortRucksack(list)
    fun part2(list:List<String>) = sortRucksackGroup(list)


    println(part1(data))
    println(part2(data))
}
