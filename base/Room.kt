class Room(var roomName: String, var enemies: MutableList<Enemy>, var door: Int) : Door() {
    override fun toString(): String {
        /*Thread.sleep(500)
        println("\n${this.roomName}: ${this.enemies.size} Gegner\n")
        for (i in 0 until this.enemies.size) {
            println("   Gegner ${i+1}")
            println(this.enemies[i])
            if (i < this.enemies.size - 1) {
                println()
            }
        }*/

        val info = StringBuilder("\n${this.roomName}: ${this.enemies.size} Gegner\n")
        for (i in 0 until this.enemies.size) {
            info.append("   Gegner ${i + 1}\n")
            Thread.sleep(500)
            info.append("${this.enemies[i]}\n")
            if (i < this.enemies.size - 1) {
                info.append("\n")
            }
        }
        return info.toString()
    }

    fun addEnemies(enemy: MutableList<SmallEnemy>) {
        this.enemies.addAll(enemy)
    }
}