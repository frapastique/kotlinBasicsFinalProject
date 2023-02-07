import kotlin.system.exitProcess

class Game {
    var round: Int = 0

    var lead: LeadHero = LeadHero("Sagittarius", 1300)
    var mage: MageHero = MageHero("Keyleth", 1250)
    var range: RangeHero = RangeHero("Vex'ahlia", 1250)
    var tank: TankHero = TankHero("Grog", 1700)

    var dungeon: MutableList<Room> = Dungeon().generateRooms(round)
    var heroesList: MutableList<Hero> = mutableListOf(this.lead, this.mage, this.range, this.tank)

    var healPotion: HealPotion = HealPotion("Heiltrank", "Füllt das Leben des Helden voll auf.", 2)
    var manaPotion: ManaPotion = ManaPotion("Manatrank", "Füllt die Mana des Helden voll auf.", 2)
    var items: MutableList<Item> = mutableListOf(healPotion, manaPotion)

    var inventory: Inventory = Inventory(items)

    fun startGame() {
        println("Du betrittst einen Dungeon mit ${this.dungeon.size} Räumen.")
        var currentHeroes: MutableList<Hero> = this.heroesList
        var heroBoostFactor: Double = 1.0
        for (room in this.dungeon) {
            currentHeroes = BattleManager(room, currentHeroes, heroBoostFactor, inventory, round).startBattle()
            Thread.sleep(1000)
            if (this.lead in currentHeroes) {
                var faktor: Int = (1 .. 30).random()
                heroBoostFactor += (faktor / 100.0)
                println("\n${this.lead.name} motiviert alle und erhöht alle Angriffe um $faktor%!")
            }
            Thread.sleep(1000)
            if (this.mage in currentHeroes && currentHeroes.size < this.heroesList.size) {
                for (hero in this.heroesList) {
                    if (hero in currentHeroes) {
                        continue
                    } else {
                        hero.hp -= hero.hp.div(2)
                        currentHeroes.add(hero)
                        println("$hero wurde mit ${hero.hp}HP von ${this.mage} wiederbelebt und ist nun wieder kampf tüchtig!")
                        Thread.sleep(500)
                    }
                }
            }
            if (room.roomName == "Boss Raum") {
                println("Möchtest du noch eine Runde Spielen?\n")
                println("""
                    (1) -> Ja, neuen Dungeon spielen.
                    (2) -> Nein, Spiel beenden.
                """.trimIndent())
                var input: Int = Input().checkInput()
                when (input) {
                    1 -> {
                        round++
                        restartGame(this.inventory, round)
                    }
                    2 -> {
                        println("\nDanke für Spielen!")
                        exitProcess(1)
                    }
                }
            }
        }
    }

    private fun restartGame(inventory: Inventory, round: Int) {
        this.dungeon = Dungeon().generateRooms(round)
    }
}