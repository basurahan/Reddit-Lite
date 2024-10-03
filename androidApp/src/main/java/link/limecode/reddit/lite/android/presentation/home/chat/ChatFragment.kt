package link.limecode.reddit.lite.android.presentation.home.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import link.limecode.reddit.lite.android.databinding.FragmentChatBinding
import link.limecode.vuebinder.FragmentViewBinding

class ChatFragment : FragmentViewBinding<FragmentChatBinding>() {

    override fun bind(inflater: LayoutInflater, container: ViewGroup?): FragmentChatBinding {
        return FragmentChatBinding.inflate(inflater, container, false)
    }
}