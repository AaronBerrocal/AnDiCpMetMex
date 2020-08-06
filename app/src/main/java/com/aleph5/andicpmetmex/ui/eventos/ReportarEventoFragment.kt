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
import com.aleph5.andicpmetmex.adapters.AreaArrayAdapter
import com.aleph5.andicpmetmex.adapters.PlantArrayAdapter
import com.aleph5.andicpmetmex.entities.AreaEntity
import com.aleph5.andicpmetmex.entities.EventEntity
import com.aleph5.andicpmetmex.entities.PlantEntity
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

    private lateinit var plantaActv: AutoCompleteTextView
    private var plantList = ArrayList<PlantEntity>()
    private lateinit var areaActv: AutoCompleteTextView
    private var areaList = ArrayList<AreaEntity>()
    private lateinit var subareaEtv: EditText
    private lateinit var equipoEtv: EditText
    private lateinit var tipoSistemaEtv: EditText

    private lateinit var tipoEventoEtv: EditText
    private lateinit var prioridadEtv: EditText
    private lateinit var estatusEtv: EditText

    private lateinit var nombreSolicitanteEtv: EditText
    private lateinit var contactoSolicitanteEtv: EditText
    private lateinit var correoSolicitanteEtv: EditText

    private var currentEventsCount: Int = 0

    private lateinit var plantCustomAdapter: PlantArrayAdapter
    private lateinit var areaCustomAdapter: AreaArrayAdapter

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
        subareaEtv = root.etv_report_event_subarea_value
        equipoEtv = root.etv_report_event_equipo_planta_value
        tipoSistemaEtv = root.etv_report_event_tipo_sistema_value
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

        plantaActv.setOnItemClickListener { adapterView: AdapterView<*>, _: View, i: Int, _: Long ->
            areaActv.setText("")

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

        cancelMbtn.setOnClickListener {
            hideKeyboard()
            findNavController().navigateUp()
        }

        saveMbtn.setOnClickListener {
            hideKeyboard()

            val selectedPlantStr = plantaActv.text.toString().split(" - ")
            val selectedAreaStr = areaActv.text.toString().split(" - ")

            if (validateEventReportData()) {
                viewModel.insertEventVm(
                    EventEntity(
                        0,
                        "evnt${currentEventsCount + 1}",
                        selectedPlantStr[0],
                        selectedPlantStr[1],
                        selectedAreaStr[0],
                        selectedAreaStr[1],
                        "sbra${currentEventsCount + 1}",
                        subareaEtv.text.toString(),
                        "eqpm${currentEventsCount + 1}",
                        equipoEtv.text.toString(),
                        "ctrl${currentEventsCount + 1}",
                        tipoSistemaEtv.text.toString(),
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
                Log.d(TAG, "observing Plants: " + plants)
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

        }

        override fun onDestroyView() {
            super.onDestroyView()
            hideKeyboard()
        }

        private fun validateEventReportData(): Boolean {

            return !(plantaActv.text.trim().isEmpty() ||
                    areaActv.text.trim().isEmpty() ||
                    subareaEtv.text.trim().isEmpty() ||
                    equipoEtv.text.trim().isEmpty() ||
                    tipoSistemaEtv.text.trim().isEmpty() ||
                    tipoEventoEtv.text.trim().isEmpty() ||
                    prioridadEtv.text.trim().isEmpty() ||
                    estatusEtv.text.trim().isEmpty() ||
                    nombreSolicitanteEtv.text.trim().isEmpty() ||
                    contactoSolicitanteEtv.text.trim().isEmpty() ||
                    correoSolicitanteEtv.text.trim().isEmpty())

        }


    }