package com.sozge.taratornew.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.sozge.taratornew.components.HeaderBar
import com.sozge.taratornew.models.DrawingViewModel
import com.sozge.taratornew.models.FilterViewModel
import com.sozge.taratornew.models.ImageViewModel

@Composable
fun TermsOfUsePage(
    navController: NavController,
    imageViewModel: ImageViewModel,
    drawingViewModel: DrawingViewModel,
    filterViewModel: FilterViewModel
) {
    val termsOfUseText = """
        Terms of Use
Welcome to the Tarator app! By using the app, you agree to the following terms and conditions. Please read these terms carefully.

1. General
The Tarator app is a mobile application developed to provide photo editing, text addition, filters, and other media features. By using this app, you can access the services provided by Tarator and use them for personal purposes.

2. Usage Rights
Tarator grants users the right to use the tools, functionalities, and content within the app, but this right is non-transferable and is for personal use only. The app may not be used for commercial purposes.

3. User Responsibilities
The content and tools within the app should be used only for lawful and ethical purposes.
Tarator is not responsible for user-generated content shared within the app. The user confirms that they own all copyrights to the content they share and that the content does not harm others.
The user must refrain from using the app in a way that infringes upon the rights of others.
4. Content and Copyright
All content, graphics, software, and other materials in the Tarator app are owned by Tarator and are protected by copyright. Unauthorized use of the content within the app is prohibited.

5. Data Privacy
Personal data of users is protected under the app's privacy policy. By using the app, you consent to the privacy policy.

6. App Changes
Tarator reserves the right to modify the app's content, functionalities, and terms of use at any time. Changes will be communicated through the app, and continued use indicates acceptance of the updated terms.

7. Disclaimer
Tarator does not guarantee that the app will function without interruption or errors. Users are fully responsible for any damage or loss resulting from the use of the app. Tarator is not liable for any data loss or security breaches.

8. Account Management
Users are responsible for the security of their accounts. All activities performed with account information are the responsibility of the user. It is the user's responsibility to take the necessary steps to secure their account.

9. App Cancellation and Account Closure
Users can stop using the Tarator app at any time and close their accounts. Once an account is closed, all data associated with the user will be deleted.

10. Contact
For any questions, suggestions, or feedback regarding Tarator, please contact us.
    """.trimIndent()
    Scaffold(
        topBar = {
            HeaderBar(navController,
                actionImageVector = Icons.Outlined.Menu,
                actionContentDescription = "save button",
                isBackButtonEnable = true,
                imageViewModel = imageViewModel,
                drawingViewModel = drawingViewModel,
                filterViewModel = filterViewModel,
                onClick = {
                    navController.navigate("SettingsPage")
                }
            )
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
        ) {
            Text(
                text = termsOfUseText
            )
        }

    }
}