class TankHero(name: String, hp: Int): Hero(name, hp) {
    override var attacks: MutableMap<String, Int> = mutableMapOf(
        "Schwert" to 250,
        "Axt" to 300,
        "Sprung Schwert" to 350,
        "Sprung Axt" to 400,
    )
    var hpCurrent: Int = this.hp

    override fun attack(target: Combatant): Int {
        println("\nWähle eine attacke:")
        var attack: String
        var damage: Int
        var j: Int = 1
        for (i in attacks) {
            print("\n($j) -> ${i.value}HP mit ${i.key}")
            j++
        }
        when (val input: Int = Input().checkInput()) {
            1 -> {
                return attacking(input - 1, target)
            }
            2 -> {
                return attacking(input - 1, target)
            }
            3 -> {
                return attacking(input - 1, target)
            }
            4 -> {
                return attacking(input - 1, target)
            }
            else -> {
                println("Eingabe nicht möglich. Versuche erneut!")
                return attack(target)
            }
        }
    }
    private fun attacking(entry: Int, target: Combatant): Int {
        val hitInfo = this.attacks.entries.elementAt(entry)
        println("${this.name} attackiert ${target.name} mit '${hitInfo.key}' und verursacht ${hitInfo.value}HP schaden.")
        return hitInfo.value
    }
    override fun takeDamage(damage: Int) {
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