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

    override fun attack(target: Combatant, factor: Double): Int {
        println("\nWähle eine attacke:")
        var j: Int = 1
        val fire: String = this.attacks.entries.elementAt(2).key
        val bolt: String = this.attacks.entries.elementAt(3).key
        for (entry in attacks) {
            var attackName: String = entry.key
            var attackHP: Int = (entry.value.times(factor)).toInt()
            print("($j) -> ${attackHP}HP mit $attackName")
            if (attackName == fire)
                print(" (Mana -30)")
            if (attackName == bolt)
                print(" (Mana -40)")
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
                if (this.manaPoints >= 30) {
                    this.manaPoints -= 30
                    return attacking(input - 1, target, factor)
                } else {
                    println("Nicht genug Mana. Wähle eine andere Attacke.")
                    return attack(target, factor)
                }
            }
            4 -> {
                if (this.manaPoints >= 40) {
                    this.manaPoints -= 40
                    return attacking(input - 1, target, factor)
                } else {
                    println("Nicht genug Mana. Wähle eine andere Attacke.")
                    return attack(target, factor)
                }
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
        println("\n${this.name} attackiert ${target.name} mit '${attackName}' und verursacht ${attackHP}HP schaden.")
        return attackHP
    }

    override fun takeDamage(damage: Int, factor: Double) {
        if (damage >= this.hpCurrent) {
            this.hpCurrent = 0
        } else {
            this.hpCurrent -= damage
            println("${this.name} hat nun ${this.hpCurrent}HP")
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

    override fun useItem(item: Item): Boolean {
        if (item.name == "Manatrank" && this.manaPoints < 50) {
            this.manaPoints = 150
            return true
        } else if (item.name == "Manatrank" && this.manaPoints >= 50) {
            println("Der Held ${this.name} hat aktuell ${this.manaPoints} das auffüllen lohnt sich nicht!")
            return false
        } else if (item.name == "Heiltrank" && this.hpCurrent <= this.hp.div(2)) {
            hpCurrent = this.hp
            println("Der Held ${this.name} wurde vollständig geheilt.")
            return true
        } else {
            println("Das Leben von ${this.name} ist über die hälfte voll und wird somit nicht geheilt.")
            return false
        }
    }
}