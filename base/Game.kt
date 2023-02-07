import kotlin.system.exitProcess

class Game {
    var round: Int = 1

    var lead: LeadHero = LeadHero("Sagittarius", 1300)
    var mage: MageHero = MageHero("Keyleth", 1250)
    var range: RangeHero = RangeHero("Vex'ahlia", 1250)
    var tank: TankHero = TankHero("Grog", 1700)

    var dungeon: MutableList<Room> = Dungeon().generateRooms(round)
    var heroesList: MutableList<Hero> = mutableListOf(this.lead, this.mage, this.range, this.tank)

    var currentHeroes: MutableList<Hero> = this.heroesList
    var heroBoostFactor: Double = 1.0
    var quantityForItems: Int = 2

    fun startGame() {
        println("Du betrittst einen Dungeon mit ${this.dungeon.size} Räumen.")
        for (room in this.dungeon) {
            var addItemCount: Int = room.enemies.size
            var currentInventory: Inventory = Inventory(generateItems(quantityForItems))
            this.currentHeroes = BattleManager(room, this.currentHeroes, this.heroBoostFactor, currentInventory, this.round).startBattle()
            Thread.sleep(1000)
            if (this.lead in this.currentHeroes) {
                var faktor: Int = (1 .. 30).random()
                heroBoostFactor += (faktor / 100.0)
                println("\n${this.lead.name} motiviert alle und erhöht alle Angriffe um $faktor%!\n")
            }
            Thread.sleep(1000)
            if (this.mage in currentHeroes && currentHeroes.size < this.heroesList.size) {
                for (hero in this.heroesList) {
                    if (hero in currentHeroes) {
                        continue
                    } else {
                        hero.hp -= hero.hp.div(2)
                        currentHeroes.add(hero)
                        println("\n$hero wurde mit ${hero.hp}HP von ${this.mage} wiederbelebt und ist nun wieder kampf tüchtig!")
                        Thread.sleep(500)
                    }
                }
            }
            if (room.roomName == "Boss Raum") {
                println("\nMöchtest du noch eine Runde Spielen?")
                println("""
                    (1) -> Ja, neuen Dungeon spielen.
                    (2) -> Nein, Spiel beenden.
                """.trimIndent())
                var input: Int = Input().checkInput()
                when (input) {
                    1 -> {
                        round++
                        restartGame(this.round)
                        startGame()
                    }
                    2 -> {
                        println("\nDanke für Spielen!")
                        exitProcess(1)
                    }
                }
            }
            quantityForItems += (addItemCount.div(2))
        }
    }

    private fun restartGame(round: Int) {
        this.dungeon = Dungeon().generateRooms(round)
        this.currentHeroes = this.heroesList
        for (hero in heroesList) {
            hero.resetStats()
        }
    }
}