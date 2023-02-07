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
                println("${ANSI_BLUE + this.name + ANSI_RESET} hat nun ${ANSI_GREEN + this.hpCurrent + ANSI_RESET}HP")
            } else if (this.hpCurrent >= this.hp.minus(this.hp.div(3).times(2))) {
                println("${ANSI_BLUE + this.name + ANSI_RESET} hat nun ${ANSI_YELLOW + this.hpCurrent + ANSI_RESET}HP")
            } else {
                println("${ANSI_BLUE + this.name + ANSI_RESET} hat nun ${ANSI_RED + this.hpCurrent + ANSI_RESET}HP")
            }
        }
    }

    override fun printStatus(): Boolean {
        if (this.hpCurrent <= 0) {
            println("${ANSI_BLUE + this.name + ANSI_RESET} wurde besiegt!")
            return true
        } else {
            println("Name: ${ANSI_BLUE + this.name + ANSI_RESET}")
            if (this.hpCurrent >= this.hp.minus(this.hp.div(3))) {
                println("HP:   ${ANSI_GREEN + this.hpCurrent + ANSI_RESET}")
            } else if (this.hpCurrent >= this.hp.minus(this.hp.div(3).times(2))) {
                println("HP:   ${ANSI_YELLOW + this.hpCurrent + ANSI_RESET}")
            } else {
                println("HP:   ${ANSI_RED + this.hpCurrent + ANSI_RESET}")
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