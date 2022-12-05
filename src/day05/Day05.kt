package day05

import readInput

data class Step(val amount: Int, val takeFrom: Int, val moveTo: Int) {
    companion object {
        fun detail(line: String): Step {
            return line.split(" ").filterIndexed { index, _ -> index % 2 == 1 }.map { it.toInt()}.let{ Step(it[0], it[1]-1, it[2]-1) }
        }
    }
}

fun main() {
    val data = readInput("day05/day05_data")

    val numberOfStacks = data.dropWhile { it.contains("[") }.first().split(" ").filter { it.isNotBlank() }.maxOf { it.toInt() }

    val stacks = List(numberOfStacks) {ArrayDeque<Char>()}
    val steps = mutableListOf<Step>()

    data.filter { it.contains("[") }.map{ line -> line.mapIndexed{ index, char -> if (char.isLetter()) stacks[index/4].addLast(line[index])}}
    data.filter { it.contains("move")}.map { steps.add(Step.detail(it))}

    fun sortCrates(stacks: List<ArrayDeque<Char>>, steps: List<Step>): String {
        steps.map { step -> repeat(step.amount) { stacks[step.moveTo].addFirst(stacks[step.takeFrom].removeFirst())} }
        return stacks.map { it.first() }.joinToString(separator = "")
    }

    fun sortCrates9001(stacks: List<ArrayDeque<Char>>, steps: List<Step>): String {
        steps.map { step ->
            stacks[step.takeFrom].subList(0, step.amount).asReversed()
                .map { stacks[step.moveTo].addFirst(it) }
                .map { stacks[step.takeFrom].removeFirst() }
        }
        return stacks.map { it.first() }.joinToString(separator = "")
    }


    fun part1(stacks: List<ArrayDeque<Char>>, steps: List<Step>) = sortCrates(stacks.map { ArrayDeque(it) }.toList(), steps)
    fun part2(stacks: List<ArrayDeque<Char>>, steps: List<Step>) = sortCrates9001(stacks, steps)

    println(part1(stacks, steps))
    println(part2(stacks, steps))
}
