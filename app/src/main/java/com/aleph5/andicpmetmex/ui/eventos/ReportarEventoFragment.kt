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
    private lateinit var tipoEventoSpr: Spinner
    private var eventTypeList = ArrayList<EventTypeEntity>()
    private lateinit var eventTypeCustomAdapter: EventTypeArrayAdapter
    var itemEventTypeStr: String = ""
    private lateinit var prioridadSpr: Spinner
    private var priorityList = ArrayList<PriorityEntity>()
    private lateinit var priorityCustomAdapter: PriorityArrayAdapter
    var itemPriorityStr: String = ""
    private lateinit var estatusSpr: Spinner
    private var statusList = ArrayList<StatusEntity>()
    private lateinit var statusCustomAdapter: StatusArrayAdapter
    var itemStatusStr: String = ""

    //ETVs
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

        //region View Binding
        //MAIN VIEWS
        val ubicacionTv = root.txt_r_e_ubicacion_header
        val ubicacionLl = root.ll_ubicacion
        val caracteristicasTv = root.txt_r_e_caracteristicas_header
        val caracteristicasLl = root.ll_caracteristicas
        val solicitudTv = root.txt_r_e_solicitud_header
        val solicitudLl = root.ll_solicitud

        //ACTVs
        plantaActv = root.actv_report_event_planta_value
        areaActv = root.actv_report_event_area_value
        subareaActv = root.actv_report_event_subarea_value
        equipoActv = root.actv_report_event_equipo_planta_value
        //SPINNERs
        tipoSistemaSpr = root.spinner_report_event_tipo_sistema_value
        tipoEventoSpr = root.spinner_report_event_tipo_evento_value
        prioridadSpr = root.spinner_report_event_prioridad_value
        estatusSpr = root.spinner_report_event_estatus_value
        //ETVs
        nombreSolicitanteEtv = root.etv_report_event_nombre_solicitante_value
        contactoSolicitanteEtv = root.etv_report_event_contacto_solicitante_value
        correoSolicitanteEtv = root.etv_report_event_correo_solicitante_value

        //SAVE-CANCEL VIEWS
        val cancelMbtn = root.mbtn_report_event_cancel
        val saveMbtn = root.mbtn_report_event_save
        //endregion View Binding

        //region View Functionality
        //MAIN VIEWS
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

        //ACTVs
        plantCustomAdapter = PlantArrayAdapter(requireContext(), plantList)
        plantaActv.setAdapter(plantCustomAdapter)
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

        areaCustomAdapter = AreaArrayAdapter(requireContext(), areaList)
        areaActv.setAdapter(areaCustomAdapter)
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

        subareaCustomAdapter = SubareaArrayAdapter(requireContext(), subareaList)
        subareaActv.setAdapter(subareaCustomAdapter)
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

        equipmentCustomAdapter = EquipmentArrayAdapter(requireContext(), equipmentList)
        equipoActv.setAdapter(equipmentCustomAdapter)

        //SPINNERs
        systemTypeCustomAdapter = SystemTypeArrayAdapter(requireContext(), systemTypeList)
        tipoSistemaSpr.adapter = systemTypeCustomAdapter
        tipoSistemaSpr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                itemSystemTypeStr = (parent?.getItemAtPosition(position) as SystemTypeEntity).signature
            }
        }

        eventTypeCustomAdapter = EventTypeArrayAdapter(requireContext(), eventTypeList)
        tipoEventoSpr.adapter = eventTypeCustomAdapter
        tipoEventoSpr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                itemEventTypeStr = (parent?.getItemAtPosition(position) as EventTypeEntity).signature
            }
        }

        priorityCustomAdapter = PriorityArrayAdapter(requireContext(), priorityList)
        prioridadSpr.adapter = priorityCustomAdapter
        prioridadSpr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                itemPriorityStr = (parent?.getItemAtPosition(position) as PriorityEntity).signature
            }
        }

        statusCustomAdapter = StatusArrayAdapter(requireContext(), statusList)
        estatusSpr.adapter = statusCustomAdapter
        estatusSpr.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                itemStatusStr = (parent?.getItemAtPosition(position) as StatusEntity).signature
            }
        }

        //SAVE-CANCEL VIEWS
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
            val selectedEventTypeStr = itemEventTypeStr.split(" - ")
            val selectedPriorityStr = itemPriorityStr.split(" - ")
            val selectedStatusStr = itemStatusStr.split(" - ")

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
                        selectedEventTypeStr[0],
                        selectedEventTypeStr[1],
                        selectedPriorityStr[0],
                        selectedPriorityStr[1],
                        selectedStatusStr[0],
                        selectedStatusStr[1],
                        nombreSolicitanteEtv.text.toString(),
                        contactoSolicitanteEtv.text.toString(),
                        correoSolicitanteEtv.text.toString(),
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
                )

                findNavController().navigateUp()

            } else {
                val builder = AlertDialog.Builder(context)
                builder.setMessage("Información faltante para Reportar Evento. Todos los campos son Requeridos.")
                builder.setPositiveButton("Ententido") { _: DialogInterface, _: Int -> }
                builder.show()
            }
        }
        //endregion View Functionality

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

            viewModel.allEventTypes.observe(viewLifecycleOwner, Observer { eventTypes ->
                eventTypes?.let {
                    this.eventTypeList = it as ArrayList<EventTypeEntity>
                    eventTypeCustomAdapter.setEventTypes(it)
                }
            })

            viewModel.allPriorities.observe(viewLifecycleOwner, Observer { priorities ->
                priorities?.let {
                    this.priorityList = it as ArrayList<PriorityEntity>
                    priorityCustomAdapter.setPriorities(it)
                }
            })

            viewModel.searchStatusByModuleIdVm(EVENT_MODULE_ID).observe(viewLifecycleOwner, Observer { status ->
                status?.let {
                    this.statusList = it as ArrayList<StatusEntity>
                    statusCustomAdapter.setStatus(it)
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
                    itemEventTypeStr.trim().isEmpty() ||
                    itemPriorityStr.trim().isEmpty() ||
                    itemStatusStr.trim().isEmpty() ||
                    nombreSolicitanteEtv.text.trim().isEmpty() ||
                    contactoSolicitanteEtv.text.trim().isEmpty() ||
                    correoSolicitanteEtv.text.trim().isEmpty())

        }

    }