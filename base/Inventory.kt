class Inventory(var items: MutableList<Item>) {
    fun addItem(item: Item, amount: Int) {
        item.increaseQuantity(amount)
    }
    fun removeItem(item: Item, amount: Int) {
        item.decreaseQuantity(amount)
    }
    fun useItem(item: Item, amount: Int) {
        removeItem(item, amount)
    }
}