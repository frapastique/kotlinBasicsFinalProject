import kotlin.system.exitProcess

class BattleManager(var room: Room, var heroes: MutableList<Hero>) {
    var roomName = this.room.roomName
    var enemies: MutableList<Enemy> = this.room.enemies
    var hero: Hero? = null
    var enemy: Enemy? = null
    var counterBossAttacks: Int = 0

    fun startBattle() {
        println("$roomName\n")
        while (!endBattle()) {
            var randomHero: Int = (0 .. heroes.size - 1).random()
            var randomEnemy: Int = (0 .. enemies.size - 1).random()
            enemy = enemies[randomEnemy]
            var enemyName = enemy!!.name
            if (roomName == "Boss Raum" && enemyName == "Drache") {
                counterBossAttacks++
                if (counterBossAttacks == 4) {
                    counterBossAttacks = 0
                    var summonedEnemies = summon(this.room, counterBossAttacks)
                    this.room.addEnemies(summonedEnemies)
                    println("$enemyName hat ${summonedEnemies.size} Gegner Beschworen.\n")
                }
            }
            hero = heroes[randomHero]
            resolveAttack(enemy!!, hero!!)
            println("\nWähle einen Gegner!\n")
            for (showEnemies in enemies) {
                var number: Int = enemies.indexOf(showEnemies) + 1
                println("($number) -> ${showEnemies.name} mit ${showEnemies.hp}HP")
            }
            chooseEnemy()
            println("Wähle einen Held!\n")
            for (showHeroes in heroes) {
                var number: Int = heroes.indexOf(showHeroes) + 1
                println("($number) -> ${showHeroes.showStatsSmall()[0]} mit ${showHeroes.showStatsSmall()[1]}HP")
            }
            chooseHero()
            println()
            enemy!!.printStatus()
            resolveAttack(hero!!, enemy!!)
        }
    }

    private fun chooseEnemy() {
        val input: Int = Input().checkInput()
        if (input in 1 .. enemies.size) {
            enemy = enemies[input - 1]
        } else {
            println("Eingabe nicht möglich. Versuche erneut.")
            chooseEnemy()
        }
    }
    private fun chooseHero() {
        val input: Int = Input().checkInput()
        if (input in 1 .. heroes.size) {
            hero = heroes[input - 1]
        } else {
            println("Eingabe nicht möglich. Versuche erneut.")
            chooseHero()
        }
    }

    private fun endBattle(): Boolean {
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

    private fun resolveAttack(attacker: Combatant, defender: Combatant) {
        defender.takeDamage(attacker.attack(defender))
        println()
        if (defender.checkDefeat(defender.showStatsSmall()[1])) {
            if (defender == enemy) {
                defender.printStatus()
                enemies.remove(defender)
            }
            if (defender == hero) {
                heroes.remove(defender)
                defender.printStatus()
            }
        }
    }
}