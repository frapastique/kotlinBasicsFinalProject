class SmallEnemy(name: String, hp: Int, override var attacks: Map<String, Int>) : Enemy(name, hp) {
    private var hpCurrent: Int = this.hp

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
                    "HP:   ${this.hpCurrent}")
        }
        return false
    }

    override fun toString(): String {
        Thread.sleep(200)
        val info: String = (
                "Name:      ${this.name}\n" +
                "HP:        ${this.hp}\n" +
                "Atk 1:     ${this.attacks.entries.elementAt(0).key}\n" +
                "Dmg:       ${this.attacks.entries.elementAt(0).value}\n" +
                "Atk 2:     ${this.attacks.entries.elementAt(1).key}\n" +
                "Dmg:       ${this.attacks.entries.elementAt(1).value}")
        return info
    }
}