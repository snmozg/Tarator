package com.sozge.taratornew.utils.com.sozge.taratornew.pages

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.sozge.taratornew.components.HeaderBar
import com.sozge.taratornew.models.DrawingViewModel
import com.sozge.taratornew.models.FilterViewModel
import com.sozge.taratornew.models.ImageViewModel

@Composable
fun PrivacyPolicyPage(
    navController: NavController,
    imageViewModel: ImageViewModel,
    drawingViewModel: DrawingViewModel,
    filterViewModel: FilterViewModel) {
    val privacyPolicyText = """
        Last Updated: 23.12.2024
        Effective Date: unknown

        At Tarator, we take your privacy seriously. This Privacy Policy explains how the Tarator app collects, uses, shares, and protects your personal data. Please read this policy carefully.

        1. DEFINITIONS
        App: The Tarator mobile application.
        User: A person who downloads, installs, and uses the app.
        Personal Data: Any information related to an identified or identifiable person.

        2. COLLECTED DATA AND METHODS OF COLLECTION
        2.1. Automatically Collected Data
        Device information (model, operating system, unique device identifiers)
        Usage statistics
        IP address

        2.2. User-Provided Data
        Photos and media content
        Language and theme preferences
        Feedback and comments

        2.3. Data Collected from Third Parties
        Data obtained through analytics service providers

        3. PURPOSES OF DATA USE
        Collected data may be used for the following purposes:
        - Providing and improving app services
        - Enhancing the user experience
        - Resolving technical issues
        - Evaluating feedback
        - Fulfilling legal obligations

        4. DATA STORAGE AND SECURITY
        Data is stored on secure servers and protected against unauthorized access.
        Technical measures, such as encryption and firewalls, are employed.
        Data is retained only as long as necessary and deleted after the legal retention period expires.

        5. DATA SHARING
        Your personal data will not be shared with third parties except in the following cases:
        - With your explicit consent
        - To fulfill legal obligations
        - Limited sharing with service providers (e.g., hosting, analytics)

        6. COOKIES AND SIMILAR TECHNOLOGIES
        The Tarator app may use cookies and similar technologies to enhance the user experience. You can manage your cookie preferences through your device settings.

        7. USER RIGHTS
        Users have the following rights:
        - Request access to their personal data
        - Request correction of incorrect or incomplete data
        - Request deletion of data
        - Object to data processing
        To exercise these rights, you can contact us at:
        📧 [tarator@gmail.com]

        8. THIRD-PARTY LINKS
        Tarator may contain links to third-party websites or services. These sites have their own privacy policies, which are not under our control.

        9. UPDATES AND CHANGES
        This Privacy Policy may be updated from time to time. Updates will be announced within the app or on our website.

        10. CONTACT
        For questions about the Privacy Policy, you can contact us at:
        📧 [tarator@gmail.com]
        

        The Tarator Team is committed to protecting your privacy.
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
            Card(
                shape = RoundedCornerShape(5.dp),
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.background)
            ) {
                Column(

                    modifier = Modifier
                        .border(5.dp,MaterialTheme.colorScheme.primary)
                        .padding(6.dp)
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Text(
                        text = "Tarator Privacy Policy",
                        style =MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.padding(20.dp)
                    )
                    Text(
                        modifier = Modifier.padding(12.dp),
                        text = privacyPolicyText,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                }
            }
            Spacer(modifier = Modifier.padding(10.dp))

            Text(
                text = "Tarator ⓒ 2025",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
        }
    }
}
