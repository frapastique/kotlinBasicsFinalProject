class FinalBoss(name: String, hp: Int) : Enemy(name, hp) {
    override val attacks: MutableMap<String, Int> = mutableMapOf(
        "Flügelschlag" to 100,
        "Kralle" to 150,
        "Biss" to 200,
        "Feuer Atem" to 250,
        "Feuer Ball" to 300,
    )
    private var hpCurrent: Int = this.hp

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