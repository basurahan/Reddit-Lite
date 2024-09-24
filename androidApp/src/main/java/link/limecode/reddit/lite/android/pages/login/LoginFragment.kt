package link.limecode.reddit.lite.android.pages.login

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ScrollView
import link.limecode.reddit.lite.android.databinding.FragmentLoginBinding
import link.limecode.reddit.lite.presentation.viewmodel.login.AndroidLoginViewModel
import link.limecode.vuebinder.FragmentViewBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : FragmentViewBinding<FragmentLoginBinding>() {

    private val viewModel: AndroidLoginViewModel by viewModel()

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentLoginBinding {
        val binding = FragmentLoginBinding.inflate(inflater, container, false)
        with(binding) {
            val displayMetrics = resources.displayMetrics
            val screenHeight = displayMetrics.heightPixels

            val typedValue = TypedValue()
            requireActivity().theme.resolveAttribute(android.R.attr.actionBarSize, typedValue, true)
            val actionBarSize = TypedValue.complexToDimensionPixelSize(typedValue.data, displayMetrics)
            val desiredHeight = screenHeight - actionBarSize

            val params = groupForm.layoutParams as FrameLayout.LayoutParams
            params.height = desiredHeight
            groupForm.layoutParams = params
            groupForm.requestLayout()
            groupForm.invalidate()
        }
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(viewBinding) {


            /*button.setOnClickListener {
                viewModel.login(
                    username = "dev.renz",
                    password = "S@langa1998"
                )
            }*/
        }
    }
}