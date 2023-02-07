class HealPotion(name: String, description: String, quantity: Int) : Item(name, description, quantity) {

    override fun use(hero: Hero, item: Item): Boolean {
        return hero.useItem(item)
    }
}