package link.limecode.reddit.lite.android

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.navigation.fragment.NavHostFragment
import link.limecode.reddit.lite.android.databinding.ActivityMainBinding
import link.limecode.reddit.lite.android.navigation.setupMainNavGraph
import link.limecode.reddit.lite.presentation.viewmodel.app.AndroidAppViewModel
import link.limecode.vuebinder.ActivityViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ActivityViewBinding<ActivityMainBinding>() {

    private val viewModel: AndroidAppViewModel by viewModel()

    override fun bind(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setupEdgeToEdge()
        super.onCreate(savedInstanceState)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.setupMainNavGraph()
    }

    private fun setupEdgeToEdge() {
        val currentNightMode = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
        val transparent = getColor(android.R.color.transparent)
        val systemBarStyle = when (currentNightMode) {
            Configuration.UI_MODE_NIGHT_NO -> SystemBarStyle.light(transparent, transparent)
            Configuration.UI_MODE_NIGHT_YES -> SystemBarStyle.dark(transparent)
            else -> error("Illegal State, current mode is $currentNightMode")
        }
        enableEdgeToEdge(
            statusBarStyle = systemBarStyle,
            navigationBarStyle = systemBarStyle,
        )
    }
}