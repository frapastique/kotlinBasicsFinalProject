class HealPotion(name: String, description: String, quantity: Int) : Item(name, description, quantity) {

    fun use(hero: Hero) {}
}