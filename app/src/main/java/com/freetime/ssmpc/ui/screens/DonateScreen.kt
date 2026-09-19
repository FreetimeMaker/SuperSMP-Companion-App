package com.freetime.ssmpc.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.freetime.ssmpc.R
import com.freetime.ssmpc.ui.glass.SuperSMPGlassButton
import com.freetime.ssmpc.ui.glass.SuperSMPGlassTopBar
import com.freetime.ssmpc.ui.glass.superSMPGlass

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DonateScreen(onBack: () -> Unit) {
    val context = LocalContext.current

    Scaffold(
        containerColor = Color.Transparent,
        topBar = {
            SuperSMPGlassTopBar(
                title = stringResource(R.string.donate_title),
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_nav_desc)
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                DonationInfoCard(
                    title = stringResource(R.string.support_development),
                    text = stringResource(R.string.select_option_msg),
                    primary = true
                )
            }

            item {
                DonationInfoCard(
                    title = stringResource(R.string.about_developer_title),
                    text = stringResource(R.string.about_developer_text)
                )
            }

            item {
                DonationInfoCard(
                    title = stringResource(R.string.donation_mission_title),
                    text = stringResource(R.string.donation_mission_text)
                )
            }

            item {
                Text(
                    text = stringResource(R.string.cash_label),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }

            item {
                SuperSMPGlassButton(
                    onClick = {
                        openDonationLink(context, "https://github.com/sponsors/FreetimeMaker", context.getString(R.string.DonViaGHSponsors))
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.DonViaGHSponsors))
                }
            }

            item {
                Text(
                    text = stringResource(R.string.crypto_label),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp)
                )
            }

            item {
                SuperSMPGlassButton(
                    onClick = {
                        openDonationLink(context, "https://nowpayments.io/donation/SuperSMP", context.getString(R.string.nowpayments))
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.nowpayments))
                }
            }

            item {
                SuperSMPGlassButton(
                    onClick = {
                        openDonationLink(context, "https://pay.oxapay.com/13038067", context.getString(R.string.DonViaOxaPay))
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.DonViaOxaPay))
                }
            }

            item {
                val label = stringResource(R.string.DonViaBTC)
                SuperSMPGlassButton(
                    onClick = { openDonationLink(context, "https://ncwallet.net/pay/60misly", label) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(label)
                }
            }

            item {
                val label = stringResource(R.string.DonViaETH)
                SuperSMPGlassButton(
                    onClick = { openDonationLink(context, "https://ncwallet.net/pay/86fremd", label) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(label)
                }
            }

            item {
                val label = stringResource(R.string.DonViaUSDT)
                SuperSMPGlassButton(
                    onClick = { openDonationLink(context, "https://ncwallet.net/pay/19tacit", label) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(label)
                }
            }

            item {
                val label = stringResource(R.string.DonViaUSDC)
                SuperSMPGlassButton(
                    onClick = { openDonationLink(context, "https://ncwallet.net/pay/15snog", label) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(label)
                }
            }

            item {
                val label = stringResource(R.string.DonViaSHIB)
                SuperSMPGlassButton(
                    onClick = { openDonationLink(context, "https://ncwallet.net/pay/18spile", label) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(label)
                }
            }

            item {
                val label = stringResource(R.string.DonViaDOGE)
                SuperSMPGlassButton(
                    onClick = { openDonationLink(context, "https://ncwallet.net/pay/30allie", label) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(label)
                }
            }

            item {
                val label = stringResource(R.string.DonateViaTRON)
                SuperSMPGlassButton(
                    onClick = { openDonationLink(context, "https://ncwallet.net/pay/15gown", label) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(label)
                }
            }

            item {
                val label = stringResource(R.string.DonViaLTC)
                SuperSMPGlassButton(
                    onClick = { openDonationLink(context, "https://ncwallet.net/pay/77pudgy", label) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(label)
                }
            }

            item {
                val label = stringResource(R.string.DonViaBNB)
                SuperSMPGlassButton(
                    onClick = { openDonationLink(context, "https://ncwallet.net/pay/02hanch", label) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(label)
                }
            }

            item {
                val label = stringResource(R.string.DonViaPEPE)
                SuperSMPGlassButton(
                    onClick = { openDonationLink(context, "https://ncwallet.net/pay/73enow", label) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(label)
                }
            }

            item {
                val label = stringResource(R.string.DonViaSOL)
                SuperSMPGlassButton(
                    onClick = { openDonationLink(context, "https://ncwallet.net/pay/54fled", label) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(label)
                }
            }

            item {
                val label = stringResource(R.string.DonViaDAI)
                SuperSMPGlassButton(
                    onClick = { openDonationLink(context, "https://ncwallet.net/pay/27thio", label) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(label)
                }
            }

            item {
                val label = stringResource(R.string.DonViaTON)
                SuperSMPGlassButton(
                    onClick = { openDonationLink(context, "https://ncwallet.net/pay/22frisk", label) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(label)
                }
            }

            item {
                val label = stringResource(R.string.DonViaPOL)
                SuperSMPGlassButton(
                    onClick = { openDonationLink(context, "https://ncwallet.net/pay/23patas", label) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(label)
                }
            }

            item {
                val label = stringResource(R.string.DonViaOptimism)
                SuperSMPGlassButton(
                    onClick = { openDonationLink(context, "https://ncwallet.net/pay/77salvy", label) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(label)
                }
            }

            item {
                val label = stringResource(R.string.DonViaARB)
                SuperSMPGlassButton(
                    onClick = { openDonationLink(context, "https://ncwallet.net/pay/80arui", label) },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(label)
                }
            }

            item {
                SuperSMPGlassButton(
                    onClick = {
                        context.startActivity(Intent(context, WalletAddressActivity::class.java))
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(stringResource(R.string.show_wallet_addresses))
                }
            }
        }
    }
}

@Composable
private fun DonationInfoCard(
    title: String,
    text: String,
    primary: Boolean = false
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .superSMPGlass(RoundedCornerShape(24.dp), interactive = false),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = if (primary) MaterialTheme.typography.titleLarge else MaterialTheme.typography.titleMedium,
                color = if (primary) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(if (primary) 8.dp else 4.dp))
            Text(
                text = text,
                style = if (primary) MaterialTheme.typography.bodyMedium else MaterialTheme.typography.bodySmall,
                color = if (primary) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}


private fun openDonationLink(context: Context, url: String, title: String) {
    val preferences = context.getSharedPreferences("ssmpc_prefs", Context.MODE_PRIVATE)
    val openExternalBrowser = preferences.getBoolean("open_external_browser", false)

    if (openExternalBrowser) {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    } else {
        context.startActivity(
            Intent(context, DonateWebViewActivity::class.java).apply {
                putExtra("url", url)
                putExtra("title", title)
            }
        )
    }
}
