class Room(var roomName: String, var enemies: MutableList<Enemy>, var door: Int) : Door() {
    override fun toString(): String {
        /*println("${this.roomName} mit ${this.enemies.size} Gegner")
        for (i in 0 until this.enemies.size) {
            var enemyName: String = this.enemies[i].name
            var enemyHP: Int = this.enemies[i].hp
            println("   Gegner ${i+1}: $enemyName mit $enemyHP Leben")
        }
        */
        println("${this.roomName} mit ${this.enemies.size} Gegner")
        for (i in 0 until this.enemies.size) {
            println("Gegner ${i+1}")
            this.enemies[i].printStatus()
            println()
        }
        return "--------------------"
    }

    fun addEnemies(enemy: SmallEnemy) {

    }
}