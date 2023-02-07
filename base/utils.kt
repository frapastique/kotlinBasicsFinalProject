const val ANSI_RESET = "\u001B[0m"
const val ANSI_BLACK = "\u001B[30m"
const val ANSI_RED = "\u001B[31m"
const val ANSI_GREEN = "\u001B[32m"
const val ANSI_YELLOW = "\u001B[33m"
const val ANSI_BLUE = "\u001B[34m"
const val ANSI_PURPLE = "\u001B[35m"
const val ANSI_CYAN = "\u001B[36m"
const val ANSI_WHITE = "\u001B[37m"

const val ANSI_BLACK_BACKGROUND = "\u001B[40m"
const val ANSI_RED_BACKGROUND = "\u001B[41m"
const val ANSI_GREEN_BACKGROUND = "\u001B[42m"
const val ANSI_YELLOW_BACKGROUND = "\u001B[43m"
const val ANSI_BLUE_BACKGROUND = "\u001B[44m"
const val ANSI_PURPLE_BACKGROUND = "\u001B[45m"
const val ANSI_CYAN_BACKGROUND = "\u001B[46m"
const val ANSI_WHITE_BACKGROUND = "\u001B[47m"

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