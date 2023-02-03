import kotlin.system.exitProcess

class BattleManager(var room: Room, var heroes: MutableList<Hero>) {
    var roomName = this.room.roomName
    var enemies: MutableList<Enemy> = this.room.enemies
    var hero: Hero? = null
    var enemy: Enemy? = null
    var i: Int = 0
    fun startBattle() {
        println(roomName)
        while (!endBattle()) {
            enemy = enemies[0]
            enemy!!.printStatus()
            hero = heroes[i]
            i++
            hero!!.printStatus()
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
        }
    }
}