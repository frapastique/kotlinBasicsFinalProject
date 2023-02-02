class MageHero(name: String, hp: Int) : Hero (name, hp) {
    override var attacks: Map<String, Int> = mutableMapOf(
        "Dolch" to 150,
        "Betäubung" to 200,
        "Eis" to 250,
        "Blitz" to 300,
        )
    override val hasMana: Boolean = true
    var hpCurrent: Int = this.hp
    var manaPoints: Int = 250
    override fun attack(target: Combatant): Int {
        println("Wähle eine attacke:")
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
                    print(" (Mana -20)")
                }
            }
            j++
        }
        when (val input: Int = Input().checkInput()) {
            1 -> {
                return attacking(input - 1, target)
            }
            2 -> {
                if (this.manaPoints >= 10) {
                    this.manaPoints -= 10
                    return attacking(input - 1, target)
                } else {
                    println("Nicht genug Mana. Wähle eine andere Attacke.")
                    return attack(target)
                }
            }
            3 -> {
                if (this.manaPoints >= 15) {
                    this.manaPoints -= 15
                    return attacking(input - 1, target)
                } else {
                    println("Nicht genug Mana. Wähle eine andere Attacke.")
                    return attack(target)
                }
            }
            4 -> {
                if (this.manaPoints >= 20) {
                    this.manaPoints -= 20
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
        printStatus()
    }
    override fun printStatus() {
        println(
            "Name: ${this.name}\n" +
                    "HP:   ${this.hpCurrent}\n" +
                    "Mana: ${this.manaPoints}"
        )
        if (this.hpCurrent <= 0) {
            println("Außer gefecht.")
        }
    }
    override fun useSpecialMove() {
        TODO("Not yet implemented")
    }
}