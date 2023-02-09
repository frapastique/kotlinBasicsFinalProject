package utils

import combatant.Enemy
import combatant.SmallEnemy
import inventory.HealPotion
import inventory.Item
import inventory.ManaPotion

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

    println("Drücke Enter um den Dungeon Crawler zu starten!")
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

fun statistics(round: Int) {
    var roomsClearedAll: Int = roomsCleared.plus(bossRoomsCleared)
    var defeatedEnemies: Int = defeatedSmallEnemies.plus(defeatedBosses)
    var gameScore: Int = (
            overallDamageGiven.div(1000)
                    - overallDamageReceived.div(1000)
                    + roomsClearedAll.times(10)
                    + defeatedSmallEnemies.times(2)
                    + defeatedBosses.times(50)
                    - usedManaPotions.times(2)
                    - usedHealPotions.times(5)
                    - lostHeroes.times(50)
                    + revivedHeroes.times(25)
            ).times(round).toInt()

    println("""
        Statistik:
        Gespielte Dungeons:         $round
        
        Gesäuberte Reguläre Räume:  $roomsCleared
        Gesäuberte Boss Räume:      $bossRoomsCleared
        Insgesamt Gesäuberte Räume: $roomsClearedAll
        
        Besiegte Reguläre Gegner:   $defeatedSmallEnemies
        Besiegte Drachen:           $defeatedBosses
        Insgesamt Besiegte Gegner:  $defeatedEnemies
        
        Erlittener Schaden:         $overallDamageReceived
        Ausgeteilter Schaden:       $overallDamageGiven
        
        Benutzte Heil tränke:       $usedHealPotions
        Benutzte Mana Tränke:       $usedManaPotions
        
        Besiegte Helden:            $lostHeroes
        Wiederbelebungen:           $revivedHeroes
        
        Angriffsverstärkung:        $attackBoostPercent%
        
        Spiel Zeit:                 ${ProgramUsage().calculatePlayTime(playTime)}
        
        Spiel Wertung:              $gameScore
    """.trimIndent())
}