package database

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import database.RoomDao.Rooms
import database.RoomDao.Room
import org.jetbrains.exposed.sql.ReferenceOption

object PersonDao {

    object Persons : IntIdTable() {

        val name = varchar("name", 45).uniqueIndex()
        val room = reference("room", Rooms, onDelete = ReferenceOption.CASCADE)
    }

    class Person(id: EntityID<Int>) : IntEntity(id) {
        companion object : IntEntityClass<Person>(Persons)

        var name by Persons.name
        var room by Room referencedOn Persons.room
    }
}