package database.dao

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import database.dao.BuildingDao.Buildings
import database.dao.BuildingDao.Building
import database.dao.PersonDao.Persons
import database.dao.PersonDao.Person

object RoomDao {

    object Rooms : IntIdTable() {

        val bid = reference("bid", Buildings, onDelete = ReferenceOption.CASCADE)
        val name = varchar("name", 45).uniqueIndex()
    }

    class Room(id: EntityID<Int>) : IntEntity(id) {
        companion object : IntEntityClass<Room>(Rooms)

        val persons by Person referrersOn Persons.room
        var building by Building referencedOn Rooms.bid
        var name by Rooms.name
    }
}