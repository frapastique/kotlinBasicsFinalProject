package combatant

import utils.BLUE
import utils.CYAN
import utils.RED
import utils.RESET
import utils.overallDamageReceived

abstract class Enemy(name: String, hp: Int) : Combatant(name, hp) {
    override fun attack(target: Combatant, factor: Double): Int {
        var attack: String = this.attacks.entries.random().key
        if (attack == "Feuer Atem") {
            return 7777
        } else {
            var damage: Int = this.attacks[attack]!!
            println("${BLUE + this.name + RESET} attackiert ${CYAN + target.name + RESET} mit '$attack' und verursacht " + RED + damage + RESET + "HP schaden.")
            Thread.sleep(500)
            overallDamageReceived += damage
            return damage
        }
    }
}