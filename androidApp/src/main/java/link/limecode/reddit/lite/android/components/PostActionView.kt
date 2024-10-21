package link.limecode.reddit.lite.android.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.core.view.isVisible
import com.google.android.material.card.MaterialCardView
import link.limecode.reddit.lite.android.R
import link.limecode.reddit.lite.android.databinding.ViewPostActionBinding

class PostActionView @JvmOverloads constructor(
    context: Context,
    attribSet: AttributeSet? = null,
    defStyleRef: Int = com.google.android.material.R.attr.materialCardViewOutlinedStyle
) : MaterialCardView(context, attribSet, defStyleRef) {

    private val binding = ViewPostActionBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        context.obtainStyledAttributes(
            attribSet,
            R.styleable.PostActionView
        ).apply {
            val actionIcon = getDrawable(R.styleable.PostActionView_actionIcon)
            val isActionIconOnly = getBoolean(R.styleable.PostActionView_actionIsIconOnly, false)
            setupView(actionIcon = actionIcon, isActionIconOnly = isActionIconOnly)
            recycle()
        }
    }

    private fun setupView(actionIcon: Drawable?, isActionIconOnly: Boolean) {
        with(binding) {
            actionIcon?.let { icon.setImageDrawable(it) }
            description.isVisible = !isActionIconOnly
        }
    }
}