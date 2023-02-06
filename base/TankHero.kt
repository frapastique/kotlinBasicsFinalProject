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
            print("\n($j) -> ${attackHP}HP mit $attackName")
            j++
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
        println("${this.name} attackiert ${target.name} mit '${attackName}' und verursacht ${attackHP}HP schaden.")
        return attackHP
    }
    override fun takeDamage(damage: Int, factor: Double) {
        if (damage >= this.hpCurrent) {
            this.hpCurrent = 0
        } else {
            this.hpCurrent -= damage
        }
    }
    override fun printStatus(): Boolean {
        if (this.hpCurrent <= 0) {
            println("${this.name} wurde besiegt!")
            return true
        } else {
            println("Name: ${this.name}\n" +
                    "HP:   ${this.hpCurrent}")
        }
        return false
    }

    override fun showStatsSmall(): List<Any> {
        return listOf(this.name, this.hpCurrent)
    }
}