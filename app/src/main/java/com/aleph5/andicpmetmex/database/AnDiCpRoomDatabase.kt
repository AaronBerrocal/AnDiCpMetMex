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
        SystemTypeEntity::class,
        EventTypeEntity::class,
        PriorityEntity::class,
        StatusEntity::class
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
    abstract fun eventTypeDao(): EventTypeDao
    abstract fun priorityDao(): PriorityDao
    abstract fun statusDao(): StatusDao

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
                        database.systemTypeDao(),
                        database.eventTypeDao(),
                        database.priorityDao(),
                        database.statusDao()
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
            systemTypeDao: SystemTypeDao,
            eventTypeDao: EventTypeDao,
            priorityDao: PriorityDao,
            statusDao: StatusDao
        ) {

            eventDao.deleteAllEvents()
            plantDao.deleteAllPlants()
            areaDao.deleteAllAreas()
            subareaDao.deleteAllSubareas()
            equipmentDao.deleteAllEquipment()
            systemTypeDao.deleteAllSystemTypes()
            eventTypeDao.deleteAllEventTypes()
            priorityDao.deleteAllPriorities()
            statusDao.deleteAllStatus()

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
                "fl",
                "Falla",
                "b",
                "Baja",
                "abtev",
                "Abierto",
                "mmoreno",
                "ext. 5959",
                "marcos_moreno@penoles.com.mx",
                Date(),
                null,
                null,
                null,
                null,
                null,
                null,
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
                "slt",
                "Solicitud",
                "b",
                "Baja",
                "ejecev",
                "Ejecución",
                "gmarquez",
                "ext. 5959",
                "gabriel_marquez@penoles.com.mx",
                Date(),
                null,
                null,
                null,
                null,
                null,
                null,
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

            //region inject subarea data
            val subareas = ArrayList<SubareaEntity>()
            subareas.add(
                SubareaEntity(
                    0,
                    "elct",
                    "ELECTROLISIS",
                    "jlam;gmarquez",
                    null,
                    1,
                    "ref",
                    "aptd",
                    "elct - ELECTROLISIS"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "hdim",
                    "HORNOS DE INDUCCION DE MOLDEO",
                    "jlam;gmarquez",
                    null,
                    1,
                    "ref",
                    "aptd",
                    "hdim - HORNOS DE INDUCCION DE MOLDEO"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "csref2",
                    "CASA DE SACOS 2",
                    "jlam",
                    null,
                    1,
                    "ref",
                    "csref",
                    "csref2 - CASA DE SACOS 2"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "csref3",
                    "CASA DE SACOS 3",
                    "jlam",
                    null,
                    1,
                    "ref",
                    "csref",
                    "csref3 - CASA DE SACOS 3"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "pda4",
                    "PAILA DE ALEACIONES 4",
                    "jmunoz",
                    null,
                    1,
                    "alz",
                    "zamak",
                    "pda4 - PAILA DE ALEACIONES 4"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "rdm",
                    "RUEDOS DE MOLDEO",
                    "jmunoz",
                    null,
                    1,
                    "alz",
                    "zamak",
                    "rdm - RUEDOS DE MOLDEO"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "csalz1",
                    "CASA DE SACOS 1",
                    "jmunoz",
                    null,
                    1,
                    "alz",
                    "csalz",
                    "csalz1 - CASA DE SACOS 1"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "csalz2",
                    "CASA DE SACOS 2",
                    "jmunoz",
                    null,
                    1,
                    "alz",
                    "csalz",
                    "csalz2 - CASA DE SACOS 2"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "mdccg",
                    "Manejo de Concentrados",
                    "hromero",
                    null,
                    1,
                    "zinc",
                    "tstz",
                    "mdccg - Manejo de Concentrados"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "fps",
                    "FPS",
                    "hromero",
                    null,
                    1,
                    "zinc",
                    "tstz",
                    "fps - FPS"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "esf1",
                    "Esfera 1",
                    "jlam",
                    null,
                    1,
                    "ftr",
                    "alm",
                    "esf1 - Esfera 1"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "esf2",
                    "Esfera 2",
                    "jlam",
                    null,
                    1,
                    "ftr",
                    "alm",
                    "esf2 - Esfera 2"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "lab1",
                    "Laboratorio 1",
                    "jlam",
                    null,
                    1,
                    "aa",
                    "lc",
                    "lab1 - Laboratorio 1"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "lab2",
                    "Laboratorio 2",
                    "jlam",
                    null,
                    1,
                    "aa",
                    "lc",
                    "lab2 - Laboratorio 2"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "ci",
                    "CARRO INCLINADO",
                    "apalos;ehernandez",
                    null,
                    1,
                    "fnc",
                    "hns",
                    "ci - CARRO INCLINADO"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "cgd",
                    "CARGADERO",
                    "apalos;ehernandez",
                    null,
                    1,
                    "fnc",
                    "hns",
                    "cgd - CARGADERO"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "p13",
                    "PAILA 13.5",
                    "hromero",
                    null,
                    1,
                    "brm",
                    "hrt",
                    "p13 - PAILA 13.5"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "p280",
                    "PAILA 280 TONS",
                    "hromero",
                    null,
                    1,
                    "brm",
                    "hrt",
                    "p280 - PAILA 280 TONS"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "cm",
                    "CARCAMO MUNICIPAL",
                    "hromero",
                    null,
                    1,
                    "tag",
                    "p2",
                    "cm - CARCAMO MUNICIPAL"
                )
            )
            subareas.add(
                SubareaEntity(
                    0,
                    "cdb",
                    "CARCAMO DEL BOSQUE",
                    "hromero",
                    null,
                    1,
                    "tag",
                    "p2",
                    "cdb - CARCAMO DEL BOSQUE"
                )
            )

            subareaDao.bulkInsertSubareas(subareas)
            //endregion inject subarea data

            //region inject equipment data
            val equipments = ArrayList<EquipmentEntity>()

            equipments.add(
                EquipmentEntity(
                    0,
                    "chr",
                    "Cuchara",
                    null,
                    1,
                    "ref",
                    "aptd",
                    "elct",
                    "chr - Cuchara"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "chm",
                    "Chimenea",
                    null,
                    1,
                    "ref",
                    "aptd",
                    "hdim",
                    "chm - Chimenea"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "ht",
                    "Horno Retentor",
                    null,
                    1,
                    "ref",
                    "csref",
                    "csref2",
                    "ht - Horno Retentor"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "bda",
                    "Bombas de Alim. / Recirc. L3",
                    null,
                    1,
                    "ref",
                    "csref",
                    "csref3",
                    "bda - Bombas de Alim. / Recirc. L3"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "hr",
                    "Horno Retentor",
                    null,
                    1,
                    "alz",
                    "zamak",
                    "pda4",
                    "hr - Horno Retentor"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "cmyr",
                    "Caldera Myrggo",
                    null,
                    1,
                    "alz",
                    "zamak",
                    "rdm",
                    "cmyr - Caldera Myrggo"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "chra",
                    "Cuchara A",
                    null,
                    1,
                    "alz",
                    "csalz",
                    "csalz1",
                    "chra - Cuchara A"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "chma",
                    "Chimenea A",
                    null,
                    1,
                    "alz",
                    "csalz",
                    "csalz2",
                    "chma - Chimenea A"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "hta",
                    "Horno Retentor A",
                    null,
                    1,
                    "zinc",
                    "tstz",
                    "mdccg",
                    "hta - Horno Retentor A"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "bdaa",
                    "Bombas de Alim. / Recirc. L3 A",
                    null,
                    1,
                    "zinc",
                    "tstz",
                    "fps",
                    "bdaa - Bombas de Alim. / Recirc. L3 A"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "hra",
                    "Horno Retentor A",
                    null,
                    1,
                    "ftr",
                    "alm",
                    "esf1",
                    "hra - Horno Retentor A"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "cmyra",
                    "Caldera Myrggo A",
                    null,
                    1,
                    "ftr",
                    "alm",
                    "esf2",
                    "cmyra - Caldera Myrggo A"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "chrb",
                    "Cuchara B",
                    null,
                    1,
                    "aa",
                    "lc",
                    "lab1",
                    "chrb - Cuchara B"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "chmb",
                    "Chimenea B",
                    null,
                    1,
                    "aa",
                    "lc",
                    "lab2",
                    "chmb - Chimenea B"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "htb",
                    "Horno Retentor B",
                    null,
                    1,
                    "fnc",
                    "hns",
                    "ci",
                    "htb - Horno Retentor B"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "bdab",
                    "Bombas de Alim. / Recirc. L3 B",
                    null,
                    1,
                    "fnc",
                    "hns",
                    "cgd",
                    "bdab - Bombas de Alim. / Recirc. L3 B"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "hrb",
                    "Horno Retentor B",
                    null,
                    1,
                    "brm",
                    "hrt",
                    "p13",
                    "hrb - Horno Retentor B"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "cmyrb",
                    "Caldera Myrggo B",
                    null,
                    1,
                    "brm",
                    "hrt",
                    "p280",
                    "cmyrb - Caldera Myrggo B"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "chrc",
                    "Cuchara C",
                    null,
                    1,
                    "tag",
                    "p2",
                    "cm",
                    "chrc - Cuchara C"
                )
            )
            equipments.add(
                EquipmentEntity(
                    0,
                    "chmc",
                    "Chimenea C",
                    null,
                    1,
                    "tag",
                    "p2",
                    "cdb",
                    "chmc - Chimenea C"
                )
            )

            equipmentDao.bulkInsertEquipment(equipments)
            //endregion inject equipment data

            //region inject system type data
            val systemTypes = ArrayList<SystemTypeEntity>()
            systemTypes.add(
                SystemTypeEntity(
                    0,
                    "ctrl",
                    "Control",
                    "Sistemas de Control",
                    1,
                    "ctrl_eventos",
                    "ctrl - Control"
                )
            )
            systemTypes.add(
                SystemTypeEntity(
                    0,
                    "exp",
                    "Experto",
                    "Sistemas expertos",
                    1,
                    "ctrl_eventos",
                    "exp - Experto"
                )
            )
            systemTypes.add(
                SystemTypeEntity(
                    0,
                    "anl",
                    "Analizador",
                    "Analizadores",
                    1,
                    "ctrl_eventos",
                    "anl - Analizador"
                )
            )

            systemTypeDao.bulkInsertSystemTypes(systemTypes)
            //endregion inject system type data

            //region inject event type data
            val eventTypes = ArrayList<EventTypeEntity>()
            eventTypes.add(
                EventTypeEntity(
                    0,
                    "fl",
                    "falla",
                    1,
                    "fl - falla"
                )
            )
            eventTypes.add(
                EventTypeEntity(
                    0,
                    "slt",
                    "solicitud",
                    1,
                    "slt - solicitud"
                )
            )

            eventTypeDao.bulkInsertEventTypes(eventTypes)
            //endregion inject event type data

            //region inject priority data
            val priorities = ArrayList<PriorityEntity>()
            priorities.add(
                PriorityEntity(
                    0,
                    "a",
                    "alta",
                    1,
                    "a - alta"
                )
            )
            priorities.add(
                PriorityEntity(
                    0,
                    "m",
                    "media",
                    1,
                    "m - media"
                )
            )
            priorities.add(
                PriorityEntity(
                    0,
                    "b",
                    "baja",
                    1,
                    "b - baja"
                )
            )

            priorityDao.bulkInsertPriorities(priorities)
            //endregion inject priority data

            //region inject status data
            val status = ArrayList<StatusEntity>()
            status.add(
                StatusEntity(
                    0,
                    "abtev",
                    "abierto",
                    null,
                    1,
                    "ctrl_eventos",
                    "abtev - abierto"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "dgntev",
                    "diagnóstico",
                    null,
                    1,
                    "ctrl_eventos",
                    "dgntev - diagnóstico"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "ejecev",
                    "ejecución",
                    null,
                    1,
                    "ctrl_eventos",
                    "ejecev - ejecución"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "pdteev",
                    "pendiente",
                    null,
                    1,
                    "ctrl_eventos",
                    "pdteev - pendiente"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "crdev",
                    "cerrado",
                    null,
                    1,
                    "ctrl_eventos",
                    "crdev - cerrado"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "ejecpy",
                    "ejecución",
                    null,
                    1,
                    "ctrl_proy",
                    "ejecpy - ejecución"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "rtspy",
                    "retrasado",
                    null,
                    1,
                    "ctrl_proy",
                    "rtspy - retrasado"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "crdpy",
                    "cerrado",
                    null,
                    1,
                    "ctrl_proy",
                    "crdpy - cerrado"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "cnclpy",
                    "cancelado",
                    null,
                    1,
                    "ctrl_proy",
                    "cnclpy - cancelado"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "susppy",
                    "suspendido",
                    null,
                    1,
                    "ctrl_proy",
                    "susppy - suspendido"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "vgncv",
                    "vigente",
                    null,
                    1,
                    "convenios_spte",
                    "vgncv - vigente"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "vnccv",
                    "vencido",
                    null,
                    1,
                    "convenios_spte",
                    "vnccv - vencido"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "ccelcv",
                    "cancelado",
                    null,
                    1,
                    "convenios_spte",
                    "ccelcv - cancelado"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "vgnan",
                    "vigente",
                    null,
                    1,
                    "anexos",
                    "vgnan - vigente"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "vncan",
                    "vencido",
                    null,
                    1,
                    "anexos",
                    "vncan - vencido"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "ccelan",
                    "cancelado",
                    null,
                    1,
                    "anexos",
                    "ccelan - cancelado"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "usoea",
                    "uso",
                    null,
                    1,
                    "equipo_anexo",
                    "usoea - uso"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "stockea",
                    "stock",
                    null,
                    1,
                    "equipo_anexo",
                    "stockea - stock"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "repea",
                    "reparación",
                    null,
                    1,
                    "equipo_anexo",
                    "repea - reparación"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "bajaea",
                    "baja",
                    null,
                    1,
                    "equipo_anexo",
                    "bajaea - baja"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "dispem",
                    "disponible",
                    null,
                    1,
                    "equipo_medicion",
                    "dispem - disponible"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "aptem",
                    "apartado",
                    null,
                    1,
                    "equipo_medicion",
                    "aptem - apartado"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "ndispem",
                    "no disponible",
                    null,
                    1,
                    "equipo_medicion",
                    "ndispem - no disponible"
                )
            )
            status.add(
                StatusEntity(
                    0,
                    "bajaem",
                    "baja",
                    null,
                    1,
                    "equipo_medicion",
                    "bajaem - baja"
                )
            )

            statusDao.bulkInsertStatus(status)
            //endregion inject status data

        }
    }
}