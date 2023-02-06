abstract class Enemy(name: String, hp: Int) : Combatant(name, hp) {
    override fun attack(target: Combatant, factor: Double): Int {
        var attack: String = this.attacks.entries.random().key
        if (attack == "Feuer Atem") {
            return 7777
        } else {
            var damage: Int = this.attacks[attack]!!
            println("${this.name} attackiert ${target.name} mit '$attack' und verursacht " + damage + "HP schaden.\n" +
                    "${target.name} hat nun ${target.showStatsSmall()[1]}HP")
            Thread.sleep(500)
            return damage
        }
    }
}