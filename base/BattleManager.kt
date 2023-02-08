import kotlin.system.exitProcess

class BattleManager(var room: Room, var heroes: MutableList<Hero>, var heroBoostFactor: Double, var inventory: Inventory, var round: Int) {
    var roomName = this.room.roomName
    var enemies: MutableList<Enemy> = this.room.enemies
    var hero: Hero? = null
    var enemy: Enemy? = null
    var counterBossAttacks: Int = 0

    fun startBattle(): MutableList<Hero> {
        println("""
            
            Inventar:
            ${this.inventory.items[0].quantity}x ${this.inventory.items[0].name}
            ${this.inventory.items[1].quantity}x ${this.inventory.items[1].name}
            
            Im ${this.roomName} erwarten dich ${this.enemies.size} Gegner:
            """.trimIndent())
        for (showEnemies in enemies) {
            Thread.sleep(500)
            showEnemies.printStatus()
        }
        println()
        do {
            Thread.sleep(500)
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
                        var summonedEnemies = summon(counterBossAttacks, round)
                        this.room.addEnemies(summonedEnemies)
                        println("$enemyName hat ${summonedEnemies.size} Gegner Beschworen.\n")
                        Thread.sleep(1000)
                        break
                    }
                }
            }

            for (heroRound in this.heroes) {
                heroRound.printStatus()
                Thread.sleep(500)
                if (heroRound.checkStats() && heroRound.chooseAction() && useItem(heroRound)) {
                    heroRound.printStatus()
                } else {
                    if (this.enemies.size > 1) {
                        println("\nWähle einen Gegner:")
                        for (showEnemies in enemies) {
                            var number: Int = enemies.indexOf(showEnemies) + 1
                            println("($number) -> ${BLUE + showEnemies.name + RESET} mit ${showEnemies.showStatsSmall()[1]}HP")
                        }
                        chooseEnemy()
                        println()
                        this.enemy!!.printStatus()
                        heroRound.printStatus()
                    } else {
                        this.enemy = enemies.first()
                        this.enemy!!.printStatus()
                    }
                    resolveAttack(heroRound, enemy!!)
                    if (this.enemies.isEmpty()) {
                        break
                    }
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
                println("\nDungeon gesäubert! Gratuliere du hast das Spiel gewonnen!")
                return true
            } else {
                println("Alle Gegner Besiegt. Raum gesäubert!")
                return true
            }
        }

        if (heroes.isEmpty()) {
            println("\nAlle deine Helden wurden besiegt. Du hast verloren!")
            exitProcess(1)
        }
        return false
    }

    private fun resolveAttack(attacker: Combatant, defender: Combatant) {
        if (defender == this.enemy) {
            defender.takeDamage(attacker.attack(defender, this.heroBoostFactor), this.heroBoostFactor)
            Thread.sleep(500)
            if (defender.name == "Drache") {
                println()
            }
        } else {
            if (attacker.name == "Drache") {
                var damageCode: Int = attacker.attack(defender, 1.0)
                if (damageCode == 7777) {
                    var dragonDamage = 250
                    println("${attacker.name} attackiert die Helden Truppe mit Flächenangriff 'Feuer Atem' und verursacht " + dragonDamage + "HP schaden.")
                    for (heroAreaDamage in this.heroes) {
                        Thread.sleep(500)
                        heroAreaDamage.takeDamage(dragonDamage, 1.0)
                    }
                    println()
                } else {
                    defender.takeDamage(damageCode, 1.0)
                    Thread.sleep(500)
                    println()
                }
            } else {
                defender.takeDamage(attacker.attack(defender, 1.0), 1.0)
                Thread.sleep(500)
                println()
            }
        }
        if (defender.checkDefeat(defender.showStatsSmall()[1])) {
            if (defender == enemy) {
                defender.printStatus()
                Thread.sleep(500)
                enemies.remove(defender)
            } else {
                defender.printStatus()
                Thread.sleep(500)
                heroes.remove(defender)
            }
        }
    }

    private fun useItem(hero: Hero): Boolean {
        println("\nWähle ein Item:")
        var k: Int = 1
        for (element in this.inventory.items) {
            println("""
                ($k) -> ${element.name}
                       ${element.quantity} im Inventar
                       ${element.description}
                        """.trimIndent())
            k++
        }
        println("(3) -> Inventar schließen und attackieren")
        when (Input().checkInput()) {
            1 -> {
                if (this.inventory.useItem(inventory.items[0], hero)) {
                    return true
                } else {
                    return useItem(hero)
                }
            }
            2 -> {
                if (this.inventory.useItem(inventory.items[1], hero)) {
                    return true
                } else {
                    return useItem(hero)
                }
            }
            3 -> return false
            else -> {
                println("Eingabe nicht möglich.")
                return useItem(hero)
            }
        }
    }
}