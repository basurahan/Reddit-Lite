package link.limecode.reddit.lite.android.presentation.home.create

import android.view.LayoutInflater
import android.view.ViewGroup
import link.limecode.reddit.lite.android.databinding.FragmentCreateBinding
import link.limecode.vuebinder.FragmentViewBinding

class CreateFragment : FragmentViewBinding<FragmentCreateBinding>() {

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentCreateBinding {
        return FragmentCreateBinding.inflate(inflater, container, false)
    }
}