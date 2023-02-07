class SmallEnemy(name: String, hp: Int, override var attacks: Map<String, Int>) : Enemy(name, hp) {
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
            println("${ANSI_BLUE + this.name + ANSI_RESET} wurde besiegt!\n")
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