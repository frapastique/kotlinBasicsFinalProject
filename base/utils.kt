fun generateName(): String {
    val namesList: String = listOf(
        "Skelett", "Krieger", "Zombie", "Elf",
        "Reaper", "Assassine", "Bestie", "Viper",
        "Golem",
        ).random()
    return namesList
}
fun generateHP(): Int {
    return (175 .. 300).random()
}

fun generateAttacks(attacksCount: Int): Map<String, Int> {
    val attacks: MutableMap<String, Int> = mutableMapOf()
    for (i in 1 .. attacksCount) {
        val attackKey: String = listOf(
            "Schwert", "Axt", "Pfeil", "Armbrust",
            "Messer", "Dolch", "Speer", "Keule",
            "Schleuder", "Stein", "Schild", "Stab",
            "Schaufel", "Wurfstern", "Kriegshammer",
            "Kratzer", "Biss",
        ).random()
        val attackValue: Int = (20 .. 150).random()
        attacks.put(attackKey, attackValue)
    }
    return attacks
}

fun generateSmallEnemy(count: Int): MutableList<Enemy> {
    var enemiesList: MutableList<Enemy> = mutableListOf()

    for (i in 1 .. count) {
        var enemy: SmallEnemy = SmallEnemy(generateName(), generateHP(), generateAttacks(2))
        enemiesList.add(enemy)
    }
    return enemiesList
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
            var finalBoss: FinalBoss = FinalBoss("Dragon", 13750)
            enemies = mutableListOf<Enemy>(finalBoss)
            rooms.add(Room(roomName, enemies, 0))
        }
    }
    return rooms
}