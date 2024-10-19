package link.limecode.reddit.lite.android.components

import android.content.Context
import android.graphics.Bitmap
import android.util.AttributeSet
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.google.android.material.shape.ShapeAppearanceModel
import link.limecode.reddit.lite.android.databinding.ViewAvatarBinding
import link.limecode.reddit.lite.android.util.shapeOverlayAvatar

class Avatar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = com.google.android.material.R.attr.materialCardViewOutlinedStyle
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val viewBinding = ViewAvatarBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        if (!isInEditMode) {
            shapeAppearanceModel = ShapeAppearanceModel.builder(
                context,
                0,
                context.shapeOverlayAvatar()
            ).build()
        }
    }

    fun setAvatar(bitmap: Bitmap) {
        with(viewBinding) {
            Glide.with(context)
                .load(bitmap)
                .into(avatar)
        }
    }
}