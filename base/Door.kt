open class Door {
    private var isLocked = true
    fun unlock(enemies: List<SmallEnemy>) {
        if (enemies.all { it.hp <= 0 }) {
            isLocked = false
        }
    }
}