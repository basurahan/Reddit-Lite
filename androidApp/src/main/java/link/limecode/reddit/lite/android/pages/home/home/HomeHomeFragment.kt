package link.limecode.reddit.lite.android.pages.home.home

import android.view.LayoutInflater
import android.view.ViewGroup
import link.limecode.reddit.lite.android.databinding.FragmentHomeHomeBinding
import link.limecode.vuebinder.FragmentViewBinding

class HomeHomeFragment : FragmentViewBinding<FragmentHomeHomeBinding>() {

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentHomeHomeBinding {
        return FragmentHomeHomeBinding.inflate(inflater, container, false)
    }
}