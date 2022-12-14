package database

import database.DatabaseCore.connection
import org.jetbrains.exposed.sql.transactions.transaction
import database.dao.BuildingDao.Buildings
import database.dao.BuildingDao.Building
import database.dao.RoomDao.Rooms
import database.dao.RoomDao.Room
import org.jetbrains.exposed.sql.*
import database.dao.PersonDao.Persons
import database.dao.PersonDao.Person

fun main() {
    Main.start()
}

object Main {

    fun start() {
        transaction(connection) {
            addLogger(StdOutSqlLogger)

            SchemaUtils.createMissingTablesAndColumns(
                Buildings,
                Rooms,
                Persons
            )

            Buildings.deleteAll()
            Rooms.deleteAll()
            Persons.deleteAll()

            val ijzersteden = Building.new {
                name = "ijzersteden"
            }

            val marssteden = Building.new {
                name = "marssteden"
            }

            val rood = Room.new {
                building = ijzersteden
                name = "rood"
            }

            val blauw = Room.new {
                building = ijzersteden
                name = "blauw"
            }

            val ruben = Person.new {
                name = "ruben"
                room = rood
            }

            val jw = Person.new {
                name = "jw"
                room = rood
            }

            commit()

            println("Buildings:")
            Building.all().forEach {
                println("- ${it.name} = ${it.id}")
            }

            println("something:")
            Room.all().forEach {
                println("- ${it.building} - ${it.name}")
                it.persons.forEach { println("-- ${it.name}") }
            }
        }
    }
}