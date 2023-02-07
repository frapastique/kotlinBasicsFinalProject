class Inventory(var items: MutableList<Item>) {
    fun addItem(item: Item, amount: Int) {
        item.increaseQuantity(amount)
    }
    fun removeItem(item: Item) {
        item.decreaseQuantity()
    }
    fun useItem(item: Item, hero: Hero): Boolean {
        if (item.use(hero, item)) {
            removeItem(item)
            return true
        } else {
            return false
        }
    }
}