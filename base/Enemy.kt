abstract class Enemy(name: String, hp: Int) : Combatant(name, hp) {
    override fun attack(target: Combatant, factor: Double): Int {
        var attack: String = this.attacks.entries.random().key
        if (attack == "Feuer Atem") {
            return 7777
        } else {
            var damage: Int = this.attacks[attack]!!
            println("${ANSI_BLUE + this.name + ANSI_RESET} attackiert ${ANSI_CYAN + target.name + ANSI_RESET} mit '$attack' und verursacht " + ANSI_RED + damage + ANSI_RESET + "HP schaden.")
            Thread.sleep(500)
            return damage
        }
    }
}