package com.freetime.ssmpc.ui.screens

import android.content.Intent
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CardGiftcard
import androidx.compose.material.icons.filled.CurrencyBitcoin
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Public
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.freetime.sdk.PaymentRequest
import com.freetime.sdk.PaymentResult
import com.freetime.sdk.PromotionView
import com.freetime.ssmpc.R
import com.freetime.ssmpc.SuperSMPApplication

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonateScreen(onBack: () -> Unit) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.donate_title)) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = stringResource(R.string.back_nav_desc))
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header Section
            Text(
                text = stringResource(R.string.support_development),
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            
            Text(
                text = stringResource(R.string.donation_mission_text),
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(horizontal = 8.dp)
            )

            Spacer(Modifier.height(8.dp))

            // Main Support Option: GitHub Sponsors
            SupportCard(
                title = "GitHub Sponsors",
                description = "Support the project monthly or one-time via GitHub.",
                icon = Icons.Default.Favorite,
                containerColor = MaterialTheme.colorScheme.primaryContainer,
                onClick = {
                    val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse("https://github.com/sponsors/FreetimeMaker"))
                    context.startActivity(intent)
                }
            )

            // Modern Crypto Option via FreetimeSDK
            SupportCard(
                title = "Crypto Support",
                description = "Donate Bitcoin, Ethereum, Solana and 30+ other coins securely.",
                icon = Icons.Default.CurrencyBitcoin,
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                onClick = {
                    val activity = context as? ComponentActivity
                    activity?.let {
                        val request = PaymentRequest(
                            amount = 5.0,
                            currency = "USD",
                            description = "SuperSMP Support"
                        )
                        SuperSMPApplication.freetimePay.showPaymentSheet(it, request) { result ->
                            when (result) {
                                is PaymentResult.Success -> {
                                    Toast.makeText(context, "Thank you so much for your support!", Toast.LENGTH_LONG).show()
                                }
                                is PaymentResult.Error -> {
                                    Toast.makeText(context, "Payment Error: ${result.message}", Toast.LENGTH_LONG).show()
                                }
                                is PaymentResult.Cancelled -> {}
                            }
                        }
                    }
                }
            )

            // Other Web Links Section
            Text(
                text = "Other Options",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start).padding(top = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                SmallSupportCard(
                    modifier = Modifier.weight(1f),
                    title = "NOWPayments",
                    icon = Icons.Default.Public,
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse("https://nowpayments.io/donation/SuperSMP"))
                        context.startActivity(intent)
                    }
                )
                SmallSupportCard(
                    modifier = Modifier.weight(1f),
                    title = "OxaPay",
                    icon = Icons.Default.Public,
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, android.net.Uri.parse("https://pay.oxapay.com/13038067"))
                        context.startActivity(intent)
                    }
                )
            }

            // Manual Addresses
            OutlinedButton(
                onClick = { context.startActivity(Intent(context, WalletAddressActivity::class.java)) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(Icons.Default.CardGiftcard, contentDescription = null)
                Spacer(Modifier.width(8.dp))
                Text(stringResource(R.string.show_wallet_addresses))
            }

            HorizontalDivider(Modifier.padding(vertical = 16.dp))

            // Promotions / Featured Projects (GeoWeather Style)
            Text(
                text = "Featured Projects",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.Start)
            )
            
            FreetimePromotion()

            Spacer(Modifier.height(16.dp))

            FilledTonalButton(
                onClick = { context.startActivity(Intent(context, DonatorActivity::class.java)) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.ViewSup))
            }
        }
    }
}

@Composable
fun SupportCard(
    title: String,
    description: String,
    icon: ImageVector,
    containerColor: androidx.compose.ui.graphics.Color,
    onClick: () -> Unit
) {
    ElevatedCard(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = containerColor)
    ) {
        Row(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Spacer(Modifier.width(20.dp))
            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun SmallSupportCard(
    modifier: Modifier = Modifier,
    title: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    OutlinedCard(
        onClick = onClick,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(icon, contentDescription = null)
            Spacer(Modifier.height(8.dp))
            Text(text = title, style = MaterialTheme.typography.labelLarge)
        }
    }
}

@Composable
fun FreetimePromotion() {
    AndroidView(
        factory = { context ->
            PromotionView(context).apply {
                loadPromotion(SuperSMPApplication.freetimePay.config)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
fun DonateButton(text: String, onClick: () -> Unit) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text)
    }
}
