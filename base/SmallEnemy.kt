class SmallEnemy(name: String, hp: Int) : Enemy(name, hp) {
    override var attacks: MutableMap<String, Int> = mutableMapOf(
        "Schwert" to 50,
        "Axt" to 70,
        "Pfeil" to 50,
        "Armbrust" to 70,
        "Messer" to 20,
        "Dolch" to 50,
        )
    var hpCurrent: Int = this.hp
    override fun attack(target: Combatant): Int {
        var attack: String = this.attacks.entries.random().key
        var damage: Int = this.attacks[attack]!!
        println("${this.name} attackiert ${target.name} mit '$attack' und verursacht " + damage + "HP schaden.")
        return damage
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
        println("Name: ${this.name}\n" +
                "HP:   ${this.hpCurrent}")
        if (this.hpCurrent <= 0) {
            println("Außer gefecht.")
        }
    }
}