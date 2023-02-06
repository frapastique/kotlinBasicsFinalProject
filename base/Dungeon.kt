class Dungeon {
    var rooms: MutableList<Room> = mutableListOf()


    override fun toString(): String {
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
                var finalBossHP: Int = when (amountRooms) {
                    5 -> {
                        (18000..20000).random()
                    }

                    4 -> {
                        (16000..18000).random()
                    }

                    3 -> {
                        (14000..16000).random()
                    }

                    else -> {
                        (9999..14000).random()
                    }
                }
                var finalBoss: FinalBoss = FinalBoss("Drache", finalBossHP)
                enemies = mutableListOf<Enemy>(finalBoss)
                rooms.add(Room(roomName, enemies, 0))
            }
        }
        return rooms
    }
}