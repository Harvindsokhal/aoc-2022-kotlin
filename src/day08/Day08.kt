package day08

import readInput

enum class Direction { LEFT, RIGHT, UP, DOWN }

fun main() {
    val data = readInput("day08/day08_data")

    fun visibleTrees(x: Int, y: Int) =
        if (x == 0 || x == data[0].lastIndex || y == 0 || y == data.lastIndex) {
            true
        } else {
            (0 until x).none { data[y][it] >= data[y][x] } ||
                (x + 1 until data[0].length).none { data[y][it] >= data[y][x] } ||
                (0 until y).none { data[it][x] >= data[y][x] } ||
                (y + 1 until data.size).none { data[it][x] >= data[y][x] }
        }

    fun part1(list: List<String>) =
        list.mapIndexed { y, line ->
            line.filterIndexed { x, _ ->
                visibleTrees(x, y)
            }
        }.sumOf { it.length }

    fun viewingDistance(direction: Direction, x: Int, y: Int): Int {
        val range = when (direction) {
            Direction.LEFT -> (x - 1 downTo 0)
            Direction.RIGHT -> (x + 1 until data[0].length)
            Direction.UP -> (y - 1 downTo 0)
            Direction.DOWN -> (y + 1 until data.size)
        }
        var blockingTreeMet = false
        return range.takeWhile {
            val tree = when (direction) {
                Direction.LEFT, Direction.RIGHT -> data[y][it]
                Direction.UP, Direction.DOWN -> data[it][x]
            }
            if (blockingTreeMet) return@takeWhile false
            if (tree >= data[y][x]) blockingTreeMet = true
            true
        }.count()
    }

    fun scenicScore(x: Int, y: Int) =
        viewingDistance(Direction.LEFT, x, y) * viewingDistance(Direction.RIGHT, x, y) * viewingDistance(Direction.UP, x, y) * viewingDistance(Direction.DOWN, x, y)

    fun part2(list: List<String>) = list.mapIndexed { y, line ->
        List(line.length) { x ->
            scenicScore(x, y)
        }
    }.flatten().max()

    println(part1(data))
    println(part2(data))
}
