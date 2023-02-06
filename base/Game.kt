class Game {
    var lead: LeadHero = LeadHero("Sagittarius", 1300)
    var mage: MageHero = MageHero("Keyleth", 1250)
    var range: RangeHero = RangeHero("Vex'ahlia", 1250)
    var tank: TankHero = TankHero("Grog", 1700)

    var dungeon: MutableList<Room> = Dungeon().generateRooms()
    var heroesList: MutableList<Hero> = mutableListOf(this.lead, this.mage, this.range, this.tank)
    fun startGame() {
        for (room in this.dungeon) {
            println(dungeon.size)
            BattleManager(room, this.heroesList).startBattle()
        }
    }
}