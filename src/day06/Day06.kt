package day06

import readInput

fun main() {
    val data = readInput("day06/day06_data").first()

    fun findMarker(dataStream: String, startSize: Int): Int {
        return dataStream.withIndex().windowed(startSize, 1).first { window ->
            window.map { it.value }.toSet().size == startSize
        }.last().index + 1
    }

    fun part1():Int = findMarker(data, 4)

    fun part2():Int = findMarker(data, 14)

    println(part1())

    println(part2())
}
