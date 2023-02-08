class TankHero(name: String, hp: Int): Hero(name, hp) {
    override var attacks: MutableMap<String, Int> = mutableMapOf(
        "Schwert" to 250,
        "Axt" to 300,
        "Sprung Schwert" to 350,
        "Sprung Axt" to 400,
    )
    var hpCurrent: Int = this.hp

    override fun attack(target: Combatant, factor: Double): Int {
        println("\nWähle eine attacke:")
        var j: Int = 1
        for (entry in attacks) {
            var attackName: String = entry.key
            var attackHP: Int = (entry.value.times(factor)).toInt()
            print("($j) -> ${attackHP}HP mit $attackName")
            j++
            println()
        }
        when (val input: Int = Input().checkInput()) {
            1 -> {
                return attacking(input - 1, target, factor)
            }
            2 -> {
                return attacking(input - 1, target, factor)
            }
            3 -> {
                return attacking(input - 1, target, factor)
            }
            4 -> {
                return attacking(input - 1, target, factor)
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
        }
        return false
    }

    override fun showStatsSmall(): List<Any> {
        return listOf(this.name, this.hpCurrent)
    }

    override fun useItem(item: Item): Boolean {
        if (item.name == "Heiltrank" && this.hpCurrent <= this.hp.div(2)) {
            hpCurrent = this.hp
            println("\nDer Held ${this.name} wurde vollständig geheilt.")
            return true
        } else {
            println("\nDas Leben von ${this.name} ist über die hälfte voll und wird somit nicht geheilt.")
            return false
        }
    }

    override fun checkStats(): Boolean {
        return this.hpCurrent <= this.hp.div(2)
    }

    override fun resetStats() {
        this.hpCurrent = this.hp
    }
}