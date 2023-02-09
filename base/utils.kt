fun generateSmallEnemy(count: Int, round: Int): MutableList<Enemy> {
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

        var hp: Int = (175 .. 300).random()
        if (round > 1) {
            var newHP: Int = hp.plus(round.times(100))
            hp = newHP
        }

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

fun summon(counter: Int, round:Int): MutableList<Enemy>{
    var enemies: MutableList<Enemy> = mutableListOf()
    if (counter % 4 == 0) {
        val countEnemies: Int = (2 .. 5).random()
        enemies = generateSmallEnemy(countEnemies, round)
    }
    return enemies
}

fun generateItems(quantity: Int): MutableList<Item> {
    var healPotion: HealPotion = HealPotion("Heiltrank", "Füllt das Leben des Helden voll auf.", quantity)
    var manaPotion: ManaPotion = ManaPotion("Manatrank", "Füllt die Mana des Helden voll auf.", quantity)
    return mutableListOf(healPotion, manaPotion)
}

fun intro() {
    print("\nGebe hier deinen Namen ein: ")
    userName = readln()
    println("\nHallo " + PURPLE + userName + RESET + "!\n\n" +
            "Willkommen zum Abschlussprojekt zum \"Modul 2: Kotlin Basics\" beim Syntax-Institut!\n\n")
    println("(Enter drücken um das Spiel zu starten)")
    readln()
}

fun motivationQuote(): String {
    var selectedQuote: String

    if (listOfMotivationQuotes.isNotEmpty()) {
        selectedQuote = listOfMotivationQuotes.random()
        listOfMotivationQuotes.remove(selectedQuote)
        listOfMotivationQuotesDump.add(selectedQuote)
        return selectedQuote
    } else {
        selectedQuote = listOfMotivationQuotesDump.random()
        listOfMotivationQuotesDump.remove(selectedQuote)
        listOfMotivationQuotes.add(selectedQuote)
        return selectedQuote
    }
}