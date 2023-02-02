fun main() {
    val lead: LeadHero = LeadHero("Sagittarius", 1300)

    var finalBoss: FinalBoss = FinalBoss("Dragon", 13750)

    /*lead.printStatus()
    println()
    smallEnemy0.printStatus()
    println()
    smallEnemy0.takeDamage(lead.attack(smallEnemy0))
    println()
    lead.takeDamage(smallEnemy0.attack(lead))
    println()
    smallEnemy0.takeDamage(lead.attack(smallEnemy0))*/

    /*var enemies = generateSmallEnemy(3)
    println(Room("BLOB", enemies, 1))*/
    var rooms = generateRooms()
    println(Dungeon(rooms))

}