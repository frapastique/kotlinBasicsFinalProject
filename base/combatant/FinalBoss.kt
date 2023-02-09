package combatant

import utils.BLUE
import utils.GREEN
import utils.RED
import utils.RESET
import utils.YELLOW
import utils.defeatedBosses

class FinalBoss(name: String, hp: Int) : Enemy(name, hp) {
    override val attacks: MutableMap<String, Int> = mutableMapOf(
        "Flügelschlag" to 100,
        "Kralle" to 150,
        "Biss" to 200,
        "Feuer Atem" to 250,
        "Feuer Ball" to 300,
    )
    private var hpCurrent: Int = this.hp

    override fun takeDamage(damage: Int, factor: Double) {
        if (damage >= this.hpCurrent) {
            this.hpCurrent = 0
        } else {
            this.hpCurrent -= damage
            if (this.hpCurrent >= this.hp.minus(this.hp.div(3))) {
                println("${BLUE + this.name + RESET} hat nun ${GREEN + this.hpCurrent + RESET}HP")
            } else if (this.hpCurrent >= this.hp.minus(this.hp.div(3).times(2))) {
                println("${BLUE + this.name + RESET} hat nun ${YELLOW + this.hpCurrent + RESET}HP")
            } else {
                println("${BLUE + this.name + RESET} hat nun ${RED + this.hpCurrent + RESET}HP")
            }
        }
    }

    override fun printStatus(): Boolean {
        if (this.hpCurrent <= 0) {
            println("${BLUE + this.name + RESET} wurde besiegt!")
            defeatedBosses++
            return true
        } else {
            println("Name: ${BLUE + this.name + RESET}")
            if (this.hpCurrent >= this.hp.minus(this.hp.div(3))) {
                println("HP:   ${GREEN + this.hpCurrent + RESET}")
            } else if (this.hpCurrent >= this.hp.minus(this.hp.div(3).times(2))) {
                println("HP:   ${YELLOW + this.hpCurrent + RESET}")
            } else {
                println("HP:   ${RED + this.hpCurrent + RESET}")
            }
        }
        return false
    }

    override fun showStatsSmall(): List<Any> {
        return listOf(this.name, this.hpCurrent)
    }

    override fun toString(): String {
        val info: String = (
                "Name:      ${this.name}\n" +
                "HP:        ${this.hp}\n" +
                "Atk 1:     ${this.attacks.entries.elementAt(0).key}\n" +
                "Dmg:       ${this.attacks.entries.elementAt(0).value}\n" +
                "Atk 2:     ${this.attacks.entries.elementAt(1).key}\n" +
                "Dmg:       ${this.attacks.entries.elementAt(1).value}\n" +
                "Atk 3:     ${this.attacks.entries.elementAt(2).key}\n" +
                "Dmg:       ${this.attacks.entries.elementAt(2).value}\n" +
                "Atk 4:     ${this.attacks.entries.elementAt(3).key}\n" +
                "Dmg:       ${this.attacks.entries.elementAt(3).value}\n" +
                "Atk 5:     ${this.attacks.entries.elementAt(4).key}\n" +
                "Dmg:       ${this.attacks.entries.elementAt(4).value}")
        return info
    }
}