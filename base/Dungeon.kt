class Dungeon {
    var rooms: MutableList<Room> = mutableListOf()

    override fun toString(): String {
        var info = StringBuilder()
        for (i in rooms) {
            var roomName = i.roomName
            var enemies = i.enemies
            info.append(Room(roomName, enemies))
        }
        return info.toString()
    }

    fun generateRooms(round: Int): MutableList<Room> {
        val amountRooms: Int = (2 .. 5).random()
        var enemies: MutableList<Enemy>
        var rooms: MutableList<Room> = mutableListOf()
        var roomName: String
        var items: MutableList<Item> = mutableListOf()

        for (i in 1 .. amountRooms) {
            if (i < amountRooms) {
                roomName = "Raum $i"
                val countEnemies: Int = (2 .. 5).random()
                enemies = generateSmallEnemy(countEnemies, round)
                rooms.add(Room(roomName, enemies))
            } else {
                roomName = "Boss Raum"
                var finalBossHP: Int = when (amountRooms) {
                    5 -> {
                        (14000..15000).random()
                    }
                    4 -> {
                        (12000..13000).random()
                    }
                    3 -> {
                        (11000..12000).random()
                    }
                    else -> {
                        (10000..11000).random()
                    }
                }
                if (round > 1) {
                    var newFinalBossHP: Int = finalBossHP.plus(round.times(500))
                    finalBossHP = newFinalBossHP
                }
                var finalBoss: FinalBoss = FinalBoss("Drache", finalBossHP)
                enemies = mutableListOf<Enemy>(finalBoss)
                rooms.add(Room(roomName, enemies))
            }
        }
        return rooms
    }
}