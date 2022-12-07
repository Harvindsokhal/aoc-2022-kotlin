package day07

import readInput

private data class Directory(
    val name: String,
    val parent: Directory? = null,
    val dirs: MutableList<Directory> = mutableListOf(),
    val files: MutableList<File> = mutableListOf()
) {
    fun allDirs(): List<Directory> = dirs + dirs.flatMap { it.allDirs() }
    fun size(): Int = files.sumOf { it.size } + dirs.sumOf { it.size() }
}

private data class File(val size: Int)

fun main() {
    val data = readInput("day07/day07_data")

    fun prepareTree(): Directory {
        val outermost = Directory("/")
        var current = outermost
        data.drop(1).forEach { line ->
            when {
                line.startsWith("$ cd ..") -> current = current.parent!!
                line.startsWith("$ cd") -> current = current.dirs.first { it.name == line.substringAfter("cd ") }
                line.startsWith("dir") -> current.dirs.add(Directory(line.substringAfter("dir "), current))
                !line.contains("$") -> current.files.add(File(line.substringBefore(" ").toInt()))
            }
        }
        return outermost
    }
    val outermostDirectory = prepareTree()

    fun solvePart1() = outermostDirectory.allDirs().map { it.size() }.filter { it < 100_000 }.sum()
    fun solvePart2(): Int {
        val spaceToFree = 30_000_000 - (70_000_000 - outermostDirectory.size())
        return outermostDirectory.allDirs().map { it.size() }.sorted().first { it >= spaceToFree }
    }
    println(solvePart1())
    println(solvePart2())
}
