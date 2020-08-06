package com.aleph5.andicpmetmex.database

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import com.aleph5.andicpmetmex.daos.*
import com.aleph5.andicpmetmex.entities.*
import com.aleph5.andicpmetmex.utilityclasses.Converters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

@Database(
    entities = [
        EventEntity::class,
        PlantEntity::class,
        AreaEntity::class,
        SubareaEntity::class,
        EquipmentEntity::class,
        SystemTypeEntity::class
    ],
    version = 1
//    ,
//    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AnDiCpRoomDatabase : RoomDatabase() {

    abstract fun eventDao(): EventDao
    abstract fun plantDao(): PlantDao
    abstract fun areaDao(): AreaDao
    abstract fun subareaDao(): SubareaDao
    abstract fun equipmentDao(): EquipmentDao
    abstract fun systemTypeDao(): SystemTypeDao

//    val MIGRATION_1_2 = object : Migration(1, 2) {
//        override fun migrate(database: SupportSQLiteDatabase) {
//            database.execSQL("include queries for updating database changes here")
//        }
//    }

    companion object {

        @Volatile
        private var instance: AnDiCpRoomDatabase? = null

        fun getInstance(
            context: Context,
            scope: CoroutineScope
        ): AnDiCpRoomDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context, scope).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context, scope: CoroutineScope): AnDiCpRoomDatabase {
            return Room.databaseBuilder(context, AnDiCpRoomDatabase::class.java, "andi_cp_database")
                .fallbackToDestructiveMigration()
//                .addMigrations()
                .addCallback(AnDiCpRoomDatabaseCallback(scope))
                .build()
        }

    }

    private class AnDiCpRoomDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)

            instance?.let { database ->
                scope.launch {
                    populateDatabase(
                        database.eventDao(),
                        database.plantDao(),
                        database.areaDao(),
                        database.subareaDao(),
                        database.equipmentDao(),
                        database.systemTypeDao()
                    )
                }
            }

        }

//        override fun onCreate(db: SupportSQLiteDatabase) {
//            super.onCreate(db)
//        }

        suspend fun populateDatabase(
            eventDao: EventDao,
            plantDao: PlantDao,
            areaDao: AreaDao,
            subareaDao: SubareaDao,
            equipmentDao: EquipmentDao,
            systemTypeDao: SystemTypeDao
        ) {

            eventDao.deleteAllEvents()
            plantDao.deleteAllPlants()
            areaDao.deleteAllAreas()
            subareaDao.deleteAllSubareas()
            equipmentDao.deleteAllEquipment()
            systemTypeDao.deleteAllSystemTypes()

            //region inject event data

            var newEvent = EventEntity(
                0,
                "vnt1",
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
                "vnt2",
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

            //endregion inject event data

            //region inject plant data
            val plants = ArrayList<PlantEntity>()

            plants.add(
                PlantEntity(
                    0,
                    "ref",
                    "Refinería",
                    "jmunoz",
                    1,
                    "ref - Refinería"
                )
            )
            plants.add(
                PlantEntity(
                    0,
                    "alz",
                    "Aleazín",
                    "jmunoz",
                    1,
                    "alz - Aleazín"
                )
            )
            plants.add(
                PlantEntity(
                    0,
                    "zinc",
                    "Zinc",
                    "fherrera",
                    1,
                    "zinc - Zinc"
                )
            )
            plants.add(
                PlantEntity(
                    0,
                    "ftr",
                    "Fertirey",
                    "jlam",
                    1,
                    "ftr - Fertirey"
                )
            )
            plants.add(
                PlantEntity(
                    0,
                    "aa",
                    "Areas Apoyo",
                    "rjacobo",
                    1,
                    "aa - Areas Apoyo"
                )
            )
            plants.add(
                PlantEntity(
                    0,
                    "fnc",
                    "Fundición",
                    "mmoreno",
                    1,
                    "fnc - Fundición"
                )
            )
            plants.add(
                PlantEntity(
                    0,
                    "brm",
                    "Bermejillo",
                    "hromero",
                    1,
                    "brm - Bermejillo"
                )
            )
            plants.add(
                PlantEntity(
                    0,
                    "tag",
                    "Tratamiento de Aguas",
                    "hromero",
                    1,
                    "tag - Tratamiento de Aguas"
                )
            )


            plantDao.bulkInsertPlants(plants)
            //endregion inject plant data

            //region inject area data
            val areas = ArrayList<AreaEntity>()

            areas.add(
                AreaEntity(
                    0,
                    "aptd",
                    "Apartado",
                    "jlam;gmarquez",
                    null,
                    1,
                    "ref",
                    "aptd - Apartado"
                )
            )
            areas.add(
                AreaEntity(
                    0,
                    "csref",
                    "Casas de Sacos",
                    "jlam",
                    null,
                    1,
                    "ref",
                    "csref - Casas de Sacos"
                )
            )
            areas.add(
                AreaEntity(
                    0,
                    "zamak",
                    "zamak",
                    "jmunoz",
                    null,
                    1,
                    "alz",
                    "zamak - zamak"
                )
            )
            areas.add(
                AreaEntity(
                    0,
                    "csalz",
                    "casa de sacos",
                    "jmunoz",
                    null,
                    1,
                    "alz",
                    "csalz - casa de sacos"
                )
            )
            areas.add(
                AreaEntity(
                    0,
                    "tstz",
                    "Tostación",
                    "hromero",
                    null,
                    1,
                    "zinc",
                    "tstz - Tostación"
                )
            )
            areas.add(
                AreaEntity(
                    0,
                    "alm",
                    "Almacenamiento de amoniaco",
                    "jlam",
                    null,
                    1,
                    "ftr",
                    "alm - Almacenamiento de amoniaco"
                )
            )
            areas.add(
                AreaEntity(
                    0,
                    "lc",
                    "Laboratorio Central",
                    "apalos;gmarquez",
                    null,
                    1,
                    "aa",
                    "lc - Laboratorio Central"
                )
            )
            areas.add(
                AreaEntity(
                    0,
                    "hns",
                    "Hornos",
                    "apalos;ehernandez",
                    null,
                    1,
                    "fnc",
                    "hns - Hornos"
                )
            )
            areas.add(
                AreaEntity(
                    0,
                    "hrt",
                    "Horno rotatorio",
                    "hromero",
                    null,
                    1,
                    "brm",
                    "hrt - Horno rotatorio"
                )
            )
            areas.add(
                AreaEntity(
                    0,
                    "p2",
                    "Planta 2",
                    "hromero",
                    null,
                    1,
                    "tag",
                    "p2 - Planta 2"
                )
            )

            areaDao.bulkInsertAreas(areas)
            //endregion inject area data


        }

    }
}