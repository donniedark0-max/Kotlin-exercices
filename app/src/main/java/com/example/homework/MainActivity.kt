package com.example.homework

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity(),ProductosBottomSheetDialogFragment.ProductoSeleccionadoListener {
    private lateinit var tvProducto: TextView
    private lateinit var tvPrecioTotal: TextView
    private lateinit var tvTotalFinal: TextView
    private lateinit var btnCalcular: Button
    private lateinit var tilCantidad: TextInputLayout
    private lateinit var cbDelivery: CheckBox
    private lateinit var cbDescuento: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnCalcular = findViewById(R.id.btnCalcular)
        tvProducto = findViewById(R.id.tvProducto)
        tvPrecioTotal = findViewById(R.id.tvPrecioTotal)
        tvTotalFinal = findViewById(R.id.tvTotalFinal)
        tilCantidad = findViewById(R.id.tilCantidad)
        cbDelivery = findViewById(R.id.cbDelivery)
        cbDescuento = findViewById(R.id.cbDescuento)
        tvPrecioTotal.text = getString(R.string.precio_total_base) // Nuevorecurso de cadena
        tvTotalFinal.text = getString(R.string.total_final_base)
        val btnSeleccionarProducto = findViewById<Button>(R.id.btnSeleccionarProducto)
        btnSeleccionarProducto.setOnClickListener {
            mostrarOpcionesProductos()
        }

        btnCalcular.setOnClickListener {
            calcularPrecio()
        }
    }

    private fun mostrarOpcionesProductos() {
        val bottomSheet = ProductosBottomSheetDialogFragment()
        bottomSheet.show(supportFragmentManager, "productosBottomSheet")
    }

    override fun onProductoSeleccionado(producto: String) {
        tvProducto.text = producto
    }


    private fun calcularPrecio() {
        val producto = tvProducto.text.toString()
        val cantidad = tilCantidad.editText?.text.toString().toDoubleOrNull() ?: 0.0
        val delivery = cbDelivery.isChecked
        val descuento = cbDescuento.isChecked
        val precio = when (producto) {
            "1 Pollo a la brasa" -> 60.00
            "1 Monstruo" -> 15.00
            "1/2 Pollo" -> 30.00
            else -> 0.00
        }

        val precioTotal = precio * cantidad
        val costoDelivery = if (delivery) 5 else 0
        val descuentoAplicado = if (descuento) 5 else 0

        val totalFinal = precioTotal + costoDelivery - descuentoAplicado

        tvPrecioTotal.text = getString(R.string.precio_total, precioTotal)
        tvTotalFinal.text = getString(R.string.total_final, totalFinal)
    }
}
