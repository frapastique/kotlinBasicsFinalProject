package combatant

import utils.BLUE
import utils.CYAN
import utils.CYAN_BOLD_BRIGHT
import utils.GREEN
import utils.Input
import utils.PURPLE
import utils.RED
import utils.RESET
import utils.YELLOW
import inventory.Item
import utils.overallDamageGiven
import utils.revivedHeroes

class MageHero(name: String, hp: Int) : Hero(name, hp) {
    override var attacks: Map<String, Int> = mutableMapOf(
        "Dolch" to 150,
        "Betäubung" to 200,
        "Eis" to 250,
        "Blitz" to 350,
        )
    override val hasMana: Boolean = true
    var hpCurrent: Int = this.hp
    var manaPoints: Int = 250

    override fun attack(target: Combatant, factor: Double): Int {
        println("\nWähle eine attacke:")
        var j: Int = 1
        var stun: String = this.attacks.entries.elementAt(1).key
        var ice: String = this.attacks.entries.elementAt(2).key
        var bolt: String = this.attacks.entries.elementAt(3).key
        for (entry in attacks) {
            var attackName: String = entry.key
            var attackHP: Int = (entry.value.times(factor)).toInt()
            print("($j) -> ${attackHP}HP mit $attackName")
            when (entry.key) {
                stun -> {
                    print(" (Mana -10)")
                }
                ice -> {
                    print(" (Mana -15)")
                }
                bolt -> {
                    print(" (Mana -40)")
                }
            }
            j++
            println()
        }
        when (val input: Int = Input().checkInput()) {
            1 -> {
                return attacking(input - 1, target, factor)
            }
            2 -> {
                if (this.manaPoints >= 10) {
                    this.manaPoints -= 10
                    return attacking(input - 1, target, factor)
                } else {
                    println("Nicht genug Mana. Wähle eine andere Attacke.")
                    return attack(target, factor)
                }
            }
            3 -> {
                if (this.manaPoints >= 15) {
                    this.manaPoints -= 15
                    return attacking(input - 1, target, factor)
                } else {
                    println("Nicht genug Mana. Wähle eine andere Attacke.")
                    return attack(target, factor)
                }
            }
            4 -> {
                if (this.manaPoints >= 40) {
                    this.manaPoints -= 40
                    return attacking(input - 1, target, factor)
                } else {
                    println("Nicht genug Mana. Wähle eine andere Attacke.")
                    return attack(target, factor)
                }
            }
            else -> {
                println("Eingabe nicht möglich. Versuche erneut!")
                return attack(target, factor)
            }
        }
    }

    private fun attacking(entry: Int, target: Combatant, factor: Double): Int {
        val hitInfo = (this.attacks.entries.elementAt(entry))
        val attackName: String = hitInfo.key
        val attackHP: Int = hitInfo.value.times(factor).toInt()
        println("\n${CYAN + this.name + RESET} attackiert ${BLUE + target.name + RESET} mit '${attackName}' und verursacht ${RED + attackHP + RESET}HP schaden.")
        overallDamageGiven += attackHP
        return attackHP
    }

    override fun takeDamage(damage: Int, factor: Double) {
        if (damage >= this.hpCurrent) {
            this.hpCurrent = 0
        } else {
            this.hpCurrent -= damage
            if (this.hpCurrent >= this.hp.minus(this.hp.div(3))) {
                println("${CYAN + this.name + RESET} hat nun ${GREEN + this.hpCurrent + RESET}HP")
            } else if (this.hpCurrent >= this.hp.minus(this.hp.div(3).times(2))) {
                println("${CYAN + this.name + RESET} hat nun ${YELLOW + this.hpCurrent + RESET}HP")
            } else {
                println("${CYAN + this.name + RESET} hat nun ${RED + this.hpCurrent + RESET}HP")
            }
        }
    }

    override fun printStatus(): Boolean {
        if (this.hpCurrent <= 0) {
            println("${CYAN + this.name + RESET} wurde besiegt!")
            return true
        } else {
            println("Name: ${CYAN + this.name + RESET}")
            if (this.hpCurrent >= this.hp.minus(this.hp.div(3))) {
                println("HP:   ${GREEN + this.hpCurrent + RESET}")
            } else if (this.hpCurrent >= this.hp.minus(this.hp.div(3).times(2))) {
                println("HP:   ${YELLOW + this.hpCurrent + RESET}")
            } else {
                println("HP:   ${RED + this.hpCurrent + RESET}")
            }
            println("Mana: ${PURPLE + this.manaPoints + RESET}")
        }
        return false
    }

    override fun showStatsSmall(): List<Any> {
        return listOf(this.name, this.hpCurrent, this.manaPoints)
    }

    override fun useItem(item: Item): Boolean {
        if (item.name == "Manatrank" && this.manaPoints < 40) {
            this.manaPoints = 250
            println("\nDie Mana von ${this.name} wurde vollständig aufgefüllt.\n")
            return true
        } else if (item.name == "Manatrank" && this.manaPoints > 40) {
            println("\nDer Held ${this.name} hat aktuell ${this.manaPoints} das auffüllen lohnt sich nicht!\n")
            return false
        } else if (item.name == "Heiltrank" && this.hpCurrent <= this.hp.div(2)) {
            hpCurrent = this.hp
            println("\nDer Held ${this.name} wurde vollständig geheilt.\n")
            return true
        } else {
            println("\nDas Leben von ${this.name} ist über die hälfte voll und wird somit nicht geheilt.\n")
            return false
        }
    }

    override fun checkStats(): Boolean {
        return (this.hpCurrent <= this.hp.div(2) || this.manaPoints < 40)
    }

    override fun resetStats() {
        this.manaPoints = 150
        this.hpCurrent = this.hp
    }

    fun revive(heroesList: MutableList<Hero>, currentHeroes: MutableList<Hero>): MutableList<Hero> {
        for (hero in heroesList) {
            if (hero in currentHeroes) {
                continue
            } else {
                currentHeroes.add(hero)
                revivedHeroes++
                println("${CYAN_BOLD_BRIGHT + hero.name + RESET} wurde mit ${GREEN + hero.hp + RESET}HP" +
                        " von ${CYAN + this.name + RESET} wiederbelebt und ist nun wieder kampf tüchtig!\n")
                Thread.sleep(500)
            }
        }
        return currentHeroes
    }
}