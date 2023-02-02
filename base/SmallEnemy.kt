class SmallEnemy(name: String, hp: Int, override var attacks: Map<String, Int>) : Enemy(name, hp) {
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