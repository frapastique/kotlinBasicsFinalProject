class RangeHero(name: String, hp: Int) : Hero(name, hp) {
    override var attacks: MutableMap<String, Int> = mutableMapOf(
        "Pfeil" to 150,
        "Armbrust" to 200,
        "Feuer Pfeil" to 250,
        "Blitz Pfeil" to 300,
    )
    override val hasMana: Boolean = true
    var hpCurrent: Int = this.hp
    var manaPoints: Int = 150

    override fun attack(target: Combatant): Int {
        println("\nWähle eine attacke:")
        var damage: Int
        var j: Int = 1
        val fire: String = this.attacks.entries.elementAt(2).key
        val bolt: String = this.attacks.entries.elementAt(3).key
        for (i in attacks) {
            print("\n($j) -> ${i.value}HP mit ${i.key}")
            if (i.key == fire)
                print(" (Mana -30)")
            if (i.key == bolt)
                print(" (Mana -40)")
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
                if (this.manaPoints >= 30) {
                    this.manaPoints -= 30
                    return attacking(input - 1, target)
                } else {
                    println("Nicht genug Mana. Wähle eine andere Attacke.")
                    return attack(target)
                }
            }
            4 -> {
                if (this.manaPoints >= 40) {
                    this.manaPoints -= 40
                    return attacking(input - 1, target)
                } else {
                    println("Nicht genug Mana. Wähle eine andere Attacke.")
                    return attack(target)
                }
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
                    "HP:   ${this.hpCurrent}\n" +
                    "Mana: ${this.manaPoints}")
        }
        return false
    }

    override fun showStatsSmall(): List<Any> {
        return listOf(this.name, this.hpCurrent, this.manaPoints)
    }
}