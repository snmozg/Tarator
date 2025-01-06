import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class BottomSheetViewModel : ViewModel() {
    var isFilterSheetOpen = mutableStateOf(false)
        private set
    var isToolsSheetOpen = mutableStateOf(false)
        private set
    var isColorSheetOpen = mutableStateOf(false)
        private set
    var isWidthSheetOpen = mutableStateOf(false)
        private set
    var isCropSheetOpen = mutableStateOf(false)
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

    fun openColorSheet() {
        isColorSheetOpen.value = true
    }

    fun closeColorSheet() {
        isColorSheetOpen.value = false
    }

    fun openWidthSheet() {
        isWidthSheetOpen.value = true
    }

    fun closeWidthSheet() {
        isWidthSheetOpen.value = false
    }
    fun openCropSheet() {
        isCropSheetOpen.value = true
    }
    fun closeCropSheet() {
        isWidthSheetOpen.value = false
    }
}
