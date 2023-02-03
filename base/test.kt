fun main() {
    var lead: LeadHero = LeadHero("Sagittarius", 1300)
    var mage: MageHero = MageHero("Keyleth", 1250)
    var range: RangeHero = RangeHero("Vex'ahlia", 1250)
    var tank: TankHero = TankHero("Grog", 1700)

    var heroesList: MutableList<Hero> = mutableListOf(lead, mage, range, tank)

    var rooms = Dungeon().generateRooms()

    var room: Room = rooms[0]

    BattleManager(room, heroesList).startBattle()
}