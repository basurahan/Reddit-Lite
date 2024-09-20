package link.limecode.reddit.lite.android.pages.home.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import link.limecode.reddit.lite.android.databinding.FragmentNotificationBinding
import link.limecode.vuebinder.FragmentViewBinding

class NotificationFragment : FragmentViewBinding<FragmentNotificationBinding>() {

    override fun bind(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentNotificationBinding {
        return FragmentNotificationBinding.inflate(inflater, container, false)
    }
}