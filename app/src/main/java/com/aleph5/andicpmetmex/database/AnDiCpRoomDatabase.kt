package com.aleph5.andicpmetmex.database

import android.content.Context
import android.os.Build
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aleph5.andicpmetmex.daos.EventDao
import com.aleph5.andicpmetmex.entities.EventEntity
import com.aleph5.andicpmetmex.utilityclasses.Converters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Database(
    entities = arrayOf(EventEntity::class),
    version = 1
//    ,
//    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AnDiCpRoomDatabase : RoomDatabase() {

    abstract fun EventDao() : EventDao

//    val MIGRATION_1_2 = object : Migration(1, 2) {
//        override fun migrate(database: SupportSQLiteDatabase) {
//            database.execSQL("include queries for updating database changes here")
//        }
//    }

    companion object{

        @Volatile
        private var instance: AnDiCpRoomDatabase? = null

        fun getInstance(
            context: Context,
            scope: CoroutineScope
        ): AnDiCpRoomDatabase {
            return instance ?: synchronized(this){
                instance ?: buildDatabase(context, scope).also{ instance = it}
            }
        }

        private fun buildDatabase(context: Context, scope: CoroutineScope): AnDiCpRoomDatabase{
            return Room.databaseBuilder(context, AnDiCpRoomDatabase::class.java, "andi_cp_database")
                .fallbackToDestructiveMigration()
//                .addMigrations()
                .addCallback(AnDiCpRoomDatabaseCallback(scope))
                .build()
        }

    }

    private class AnDiCpRoomDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback(){

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

            instance?.let { database ->
                scope.launch {
                    populateDatabase(database.EventDao())
                }
            }

        }

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
        }

        suspend fun populateDatabase(eventDao: EventDao){

            eventDao.deleteAllEvents()

            var newEvent = EventEntity(
                0,
                "evnt0001",
                "refn0001",
                "Refineria",
                "area0001",
                "Apartado",
                "sbra0001",
                "Moldeo",
                "eqpm0001",
                "Cuchara",
                "ctrl0001",
                "Control",
                2, //2
                "Falla", //Falla
                3, //1 //2
                "Baja", //Alta //Media
                1, //2 //3 //4
                "Abierto", //Diagnóstico //Ejecución //Pendiente
                "mmoreno",
                "ext. 5959",
                "marcos_moreno@penoles.com.mx",
                Date(),
                null,
                null,
                null,
                null,
                1,
                "Atendido por CDP",
                null,
                null,
                null,
                null,
                null,
                null
            )
            eventDao.insertEvent(newEvent)

            newEvent = EventEntity(
                0,
                "evnt0002",
                "refn0001",
                "Refineria",
                "area0001",
                "Apartado",
                "sbra0001",
                "Moldeo",
                "eqpm0002",
                "Horno",
                "xprt0002",
                "Experto",
                1, //2
                "Solicitud", //Falla
                3, //1 //2
                "Baja", //Alta //Media
                3, //2 //3 //4
                "Ejecución", //Diagnóstico //Ejecución //Pendiente
                "gmarquez",
                "ext. 5959",
                "gabriel_marquez@penoles.com.mx",
                Date(),
                null,
                null,
                null,
                null,
                1,
                "Atendido por CDP",
                null,
                null,
                null,
                null,
                null,
                null
            )
            eventDao.insertEvent(newEvent)
        }

    }
}

//        suspend fun populateDatabasePlants(plantDao: PlantDao) {
//
//            plantDao.deleteAllPlants()
//
//            val plants = ArrayList<PlantEntity>()
//
//            plants.add(
//                PlantEntity(
//                    0,
//                    "PLN001",
//                    "Refinería",
//                    "PLN001 - Refinería",
//                    1
//                )
//            )
//            plants.add(
//                PlantEntity(
//                    0,
//                    "PLN002",
//                    "Aleazin",
//                    "PLN002 - Aleazin",
//                    1
//                )
//            )
//            plants.add(
//                PlantEntity(
//                    0,
//                    "PLN003",
//                    "Zinc",
//                    "PLN003 - Zinc",
//                    1
//                )
//            )
//
//            plantDao.bulkInsertPlants(plants)
//        }