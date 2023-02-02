class Dungeon(var rooms: MutableList<Room>) {

    override fun toString(): String {
        for (i in rooms) {
            var roomName= i.roomName
            var enemies = i.enemies
            var door = i.door
            println(Room(roomName, enemies, door))
        }
        return ""
    }
}