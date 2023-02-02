class LeadHero(name: String, hp: Int): Combatant(name, hp) {
    override var attacks: MutableMap<String, Int> = mutableMapOf(
        "Schwert       " to 150,
        "Axt           " to 200,
        "Eis           " to 250,
        "Blitz         " to 300,
    )
    override val hasMana: Boolean = true
    var hpCurrent: Int = this.hp
    var manaPoints: Int = 150
    override fun attack(target: Combatant): Int {
        println("Wähle eine attacke:")
        var damage: Int
        var j: Int = 1
        val bolt: String = this.attacks.entries.elementAt(2).key
        val ice: String = this.attacks.entries.elementAt(3).key
        for (i in attacks) {
            print("\n($j) -> ${i.key} ${i.value}HP")
            if (i.key == bolt || i.key == ice)
                print(" (Mana -10)")
            j++
        }
        when (val input: Int = Input().checkInput()) {
            1 -> {
                damage = attacks(input - 1)
                return damage
            }
            2 -> {
                damage = attacks(input - 1)
                return damage
            }
            3 -> {
                if (this.manaPoints >= 10) {
                    this.manaPoints -= 10
                    damage = attacks(input - 1)
                    println("Mana: ${this.manaPoints}")
                    return damage
                } else {
                    println("Nicht genug Mana. Wähle eine andere Attacke.")
                    return attack(target)
                }
            }
            4 -> {
                if (this.manaPoints >= 15) {
                    this.manaPoints -= 15
                    damage = attacks(input - 1)
                    println("Mana: ${this.manaPoints}")
                    return damage
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
    private fun attacks(entry: Int): Int {
        val hitPoints = this.attacks.entries.elementAt(entry)
        return hitPoints.value
    }
    override fun takeDamage(damage: Int) {
        if (damage >= this.hpCurrent) {
            this.hpCurrent = 0
        } else {
            this.hpCurrent -= damage
        }
        printStatus()
    }

    override fun useSpecialMove() {
        TODO("Not yet implemented")
    }

    override fun printStatus() {
        println("Name: ${this.name}\n" +
                "HP:   ${this.hpCurrent}\n" +
                "Mana: ${this.manaPoints}\n")
        if (this.hpCurrent <= 0) {
            println("Außer gefecht.")
        }
    }

    override fun move(direction: String) {
        TODO("Not yet implemented")
    }
}