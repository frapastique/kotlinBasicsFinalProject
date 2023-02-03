import kotlin.system.exitProcess

class BattleManager(var room: Room, var heroes: MutableList<Hero>) {
    var roomName = this.room.roomName
    var enemies: MutableList<Enemy> = this.room.enemies
    var hero: Hero? = null
    var enemy: Enemy? = null
    var i: Int = 0
    var counter: Int = 0
    fun startBattle() {
        println(roomName)
        while (!endBattle()) {
            var lastHero = i
            i = (0 .. heroes.size - 1).random()
            var randomEnemy: Int = (0 .. enemies.size - 1).random()
            enemy = enemies[randomEnemy]
            var enemyName = enemy!!.name
            if (roomName == "Boss Raum" && enemyName == "Drache") {
                counter++
                if (counter == 4) {
                    counter = 0
                    var summonedEnemies = summon(this.room, counter)
                    this.room.addEnemies(summonedEnemies)
                    println("$enemyName hat ${summonedEnemies.size} Gegner Beschworen.")
                }
            }
            enemy!!.printStatus()
            hero = heroes[i]
            resolveAttack(enemy!!, hero!!)
            resolveAttack(hero!!, enemy!!)
        }
    }
    fun endBattle(): Boolean {
        if (enemies.isEmpty()) {
            println("Alle Gegner Besiegt. Du hast gewonnen.")
            exitProcess(1)
        }
        if (heroes.isEmpty()) {
            println("Alle deine Helden wurden besiegt. Du hast verloren")
            exitProcess(1)
        }
        return false
    }
    fun resolveAttack(attacker: Combatant, defender: Combatant) {
        defender.takeDamage(attacker.attack(defender))
        if (defender.printStatus()) {
            enemies.remove(defender)
            heroes.remove(defender)
        }
    }
}