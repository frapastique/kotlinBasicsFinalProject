class Room(var roomName: String, var enemies: MutableList<Enemy>) : Door() {
    override fun toString(): String {
        return "$roomName, ${enemies.size} Gegner"
    }
}