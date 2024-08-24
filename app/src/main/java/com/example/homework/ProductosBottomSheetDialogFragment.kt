package com.example.homework

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ProductosBottomSheetDialogFragment : BottomSheetDialogFragment() {
    private lateinit var listener: ProductoSeleccionadoListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as ProductoSeleccionadoListener
        } catch (e: IllegalStateException) {
            throw ClassCastException("$context debe implementar ProductoSeleccionadoListener")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_productos, container, false)
        val opciones = listOf("1 Pollo a la brasa", "1 Monstruo", "1/2 Pollo")
        val listaOpciones = view.findViewById<ListView>(R.id.listaOpciones)
        listaOpciones.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, opciones)
        listaOpciones.setOnItemClickListener { _, _, position, _ ->
            listener.onProductoSeleccionado(opciones[position])
            dismiss()
        }
        return view
    }    interface ProductoSeleccionadoListener {
        fun onProductoSeleccionado(producto: String)
    }

}