class Room(var roomName: String, var enemies: MutableList<Enemy>, var door: Int) : Door() {
    override fun toString(): String {
        println("${this.roomName} mit ${this.enemies.size} Gegner")
        for (i in 0 until this.enemies.size) {
            println("Gegner ${i+1}")
            this.enemies[i].printStatus()
            if (i < this.enemies.size - 1) {
                println()
            }
        }
        return "--------------------"
    }

    fun addEnemies(enemy: SmallEnemy) {

    }
}