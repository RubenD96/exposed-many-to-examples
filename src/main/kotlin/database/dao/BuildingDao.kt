package database.dao

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.IntIdTable

object BuildingDao {

    object Buildings : IntIdTable() {

        val name = varchar("name", 45).uniqueIndex()
    }

    class Building(id: EntityID<Int>) : IntEntity(id) {
        companion object : IntEntityClass<Building>(Buildings)

        var name by Buildings.name

        override fun toString(): String {
            return name
        }
    }
}