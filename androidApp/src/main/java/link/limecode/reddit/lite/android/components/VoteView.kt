package link.limecode.reddit.lite.android.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.google.android.material.card.MaterialCardView
import link.limecode.reddit.lite.android.databinding.ViewVoteBinding

class VoteView @JvmOverloads constructor(
    context: Context,
    attribSet: AttributeSet? = null,
    defStyleRef: Int = com.google.android.material.R.attr.materialCardViewOutlinedStyle
) : MaterialCardView(context, attribSet, defStyleRef) {

    init {
        ViewVoteBinding.inflate(LayoutInflater.from(context), this, true)
    }
}