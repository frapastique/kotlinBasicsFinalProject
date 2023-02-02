fun generateSmallEnemy(count: Int): MutableList<Enemy> {
    var enemiesList: MutableList<Enemy> = mutableListOf()

    for (i in 1 .. count) {
        val namesList = mutableListOf(
            "Skelett", "Krieger", "Zombie", "Elf", "Reaper",
            "Assassine", "Bestie", "Viper", "Golem", "Kobold",
            "Minotaur", "Hydra", "Troll", "Oger", "Goblin",
            "Werwolf", "Harpyie", "Zentauren", "Ungeheuer", "Elementar",
            "Schlange", "Dämon",
        )
        val name = namesList.random()

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

        var enemy: SmallEnemy = SmallEnemy(name, hp, attacks)
        enemiesList.add(enemy)
    }
    return enemiesList
}