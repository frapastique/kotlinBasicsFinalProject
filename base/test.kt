fun main() {
    val lead: LeadHero = LeadHero("Sagittarius", 1300)

    val smallEnemy0: SmallEnemy = SmallEnemy(generateName(), generateHP())

    lead.printStatus()
    println()
    smallEnemy0.printStatus()
    println()
    smallEnemy0.takeDamage(lead.attack(smallEnemy0))
    println()
    lead.takeDamage(smallEnemy0.attack(lead))
}