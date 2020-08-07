package com.aleph5.andicpmetmex.ui.eventos

import android.app.AlertDialog
import android.content.ContentValues.TAG
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.aleph5.andicpmetmex.R
import com.aleph5.andicpmetmex.adapters.*
import com.aleph5.andicpmetmex.entities.*
import com.aleph5.andicpmetmex.utilityclasses.*
import com.aleph5.andicpmetmex.viewmodels.AdministracionEventosViewModel
import kotlinx.android.synthetic.main.fragment_reportar_evento.view.*
import java.util.*
import kotlin.collections.ArrayList

class ReportarEventoFragment : Fragment() {

    companion object {
        fun newInstance() = ReportarEventoFragment()
    }

    private val viewModel: AdministracionEventosViewModel by viewModels {
        InjectorUtils.provideAdministracionEventosViewModelFactory(requireContext())
    }

    //ACTVs
    private lateinit var plantaActv: AutoCompleteTextView
    private var plantList = ArrayList<PlantEntity>()
    private lateinit var plantCustomAdapter: PlantArrayAdapter
    private lateinit var areaActv: AutoCompleteTextView
    private var areaList = ArrayList<AreaEntity>()
    private lateinit var areaCustomAdapter: AreaArrayAdapter
    private lateinit var subareaActv: AutoCompleteTextView
    private var subareaList = ArrayList<SubareaEntity>()
    private lateinit var subareaCustomAdapter: SubareaArrayAdapter
    private lateinit var equipoActv: AutoCompleteTextView
    private var equipmentList = ArrayList<EquipmentEntity>()
    private lateinit var equipmentCustomAdapter: EquipmentArrayAdapter

    //SPINNERs
    private lateinit var tipoSistemaSpr: Spinner
    private var systemTypeList = ArrayList<SystemTypeEntity>()
    private lateinit var systemTypeCustomAdapter: SystemTypeArrayAdapter
    var itemSystemTypeStr: String = ""
    private lateinit var tipoEventoEtv: EditText
    private lateinit var prioridadEtv: EditText
    private lateinit var estatusEtv: EditText

    private lateinit var nombreSolicitanteEtv: EditText
    private lateinit var contactoSolicitanteEtv: EditText
    private lateinit var correoSolicitanteEtv: EditText

    private var currentEventsCount: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_reportar_evento, container, false)

        val ubicacionTv = root.txt_r_e_ubicacion_header
        val ubicacionLl = root.ll_ubicacion
        val caracteristicasTv = root.txt_r_e_caracteristicas_header
        val caracteristicasLl = root.ll_caracteristicas
        val solicitudTv = root.txt_r_e_solicitud_header
        val solicitudLl = root.ll_solicitud

        val cancelMbtn = root.mbtn_report_event_cancel
        val saveMbtn = root.mbtn_report_event_save

        plantaActv = root.actv_report_event_planta_value
        areaActv = root.actv_report_event_area_value
        subareaActv = root.actv_report_event_subarea_value
        equipoActv = root.actv_report_event_equipo_planta_value
        tipoSistemaSpr = root.spinner_report_event_tipo_sistema_value
        tipoEventoEtv = root.etv_report_event_tipo_evento_value
        prioridadEtv = root.etv_report_event_prioridad_value
        estatusEtv = root.etv_report_event_estatus_value
        nombreSolicitanteEtv = root.etv_report_event_nombre_solicitante_value
        contactoSolicitanteEtv = root.etv_report_event_contacto_solicitante_value
        correoSolicitanteEtv = root.etv_report_event_correo_solicitante_value

        ubicacionTv.setOnClickListener {
            hideKeyboard()
            ubicacionLl.expandCollapse()
        }

        caracteristicasTv.setOnClickListener {
            hideKeyboard()
            caracteristicasLl.expandCollapse()
        }

        solicitudTv.setOnClickListener {
            hideKeyboard()
            solicitudLl.expandCollapse()
        }

        plantCustomAdapter = PlantArrayAdapter(requireContext(), plantList)
        plantaActv.setAdapter(plantCustomAdapter)
//        plantaActv.setOnClickDropDown()

        areaCustomAdapter = AreaArrayAdapter(requireContext(), areaList)
        areaActv.setAdapter(areaCustomAdapter)
//        areaActv.setOnClickDropDown()

        subareaCustomAdapter = SubareaArrayAdapter(requireContext(), subareaList)
        subareaActv.setAdapter(subareaCustomAdapter)
//        subareaActv.setOnClickDropDown()

        equipmentCustomAdapter = EquipmentArrayAdapter(requireContext(), equipmentList)
        equipoActv.setAdapter(equipmentCustomAdapter)
//        subareaActv.setOnClickDropDown()

        plantaActv.setOnItemClickListener { adapterView: AdapterView<*>, _: View, i: Int, _: Long ->
            areaActv.setText("")
            subareaActv.setText("")
            equipoActv.setText("")

            val selectedPlant = adapterView.getItemAtPosition(i) as PlantEntity
            if (areaList.size > 0) {
                val filteredAreas = ArrayList<AreaEntity>()
                for (a: AreaEntity in areaList) {
                    if (a.idPlanta == selectedPlant.idPlanta) {
                        filteredAreas.add(a)
                    }
                }
                areaCustomAdapter.setAreas(filteredAreas)
            }
        }

        areaActv.setOnItemClickListener { adapterView: AdapterView<*>, _: View, i: Int, _:Long ->
            subareaActv.setText("")
            equipoActv.setText("")

            val selectedArea = adapterView.getItemAtPosition(i) as AreaEntity
            if(subareaList.size > 0){
                val filteredSubareas = ArrayList<SubareaEntity>()
                for(s: SubareaEntity in subareaList){
                    if(s.idArea == selectedArea.idArea){
                        filteredSubareas.add(s)
                    }
                }
                subareaCustomAdapter.setSubareas(filteredSubareas)
            }
        }

        subareaActv.setOnItemClickListener { adapterView: AdapterView<*>, _: View, i: Int, _:Long ->
            equipoActv.setText("")

            val selectedSubarea = adapterView.getItemAtPosition(i) as SubareaEntity
            if(equipmentList.size > 0){
                val filteredEquipments = ArrayList<EquipmentEntity>()
                for(eqp: EquipmentEntity in equipmentList){
                    if(eqp.idSubrea == selectedSubarea.idSubarea){
                        filteredEquipments.add(eqp)
                    }
                }
                equipmentCustomAdapter.setEquipments(filteredEquipments)
            }
        }

        systemTypeCustomAdapter = SystemTypeArrayAdapter(requireContext(), systemTypeList)
        tipoSistemaSpr.adapter = systemTypeCustomAdapter

        tipoSistemaSpr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                itemSystemTypeStr = (parent?.getItemAtPosition(position) as SystemTypeEntity).signature
            }
        }

        cancelMbtn.setOnClickListener {
            hideKeyboard()
            findNavController().navigateUp()
        }

        saveMbtn.setOnClickListener {
            hideKeyboard()

            val selectedPlantStr = plantaActv.text.toString().split(" - ")
            val selectedAreaStr = areaActv.text.toString().split(" - ")
            val selectedSubareaStr = subareaActv.text.toString().split(" - ")
            val selectedEquipmentStr = equipoActv.text.toString().split(" - ")
            val selectedSystemTypeStr = itemSystemTypeStr.split(" - ")

            if (validateEventReportData()) {
                viewModel.insertEventVm(
                    EventEntity(
                        0,
                        "evnt${currentEventsCount + 1}",
                        selectedPlantStr[0],
                        selectedPlantStr[1],
                        selectedAreaStr[0],
                        selectedAreaStr[1],
                        selectedSubareaStr[0],
                        selectedSubareaStr[1],
                        selectedEquipmentStr[0],
                        selectedEquipmentStr[1],
                        selectedSystemTypeStr[0],
                        selectedSystemTypeStr[1],
                        2, //2
                        tipoEventoEtv.text.toString(), //Falla
                        3, //1 //2
                        prioridadEtv.text.toString(), //Alta //Media
                        1, //2 //3 //4
                        estatusEtv.text.toString(), //Diagnóstico //Ejecución //Pendiente
                        nombreSolicitanteEtv.text.toString(),
                        contactoSolicitanteEtv.text.toString(),
                        correoSolicitanteEtv.text.toString(),
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
                )

                findNavController().navigateUp()

            } else {
                val builder = AlertDialog.Builder(context)
                builder.setMessage("Información faltante para Reportar Evento. Todos los campos son Requeridos.")
                builder.setPositiveButton("Ententido") { _: DialogInterface, _: Int -> }
                builder.show()
            }

        }

        return root
    }

        override fun onActivityCreated(savedInstanceState: Bundle?) {
            super.onActivityCreated(savedInstanceState)

            viewModel.eventsCount.observe(viewLifecycleOwner, Observer { eventsCount ->
                this.currentEventsCount = eventsCount
            })

            viewModel.allPlants.observe(viewLifecycleOwner, Observer { plants ->
                plants?.let {
                    this.plantList = it as ArrayList<PlantEntity>
                    plantCustomAdapter.setPlants(it)
                }
            })

            viewModel.allAreas.observe(viewLifecycleOwner, Observer { areas ->
                areas?.let {
                    this.areaList = it as ArrayList<AreaEntity>
                    areaCustomAdapter.setAreas(it)
                }
            })

            viewModel.allSubareas.observe(viewLifecycleOwner, Observer { subareas ->
                subareas?.let {
                    this.subareaList = it as ArrayList<SubareaEntity>
                    subareaCustomAdapter.setSubareas(it)
                }
            })

            viewModel.allEquipments.observe(viewLifecycleOwner, Observer { equipments ->
                equipments?.let {
                    this.equipmentList = it as ArrayList<EquipmentEntity>
                    equipmentCustomAdapter.setEquipments(it)
                }
            })

            viewModel.searchSystemTypesByModuleIdVm(EVENT_MODULE_ID).observe(viewLifecycleOwner, Observer { systemTypes ->
                systemTypes?.let {
                    this.systemTypeList = it as ArrayList<SystemTypeEntity>
                    systemTypeCustomAdapter.setSystemTypes(it)
                }
            })

        }

        override fun onDestroyView() {
            super.onDestroyView()
            hideKeyboard()
        }

        private fun validateEventReportData(): Boolean {

            return !(plantaActv.text.trim().isEmpty() ||
                    areaActv.text.trim().isEmpty() ||
                    subareaActv.text.trim().isEmpty() ||
                    equipoActv.text.trim().isEmpty() ||
                    itemSystemTypeStr.trim().isEmpty() ||
                    tipoEventoEtv.text.trim().isEmpty() ||
                    prioridadEtv.text.trim().isEmpty() ||
                    estatusEtv.text.trim().isEmpty() ||
                    nombreSolicitanteEtv.text.trim().isEmpty() ||
                    contactoSolicitanteEtv.text.trim().isEmpty() ||
                    correoSolicitanteEtv.text.trim().isEmpty())

        }

    }