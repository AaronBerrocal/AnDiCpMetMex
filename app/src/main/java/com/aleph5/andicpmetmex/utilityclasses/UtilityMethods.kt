package com.aleph5.andicpmetmex.utilityclasses

import android.os.Build
import com.aleph5.andicpmetmex.datamodels.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

object UtilityMethods {

    fun generateEventList(size: Int): ArrayList<EventData>{

        val list = ArrayList<EventData>()

        for(i in 0 until size){
            val item = EventData(
                "idPlanta $i",
                "Planta $i",
                "idArea $i",
                "Area $i",
                "idSubarea $i",
                "Subarea $i",
                "idEquipoPlanta $i",
                "EquipoPlanta $i",
                "idTipoSistema $i",
                "TipoSistema $i",
                "idTipoEvento $i",
                "TipoEvento $i",
                "User $i",
                "Ext. $i, Tel. 8${i} 3876 9876",
                "user${i}@email.com"
            )
            list +=item
        }

        return list
    }

    fun generateProjectList(size: Int): ArrayList<ProjectData>{

        val list = ArrayList<ProjectData>()

        for(i in 0 until size){
            val item = ProjectData(
                "idProyecto $i",
                "Proyecto con Nombre $i",
                "User $i",
                "idPlanta $i",
                "Planta $i",
                "idArea $i",
                "Area $i",
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    (LocalDateTime.now().minusDays(i.toLong())).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                }else{
                    SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
                },
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    (LocalDateTime.now().plusDays(i.toLong())).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                }else{
                    SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
                },
                "${2*i}",
                2,
                "Media",
                i.rem(5)+1,
                when(i.rem(5)+1){
                    1 -> "En Ejecución"
                    2 -> "Retrasado"
                    3 -> "Cerrado"
                    4 -> "Suspendido"
                    5 -> "Cancelado"
                    else -> "En Ejecución"
                },
                "{\"1\":34, \"2\":20, \"3\":12, \"4\":34}",
                "${(i*Random.nextInt(10)).rem(100)}",
                "${(i*Random.nextInt(10)).rem(100)}",
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                }else{
                    SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
                },
                "Comentario $i"
            )
            list += item
        }

        return list
    }

    fun generateAgreementList(size: Int) : ArrayList<AgreementData>{

        val list = ArrayList<AgreementData>()

        for(i in 0 until size){
            val item = AgreementData(
                "idProveedor $i",
                "Proveedor $i",
                "123456789",
                "+52 80 0909 0100",
                Random.nextInt(13) + 12,
                "01-01-2020",
                "31-12-2020",
                "https://drive.google.com/file/d/1hllQjFy3rIkPl-ja__bKfiGo3qjms-6y/view?usp=sharing",
                "https://www.emerson.com/es-mx/automation/deltav",
                Random.nextInt(3) + 1,
                when(Random.nextInt(3) + 1){
                    1 -> "Vigente"
                    2 -> "Vencido"
                    3 -> "Cancelado"
                    else -> "Cancelado"
                },
                "Comentarios $i"
            )

            list += item
        }

        return list
    }

    fun generateUtilityList(size: Int) : ArrayList<UtilityData>{

        val list = ArrayList<UtilityData>()

        for(i in 0 until size){
            val item = UtilityData(
                "idProvedor $i",
                "Proveedor $i",
                "Utilería $i",
                "192.168.1.1",
                "https://drive.google.com/file/d/1hllQjFy3rIkPl-ja__bKfiGo3qjms-6y/view?usp=sharing",
                "https://drive.google.com/file/d/1hllQjFy3rIkPl-ja__bKfiGo3qjms-6y/view?usp=sharing",
                "com.emerson.deltavmobile",
                "https://www.emerson.com/es-mx/automation/deltav",
                Random.nextInt(2)
            )

            list  += item
        }

        return list
    }

    fun generatePartList(size: Int) : ArrayList<PartData>{

        val list = ArrayList<PartData>()

        for(i in 0 until size){
            val item = PartData(
                "numeroSerie $i",
                "Refacción $i",
                "idTipoRefaccion $i",
                "idInventario $i",
                when(Random.nextInt(2)){
                    0 -> "Arrendamiento Emerson"
                    1 -> "Inventario Met-Mex"
                    else -> "Inventario Met-Mex"
                },
                Random.nextInt(3) + 1,
                when(Random.nextInt(3) + 1){
                    1 -> "Disponible"
                    2 -> "Apartada"
                    3 -> "No Disponible"
                    else -> "No Disponible"
                },
                Random.nextInt(2) + 1,
                Random.nextInt(8) + 1,
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                }else{
                    SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
                }
            )

            list += item
        }

        return list
    }

    fun generateComputingEquipmentList(size: Int) : ArrayList<ComputingEquipmentData>{

        val list = ArrayList<ComputingEquipmentData>()

        for(i in 0 until size){
            val item = ComputingEquipmentData(
                "idTipoEquipo $i",
                "tipoEquipo $i",
                "serviceTag $i",
                "idProveedor $i",
                "Proveedor $i",
                Random.nextInt(2) + 1,
                when(Random.nextInt(2) + 1){
                    1 -> "Uso"
                    2 -> "Stock"
                    else -> "Stock"
                },
                Random.nextInt(4) + 1,
                when(Random.nextInt(4) + 1){
                    1 -> "Uso"
                    2 -> "Stock"
                    3 -> "Reparación"
                    4 -> "Baja"
                    else -> "Stock"
                },
                72.65,
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                }else{
                    SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
                },
                "A-522",
                Random.nextInt(3) + 1,
                when(Random.nextInt(3) + 1){
                    1 -> "Vigente"
                    2 -> "Vencido"
                    3 -> "Cancelado"
                    else -> "Cancelado"
                },
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                }else{
                    SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
                }
            )

            list += item
        }

        return  list
    }

    fun generateMeasureLabelList(size: Int) : ArrayList<MeasureLabelData>{

        val list = ArrayList<MeasureLabelData>()

        for(i in 0 until size){

            val item = MeasureLabelData(
                "serialNumber $i",
                "Equipo $i",
                "Marca $i",
                "idTipoEquipo $i",
                "Tipo Equipo $i",
                Random.nextInt(3) + 1,
                when(Random.nextInt(3) + 1){
                    1 -> "Disponible"
                    2 -> "Reparación"
                    3 -> "Baja"
                    else -> "Baja"
                },
                Random.nextInt(2),
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                }else{
                    SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
                },
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                }else{
                    SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(Date())
                }
            )

            list += item
        }

        return list
    }


}