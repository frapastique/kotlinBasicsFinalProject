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
        println("\n${YELLOW_UNDERLINED}Du betrittst einen Dungeon mit ${this.dungeon.size} Räumen.$RESET\n")
        Thread.sleep(500)
        for (room in this.dungeon) {
            println("Helden:")
            for (hero in currentHeroes) {
                Thread.sleep(200)
                hero.printStatus()
            }
            Thread.sleep(500)
            var addItemCount: Int = room.enemies.size
            var currentInventory: Inventory = Inventory(generateItems(quantityForItems))
            this.currentHeroes = BattleManager(room, this.currentHeroes, this.heroBoostFactor, currentInventory, this.round).startBattle()
            Thread.sleep(1000)
            if (this.lead in this.currentHeroes) {
                heroBoostFactor = this.lead.inspire(heroBoostFactor)
            }
            Thread.sleep(1000)
            if (this.mage in currentHeroes && currentHeroes.size < this.heroesList.size) {
                currentHeroes = this.mage.revive(this.heroesList, this.currentHeroes)
            }
            checkGameEnd(room.roomName)
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

    fun checkGameEnd(roomName: String) {
        if (roomName == "Boss Raum") {
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
                    println("\nDanke für Spielen ${PURPLE + userName + RESET} und bis zum nächsten Mal!\n")
                    statistics(this.round)
                    exitProcess(1)
                }
            }
        }

        if (this.currentHeroes.isEmpty()) {
            println("\nDanke für Spielen ${PURPLE + userName + RESET} und bis zum nächsten Mal!\n")
            statistics(this.round)
            exitProcess(1)
        }
    }
}