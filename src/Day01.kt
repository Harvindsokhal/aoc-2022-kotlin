fun main() {
    val data = readInput("day1")

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
        }.max()
    }
    println(maxCalories(data))
}
