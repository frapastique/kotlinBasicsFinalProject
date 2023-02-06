class LeadHero(name: String, hp: Int): Hero(name, hp) {
    override var attacks: MutableMap<String, Int> = mutableMapOf(
        "Schwert" to 250,
        "Axt" to 350,
        "Eis" to 300,
        "Blitz" to 400,
        )
    override val hasMana: Boolean = true
    var hpCurrent: Int = this.hp
    var manaPoints: Int = 150

    override fun attack(target: Combatant, factor: Double): Int {
        println("\nWähle eine attacke:")
        var j: Int = 1
        val ice: String = this.attacks.entries.elementAt(2).key
        val bolt: String = this.attacks.entries.elementAt(3).key
        for (i in attacks) {
            print("\n($j) -> ${i.value}HP mit ${i.key}")
            if (i.key == ice)
                print(" (Mana -20)")
            if (i.key == bolt)
                print(" (Mana -50)")
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
                if (this.manaPoints >= 20) {
                    this.manaPoints -= 20
                    return attacking(input - 1, target, factor)
                } else {
                    println("Nicht genug Mana. Wähle eine andere Attacke.")
                    return attack(target, factor)
                }
            }
            4 -> {
                if (this.manaPoints >= 50) {
                    this.manaPoints -= 50
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
    fun motivateAll(heroes: MutableList<Hero>) {
        for (hero in heroes) {
            for (attack in hero.attacks) {
                val randomPercent = (5..20).random().toDouble()
                for (entry in hero.attacks.entries) {
                    val newValue = entry.value + (entry.value * (randomPercent / 100)).toInt()
                    TODO("create new heroes and return them")
                }
            }
        }
    }
}