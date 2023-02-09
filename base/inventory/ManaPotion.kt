package inventory

import combatant.Hero

class ManaPotion(name: String, description: String, quantity: Int) : Item(name, description, quantity) {

    override fun use(hero: Hero, item: Item): Boolean {
        if (hero.hasMana) {
            return hero.useItem(item)
        } else {
            println("${hero.name} hat keine Mana.")
            return false
        }
    }
}