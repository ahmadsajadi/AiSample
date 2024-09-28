import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

class SpeechBubbleShape2(
    private val cornerRadius: Dp = 10.dp,
    private val tipSize: Dp = 2.dp,
    private val anchorPosition: AnchorPosition = AnchorPosition.Left
) : Shape {

    enum class AnchorPosition {Left, Right
    }

    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        val tipSizePx = with(density) { tipSize.toPx() }
        val cornerRadiusPx = with(density) { cornerRadius.toPx() }
        val path = Path().apply {
            when (anchorPosition) {
                AnchorPosition.Left -> {
                    // Existing path for left anchor (unchanged)
                    addRoundRect(
                        RoundRect(
                            left = tipSizePx,
                            top = 0f,
                            right = size.width,
                            bottom = size.height - tipSizePx,
                            radiusX = cornerRadiusPx,
                            radiusY = cornerRadiusPx
                        )
                    )
                    moveTo(tipSizePx, size.height - tipSizePx - cornerRadiusPx)
                    lineTo(0f, size.height)
                    lineTo(tipSizePx + cornerRadiusPx, size.height - tipSizePx)
                    close()
                }

                AnchorPosition.Right -> {
                    // Corrected path for right anchor
                    addRoundRect(
                        RoundRect(
                            left = 0f,
                            top = 0f,
                            right = size.width - tipSizePx,
                            bottom = size.height - tipSizePx,
                            radiusX = cornerRadiusPx,
                            radiusY = cornerRadiusPx
                        )
                    )
                    moveTo(size.width - tipSizePx, size.height - tipSizePx - cornerRadiusPx)
                    lineTo(size.width, size.height)
                    lineTo(size.width - tipSizePx - cornerRadiusPx, size.height - tipSizePx)
                    close()
                }
            }
        }

        return Outline.Generic(path)
    }
}