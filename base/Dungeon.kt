class Dungeon {
    var rooms: MutableList<Room> = mutableListOf()

    override fun toString(): String {
        /*for (i in rooms) {
            var roomName= i.roomName
            var enemies = i.enemies
            var door = i.door
            println(Room(roomName, enemies, door))
        }*/
        var info = StringBuilder()
        for (i in rooms) {
            var roomName = i.roomName
            var enemies = i.enemies
            var door = i.door
            info.append(Room(roomName, enemies, door))
        }
        return info.toString()
    }

    fun generateRooms(): MutableList<Room> {
        val amountRooms: Int = (2 .. 5).random()
        var enemies: MutableList<Enemy>
        var rooms: MutableList<Room> = mutableListOf()
        var roomName: String
        for (i in 1 .. amountRooms) {
            if (i < amountRooms) {
                roomName = "Raum $i"
                val countEnemies: Int = (2 .. 5).random()
                enemies = generateSmallEnemy(countEnemies)
                rooms.add(Room(roomName, enemies, 1))
            } else {
                roomName = "Boss Raum"
                var finalBoss: FinalBoss = FinalBoss("Drache", 13750)
                enemies = mutableListOf<Enemy>(finalBoss)
                rooms.add(Room(roomName, enemies, 0))
            }
        }
        return rooms
    }
}