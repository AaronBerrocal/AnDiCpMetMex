package com.aleph5.andicpmetmex.ui.eventos

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.transition.Slide
import android.transition.TransitionManager
import android.view.*
import android.widget.ImageButton
import android.widget.PopupWindow
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aleph5.andicpmetmex.R
import com.aleph5.andicpmetmex.adapters.AdministracionEventosAdapter
import com.aleph5.andicpmetmex.utilityclasses.InjectorUtils
import com.aleph5.andicpmetmex.viewmodels.AdministracionEventosViewModel
import kotlinx.android.synthetic.main.fragment_administracion_eventos.view.*
import kotlinx.android.synthetic.main.popup_window_guardia_info_layout.view.*

class AdministracionEventosFragment : Fragment() {

    companion object {
        fun newInstance() = AdministracionEventosFragment()
    }

    private val viewModel: AdministracionEventosViewModel by viewModels {
        InjectorUtils.provideAdministracionEventosViewModelFactory(requireContext())
    }
//    private lateinit var eventDataList : ArrayList<EventEntity>
    private var adapter = AdministracionEventosAdapter()

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        administracionEventosViewModel = ViewModelProvider(this).get(AdministracionEventosViewModel::class.java)
//        administracionEventosViewModel.allEvents.observe(this, Observer { events ->
//            events?.let { adapter.setEvents(it) }
//        })
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_administracion_eventos, container, false)

        val recyclerView: RecyclerView = root.event_recycler_view
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter

//        administracionEventosViewModel = ViewModelProvider(this).get(AdministracionEventosViewModel::class.java)
//        administracionEventosViewModel.allEvents.observe(viewLifecycleOwner, Observer { events ->
//            events?.let { adapter.setEvents(it) }
//        })

        val showWardInfoBtn : ImageButton = root.btn_watch_info

        showWardInfoBtn.setOnClickListener{
            showWardInfoPopupWindow(container, getString(R.string.telefono_guardia))
        }

        val reportNewEventBtn : ImageButton = root.btn_to_report

        reportNewEventBtn.setOnClickListener{
            findNavController().navigate(R.id.action_nav_eventos_to_nav_reportar_evento)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.allEvents.observe(viewLifecycleOwner, Observer { events ->
            events?.let { adapter.setEvents(it) }
        })
    }

    private fun showWardInfoPopupWindow(container: ViewGroup?, telephone: String){

        val phoneCallUri = Uri.parse("tel:" + telephone)

        val popupView = LayoutInflater.from(activity).inflate(R.layout.popup_window_guardia_info_layout, null)
        val popupWindow = PopupWindow(popupView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true)
        val phoneTv = popupView.txt_phone_value
        val closeBtn = popupView.btn_watch_data_close

        popupWindow.elevation = 5.0f

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val slideIn = Slide()
            slideIn.slideEdge = Gravity.TOP
            popupWindow.enterTransition = slideIn

            val slideOut = Slide()
            slideOut.slideEdge = Gravity.END
            popupWindow.exitTransition = slideOut
        }

        phoneTv.setOnClickListener{
            val phoneCallIntent = Intent(Intent.ACTION_DIAL).also{
                it.data = phoneCallUri
            }

            startActivity(phoneCallIntent)
        }

        closeBtn.setOnClickListener{
            popupWindow.dismiss()
        }

        popupWindow.setOnDismissListener {
            Toast.makeText(context, "Popup closed", Toast.LENGTH_SHORT).show()
        }


        TransitionManager.beginDelayedTransition(container)
        popupWindow.showAtLocation(container, Gravity.CENTER, 0, 0)
    }



}