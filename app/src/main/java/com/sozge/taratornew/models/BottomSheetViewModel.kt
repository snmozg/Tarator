import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BottomSheetViewModel : ViewModel() {
    var isFilterSheetOpen = mutableStateOf(false)
        private set
    var isToolsSheetOpen = mutableStateOf(false)
        private set
    var isBrushSheetOpen = mutableStateOf(false)
        private set

    fun openFilterSheet() {
        isFilterSheetOpen.value = true
    }

    fun closeFilterSheet() {
        isFilterSheetOpen.value = false
    }

    fun openToolsSheet() {
        isToolsSheetOpen.value = true
    }

    fun closeToolsSheet() {
        isToolsSheetOpen.value = false
    }

    fun openBrushSheet() {
        isBrushSheetOpen.value = true
    }

    fun closeBrushSheet() {
        isBrushSheetOpen.value = false
    }
}
