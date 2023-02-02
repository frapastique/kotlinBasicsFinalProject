class Room(var roomName: String, var enemies: MutableList<Enemy>, door: Int) : Door() {
    override fun toString(): String {
        return "$roomName, ${enemies.size} Gegner"
    }

    fun addEnemies(enemy: SmallEnemy) {

    }
}