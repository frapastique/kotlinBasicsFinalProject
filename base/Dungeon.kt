class Dungeon(var rooms: MutableList<Room>) {

    override fun toString(): String {
        /*for (i in 0 until rooms.size) {
            var roomName: String = rooms[i].roomName
            var enemyCount: Int = rooms[i].enemies.size
            println("$roomName mit $enemyCount Gegner")
            for (j in 0 until rooms[i].enemies.size) {
                var enemyName: String = rooms[i].enemies[j].name
                var enemyHP: Int = rooms[i].enemies[j].hp
                println("   Gegner ${j+1}: $enemyName mit $enemyHP Leben")
            }
        }
        println("--------------------")*/
        for (i in rooms) {
            var roomName= i.roomName
            var enemies = i.enemies
            var door = i.door
            println(Room(roomName, enemies, door))
        }
        return ""
    }
}