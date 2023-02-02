class Room(var roomName: String, var enemies: MutableList<Enemy>, var door: Int) : Door() {
    override fun toString(): String {
        println("${this.roomName}: ${this.enemies.size} Gegner\n")
        for (i in 0 until this.enemies.size) {
            println("   Gegner ${i+1}")
            println(this.enemies[i])
            if (i < this.enemies.size - 1) {
                println()
            }
        }
        return "--------------------"
    }

    fun addEnemies(enemy: MutableList<SmallEnemy>) {
        this.enemies.addAll(enemy)
    }
}