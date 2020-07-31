package com.aleph5.andicpmetmex.ui.eventos

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.aleph5.andicpmetmex.R
import com.aleph5.andicpmetmex.entities.EventEntity
import com.aleph5.andicpmetmex.utilityclasses.expandCollapse
import com.aleph5.andicpmetmex.utilityclasses.hideKeyboard
import kotlinx.android.synthetic.main.fragment_reportar_evento.view.*
import java.util.*

class ReportarEventoFragment : Fragment() {

    companion object {
        fun newInstance() = ReportarEventoFragment()
    }

    private lateinit var viewModel: AdministracionEventosViewModel
    private lateinit var plantaEtv: EditText
    private lateinit var areaEtv: EditText
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

        plantaEtv = root.etv_report_event_planta_value
        areaEtv = root.etv_report_event_area_value
        subareaEtv = root.etv_report_event_subarea_value
        equipoEtv = root.etv_report_event_equipo_planta_value
        tipoSistemaEtv = root.etv_report_event_tipo_sistema_value
        tipoEventoEtv = root.etv_report_event_tipo_evento_value
        prioridadEtv = root.etv_report_event_prioridad_value
        estatusEtv = root.etv_report_event_estatus_value
        nombreSolicitanteEtv = root.etv_report_event_nombre_solicitante_value
        contactoSolicitanteEtv = root.etv_report_event_contacto_solicitante_value
        correoSolicitanteEtv = root.etv_report_event_correo_solicitante_value



        ubicacionTv.setOnClickListener{
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

        cancelMbtn.setOnClickListener {
            hideKeyboard()
            findNavController().navigateUp()
        }

        saveMbtn.setOnClickListener {
            hideKeyboard()
            if(validateEventReportData()){
                viewModel.insertEventVm(EventEntity(
                    0,
                    "evnt${currentEventsCount + 1}",
                    "refn${currentEventsCount + 1}",
                    plantaEtv.text.toString(),
                    "area${currentEventsCount + 1}",
                    areaEtv.text.toString(),
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

            }else{
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

        viewModel = ViewModelProvider(this).get(AdministracionEventosViewModel::class.java)
        viewModel.eventsCount.observe(viewLifecycleOwner, Observer {eventsCount ->
            this.currentEventsCount = eventsCount
        })

    }

    private fun validateEventReportData() : Boolean {

        return !(plantaEtv.text.trim().isEmpty() ||
                areaEtv.text.trim().isEmpty() ||
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