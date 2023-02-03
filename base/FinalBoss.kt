class FinalBoss(name: String, hp: Int) : Enemy(name, hp) {
    override val attacks: MutableMap<String, Int> = mutableMapOf(
        "Flügelschlag" to 100,
        "Kralle" to 150,
        "Biss" to 200,
        "Feuer Atem" to 250,
        "Feuer Ball" to 300,
    )
    private var hpCurrent: Int = this.hp
    var counter: Int = 1

    override fun takeDamage(damage: Int) {
        if (damage >= this.hpCurrent) {
            this.hpCurrent = 0
        } else {
            this.hpCurrent -= damage
        }
        this.counter++
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

    override fun toString(): String {
        val info: String = (
                "Name:      ${this.name}\n" +
                "HP:        ${this.hp}\n" +
                "Atk 1:     ${this.attacks.entries.elementAt(0).key}\n" +
                "Dmg:       ${this.attacks.entries.elementAt(0).value}\n" +
                "Atk 2:     ${this.attacks.entries.elementAt(1).key}\n" +
                "Dmg:       ${this.attacks.entries.elementAt(1).value}\n" +
                "Atk 3:     ${this.attacks.entries.elementAt(2).key}\n" +
                "Dmg:       ${this.attacks.entries.elementAt(2).value}\n" +
                "Atk 4:     ${this.attacks.entries.elementAt(3).key}\n" +
                "Dmg:       ${this.attacks.entries.elementAt(3).value}\n" +
                "Atk 5:     ${this.attacks.entries.elementAt(4).key}\n" +
                "Dmg:       ${this.attacks.entries.elementAt(4).value}")
        return info
    }
}