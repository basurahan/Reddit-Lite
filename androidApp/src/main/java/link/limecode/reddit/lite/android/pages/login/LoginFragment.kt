package link.limecode.reddit.lite.android.pages.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import link.limecode.reddit.lite.android.databinding.FragmentLoginBinding
import link.limecode.reddit.lite.presentation.viewmodel.login.AndroidLoginViewModel
import link.limecode.vuebinder.FragmentViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : FragmentViewBinding<FragmentLoginBinding>() {

    private val viewModel: AndroidLoginViewModel by viewModel()

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {
            /*val displayMetrics = resources.displayMetrics
            val screenHeight = displayMetrics.heightPixels
            val desiredHeight = (screenHeight * 0.5).toInt()

            groupForm.setPadding(
                groupForm.paddingLeft,
                desiredHeight,
                groupForm.paddingRight,
                groupForm.paddingBottom
            )*/

            ViewCompat.setOnApplyWindowInsetsListener(appbarLayout) { view, insets ->
                val systemWindowInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                view.setPadding(
                    view.paddingLeft,
                    systemWindowInsets.top,
                    view.paddingRight,
                    view.paddingBottom
                )
                insets
            }
        }
    }
}