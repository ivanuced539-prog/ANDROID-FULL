package com.example.apppantaillas.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.apppantaillas.R
import com.google.android.material.card.MaterialCardView
import com.google.android.material.navigation.NavigationView

class Dashboard : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 1. Referencias de componentes visuales
        val drawerLayout = view.findViewById<DrawerLayout>(R.id.drawerLayout)
        val ivMenu = view.findViewById<ImageView>(R.id.ivMenu)
        val navigationView = view.findViewById<NavigationView>(R.id.navigationView)

        val cardAverias = view.findViewById<MaterialCardView>(R.id.cardAverias)
        val cardClientes = view.findViewById<MaterialCardView>(R.id.cardClientes)

        // 2. Abrir el menú lateral al hacer clic en el ícono de 3 líneas
        ivMenu?.setOnClickListener {
            drawerLayout?.openDrawer(GravityCompat.END)
        }

        // 3. Navegación desde las Tarjetas (Cards)
        cardAverias?.setOnClickListener {
            exemplarFragment(Averias())
        }

        cardClientes?.setOnClickListener {
            exemplarFragment(Clientes())
        }

        // 4. Navegación desde los items del Menú Lateral
        navigationView?.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_averias -> exemplarFragment(Averias())
                R.id.nav_clientes -> exemplarFragment(Clientes())
                // Agrega aquí más casos según tus items de drawer_menu.xml
            }
            drawerLayout?.closeDrawer(GravityCompat.END)
            true
        }
    }

    // Función auxiliar para realizar el reemplazo de Fragment con la pila de retroceso
    private fun exemplarFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right,
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}