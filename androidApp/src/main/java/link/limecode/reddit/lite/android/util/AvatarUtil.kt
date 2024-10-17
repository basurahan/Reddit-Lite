package link.limecode.reddit.lite.android.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import androidx.core.content.res.ResourcesCompat
import link.limecode.reddit.lite.android.R

/**
 * Generates a bitmap avatar using the initials of a user.
 *
 * @param initials the letters to use in the avatar
 * @param size the size of the bitmap in dp
 * @return the generated [Bitmap] avatar
 */
fun Context.generateAvatar(
    initials: String,
    size: Int
): Bitmap {
    val px = dpToPx(size)
    val bitmap = Bitmap.createBitmap(px, px, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(bitmap)

    val paint = Paint().apply {
        color = colorOnTertiary()
        textSize = spToPx(100F)
        textAlign = Paint.Align.CENTER
        typeface = ResourcesCompat.getFont(this@generateAvatar, R.font.poppins_medium)
    }

    val xPos = px / 2
    val yPos = (px / 2 - (paint.descent() + paint.ascent()) / 2)
    canvas.drawColor(colorTertiary())
    canvas.drawText(initials, xPos.toFloat(), yPos, paint)

    return bitmap
}