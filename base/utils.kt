fun generateSmallEnemy(count: Int): MutableList<Enemy> {
    var enemiesList: MutableList<Enemy> = mutableListOf()
    val namesList = mutableListOf(
        "Skelett", "Krieger", "Zombie", "Elf", "Reaper",
        "Assassine", "Bestie", "Viper", "Golem", "Kobold",
        "Minotaur", "Hydra", "Troll", "Oger", "Goblin",
        "Werwolf", "Harpyie", "Zentauren", "Ungeheuer", "Elementar",
        "Schlange", "Dämon",
    )
    val name = namesList.random()
    namesList.remove(name)

    val hp: Int = (175 .. 300).random()

    val attacks: MutableMap<String, Int> = mutableMapOf()
    for (i in 1 .. 2) {
        val attackKey: String = listOf(
            "Schwert", "Axt", "Pfeil", "Armbrust",
            "Messer", "Dolch", "Speer", "Keule",
            "Schleuder", "Stein", "Schild", "Stab",
            "Schaufel", "Wurfstern", "Kriegshammer",
            "Kratzer", "Biss",
        ).random()
        val attackValue: Int = (20 .. 150).random()
        attacks[attackKey] = attackValue
    }

    for (i in 1 .. count) {
        var enemy: SmallEnemy = SmallEnemy(name, hp, attacks)
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
            var finalBoss: FinalBoss = FinalBoss("Drache", 13750)
            enemies = mutableListOf<Enemy>(finalBoss)
            rooms.add(Room(roomName, enemies, 0))
        }
    }
    return rooms
}