import kotlin.system.exitProcess

class BattleManager(var room: Room, var heroes: MutableList<Hero>, var heroBoostFactor: Double) {
    var roomName = this.room.roomName
    var enemies: MutableList<Enemy> = this.room.enemies
    var hero: Hero? = null
    var enemy: Enemy? = null
    var counterBossAttacks: Int = 0

    fun startBattle(): MutableList<Hero> {
        println("""
            Du hast nun $roomName betreten!
            
            Dich erwartet:
            """.trimIndent())
        for (showEnemies in enemies) {
            var number: Int = enemies.indexOf(showEnemies) + 1
            println("($number) -> ${showEnemies.name} mit ${showEnemies.showStatsSmall()[1]}HP")
        }
        println()
        do {
            Thread.sleep(1000)
            for (enemyRound in this.enemies) {
                var randomHero: Int = (0 .. heroes.size - 1).random()
                this.hero = this.heroes[randomHero]
                var enemyName = enemyRound.name
                resolveAttack(enemyRound, this.hero!!)
                if (endBattle()) {
                    break
                }
                if (roomName == "Boss Raum" && enemyName == "Drache") {
                    counterBossAttacks++
                    if (counterBossAttacks == 4) {
                        counterBossAttacks = 0
                        var summonedEnemies = summon(counterBossAttacks)
                        this.room.addEnemies(summonedEnemies)
                        println("$enemyName hat ${summonedEnemies.size} Gegner Beschworen.\n")
                        Thread.sleep(1000)
                        break
                    }
                }
            }

            for (heroRound in this.heroes) {
                if (this.enemies.size > 1) {
                    println("Wähle einen Gegner:")
                    for (showEnemies in enemies) {
                        var number: Int = enemies.indexOf(showEnemies) + 1
                        println("($number) -> ${showEnemies.name} mit ${showEnemies.showStatsSmall()[1]}HP")
                    }
                    chooseEnemy()
                    println()
                } else {
                    this.enemy = enemies.first()
                }
                if (heroRound.hasMana) {
                    println("${heroRound.name} (${heroRound.showStatsSmall()[2]}MP) vs. ${this.enemy!!.name} (${this.enemy!!.showStatsSmall()[1]}HP)")
                } else {
                    println("${heroRound.name} vs. ${this.enemy!!.name} (${this.enemy!!.showStatsSmall()[1]}HP)")
                }
                resolveAttack(heroRound, enemy!!)
                if (this.enemies.isEmpty()) {
                    break
                }
            }
        } while (!endBattle())
        return this.heroes
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
            if (roomName == "Final Boss") {
                println("Dungeon gesäubert! Gratuliere du hast das Spiel gewonnen!")
                exitProcess(1)
            } else {
                println("Alle Gegner Besiegt. Raum gesäubert!")
                return true
            }
        }

        if (heroes.isEmpty()) {
            println("Alle deine Helden wurden besiegt. Du hast verloren")
            exitProcess(1)
        }
        return false
    }

    private fun resolveAttack(attacker: Combatant, defender: Combatant) {
        if (defender == this.enemy) {
            defender.takeDamage(attacker.attack(defender, this.heroBoostFactor), this.heroBoostFactor)
        } else {
            if (attacker.name == "Drache") {
                var damageCode: Int = attacker.attack(defender, 1.0)
                if (damageCode == 7777) {
                    var dragonDamage = 250
                    println("${attacker.name} attackiert die Helden Truppe mit Flächenangriff 'Feuer Atem' und verursacht " + dragonDamage + "HP schaden.")
                    for (heroAreaDamage in this.heroes) {
                        heroAreaDamage.takeDamage(dragonDamage, 1.0)
                        println("${heroAreaDamage.name} hat nun ${heroAreaDamage.showStatsSmall()[1]}HP")
                    }
                }
            } else {
                defender.takeDamage(attacker.attack(defender, 1.0), 1.0)
            }
        }
        println()
        if (defender.checkDefeat(defender.showStatsSmall()[1])) {
            if (defender == enemy) {
                defender.printStatus()
                enemies.remove(defender)
            } else {
                defender.printStatus()
                heroes.remove(defender)
            }
        }
    }
}