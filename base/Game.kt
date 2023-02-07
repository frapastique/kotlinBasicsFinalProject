class Game {
    var lead: LeadHero = LeadHero("Sagittarius", 1300)
    var mage: MageHero = MageHero("Keyleth", 1250)
    var range: RangeHero = RangeHero("Vex'ahlia", 1250)
    var tank: TankHero = TankHero("Grog", 1700)

    var dungeon: MutableList<Room> = Dungeon().generateRooms()
    var heroesList: MutableList<Hero> = mutableListOf(this.lead, this.mage, this.range, this.tank)

    fun startGame() {
        println("Du betrittst einen Dungeon mit ${this.dungeon.size} Räumen.")
        var currentHeroes: MutableList<Hero> = this.heroesList
        var heroBoostFactor: Double = 1.0
        for (room in this.dungeon) {
            currentHeroes = BattleManager(room, currentHeroes, heroBoostFactor).startBattle()
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
            if (room.enemies.isEmpty()) {
                println("\n\n ACHTUNG: Der Raum ist leer!")
            }
        }
    }
}