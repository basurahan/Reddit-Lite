package link.limecode.reddit.lite.android

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.core.graphics.Insets
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.fragment.NavHostFragment
import link.limecode.reddit.lite.android.databinding.ActivityMainBinding
import link.limecode.reddit.lite.android.navigation.setupMainNavGraph
import link.limecode.reddit.lite.presentation.viewmodel.app.AndroidAppEventsViewModel
import link.limecode.reddit.lite.presentation.viewmodel.app.AndroidSessionViewModel
import link.limecode.reddit.lite.presentation.viewmodel.app.SessionUIState
import link.limecode.vuebinder.ActivityViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ActivityViewBinding<ActivityMainBinding>() {

    private val eventsViewModel: AndroidAppEventsViewModel by viewModel()
    private val sessionViewModel: AndroidSessionViewModel by viewModel()

    override fun bind(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        setupEdgeToEdge()
        super.onCreate(savedInstanceState)
        with(viewBinding) {
            root.viewTreeObserver.addOnPreDrawListener {
                return@addOnPreDrawListener sessionViewModel.sessionUIState.value !is SessionUIState.Initial
            }
        }
        setupKeyboardInsets()
        setupNavGraph()
    }

    private fun setupNavGraph() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        navController.setupMainNavGraph()
    }

    private fun setupKeyboardInsets() {
        with(viewBinding) {
            ViewCompat.setOnApplyWindowInsetsListener(layoutFrame) { v, insets ->
                val imeHeight = insets.getInsets(WindowInsetsCompat.Type.ime()).bottom
                v.setPadding(0, 0, 0, imeHeight)

                val childInsets = WindowInsetsCompat.Builder(insets)
                childInsets.setInsets(WindowInsetsCompat.Type.ime(), Insets.NONE)
                if (imeHeight > 0) {
                    childInsets.setInsets(WindowInsetsCompat.Type.navigationBars(), Insets.NONE)
                }

                childInsets.build()
            }
        }
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

    override fun onDestroy() {
        ViewCompat.setOnApplyWindowInsetsListener(viewBinding.layoutFrame, null)
        super.onDestroy()
    }
}