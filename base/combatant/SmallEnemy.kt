package combatant

import utils.BLUE
import utils.GREEN
import utils.RED
import utils.RESET
import utils.YELLOW
import utils.defeatedSmallEnemies

class SmallEnemy(name: String, hp: Int, override var attacks: Map<String, Int>) : Enemy(name, hp) {
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
            println("${BLUE + this.name + RESET} wurde besiegt!\n")
            defeatedSmallEnemies++
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
        Thread.sleep(200)
        val info: String = (
                "Name:      ${this.name}\n" +
                "HP:        ${this.hp}\n" +
                "Atk 1:     ${this.attacks.entries.elementAt(0).key}\n" +
                "Dmg:       ${this.attacks.entries.elementAt(0).value}\n" +
                "Atk 2:     ${this.attacks.entries.elementAt(1).key}\n" +
                "Dmg:       ${this.attacks.entries.elementAt(1).value}")
        return info
    }
}