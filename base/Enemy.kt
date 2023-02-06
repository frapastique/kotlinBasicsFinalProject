abstract class Enemy(name: String, hp: Int) : Combatant(name, hp) {
    override fun attack(target: Combatant, factor: Double): Int {
        var attack: String = this.attacks.entries.random().key
        var damage: Int = this.attacks[attack]!!
        println("${this.name} attackiert ${target.name} mit '$attack' und verursacht " + damage + "HP schaden.")
        Thread.sleep(500)
        return damage
    }
}