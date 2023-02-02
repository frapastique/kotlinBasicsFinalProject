class LeadHero(name: String, hp: Int): Combatant(name, hp) {
    override var attacks: MutableList<String> = mutableListOf(
        "Schwert", "Axt", "Eis", "Blitz"
    )
    override val hasMana: Boolean = true
    override fun attack(target: Combatant): Int {
        TODO("Not yet implemented")
    }

    override fun takeDamage(damage: Int) {
        TODO("Not yet implemented")
    }

    override fun useSpecialMove() {
        TODO("Not yet implemented")
    }

    override fun printStatus() {
        TODO("Not yet implemented")
    }

    override fun move(direction: String) {
        TODO("Not yet implemented")
    }
}