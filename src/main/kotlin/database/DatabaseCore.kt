package database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

object DatabaseCore {

    private val dataSource: HikariDataSource
    val connection: Database get() = Database.connect(dataSource)

    init {
        val config = HikariConfig()
        config.dataSourceClassName = "org.postgresql.ds.PGSimpleDataSource"
        config.addDataSourceProperty("serverName", "localhost")
        config.addDataSourceProperty("portNumber", "3307")
        config.addDataSourceProperty("databaseName", "test")

        config.username = "postgres"
        config.password = "password"
        config.maximumPoolSize = 20
        config.leakDetectionThreshold = 15 * 1000L
        config.maxLifetime = 595 * 1000L
        config.idleTimeout = 0 // disabled
        dataSource = HikariDataSource(config)
    }
}