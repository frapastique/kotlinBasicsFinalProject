class Dungeon(var rooms: MutableList<Room>) {

    override fun toString(): String {
        for (i in rooms) {
            var roomName: String = i.roomName
            var enemiesCount: Int = i.enemies.size
            var info = "$roomName mit $enemiesCount Gegner."
            println(info)
            for (k in 0 until rooms.size) {
                for (j in 0 until rooms[k].enemies.size) {
                    var enemyName: String = rooms[k].enemies[j].name
                    var enemyHP: Int = rooms[k].enemies[j].hp
                    info = "Gegner ${j + 1}: $enemyName mit $enemyHP"
                    println(info)
                }
            }
        }
        return "blob"
    }
}