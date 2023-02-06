class MageHero(name: String, hp: Int) : Hero (name, hp) {
    override var attacks: Map<String, Int> = mutableMapOf(
        "Dolch" to 150,
        "Betäubung" to 200,
        "Eis" to 250,
        "Blitz" to 350,
        )
    override val hasMana: Boolean = true
    var hpCurrent: Int = this.hp
    var manaPoints: Int = 250
    override fun attack(target: Combatant, factor: Double): Int {
        println("\nWähle eine attacke:")
        var damage: Int
        var j: Int = 1
        var stun: String = this.attacks.entries.elementAt(1).key
        var ice: String = this.attacks.entries.elementAt(2).key
        var bolt: String = this.attacks.entries.elementAt(3).key
        for (i in attacks) {
            print("\n($j) -> ${i.value}HP mit ${i.key}")
            when (i.key) {
                stun -> {
                    print(" (Mana -10)")
                }
                ice -> {
                    print(" (Mana -15)")
                }
                bolt -> {
                    print(" (Mana -40)")
                }
            }
            j++
        }
        when (val input: Int = Input().checkInput()) {
            1 -> {
                return attacking(input - 1, target, factor)
            }
            2 -> {
                if (this.manaPoints >= 10) {
                    this.manaPoints -= 10
                    return attacking(input - 1, target, factor)
                } else {
                    println("Nicht genug Mana. Wähle eine andere Attacke.")
                    return attack(target, factor)
                }
            }
            3 -> {
                if (this.manaPoints >= 15) {
                    this.manaPoints -= 15
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
        println("${this.name} attackiert ${target.name} mit '${hitInfo.key}' und verursacht ${hitInfo.value.times(factor.toInt())}HP schaden.")
        return hitInfo.value.times(factor.toInt())
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
                    "HP:   ${this.hpCurrent}\n" +
                    "Mana: ${this.manaPoints}")
        }
        return false
    }

    override fun showStatsSmall(): List<Any> {
        return listOf(this.name, this.hpCurrent, this.manaPoints)
    }

    fun useSpecialMove() {
        TODO("Not yet implemented")
    }
}