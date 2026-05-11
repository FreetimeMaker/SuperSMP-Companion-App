package com.freetime.ssmpc.ui.screens

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.freetime.ssmpc.R

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
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = stringResource(R.string.support_development),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.select_option_msg),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Text(
                text = stringResource(R.string.cash_label),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start).padding(top = 8.dp)
            )

            DonateButton(text = stringResource(R.string.DonViaGHSponsors)) {
                openDonateUrl(context, "https://github.com/sponsors/FreetimeMaker", context.getString(R.string.DonViaGHSponsors))
            }

            Text(
                text = stringResource(R.string.crypto_label),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.align(Alignment.Start).padding(top = 8.dp)
            )

            DonateButton(text = stringResource(R.string.DonViaOxaPay)) {
                openDonateUrl(context, "https://pay.oxapay.com/13038067", context.getString(R.string.DonViaOxaPay))
            }

            DonateButton(text = stringResource(R.string.DonViaBTC)) {
                openDonateUrl(context, "https://ncwallet.net/pay/60misly", context.getString(R.string.DonViaBTC))
            }

            DonateButton(text = stringResource(R.string.DonViaETH)) {
                openDonateUrl(context, "https://ncwallet.net/pay/86fremd", context.getString(R.string.DonViaETH))
            }

            DonateButton(text = stringResource(R.string.DonViaUSDT)) {
                openDonateUrl(context, "https://ncwallet.net/pay/19tacit", context.getString(R.string.DonViaUSDT))
            }

            DonateButton(text = stringResource(R.string.DonViaUSDC)) {
                openDonateUrl(context, "https://ncwallet.net/pay/15snog", context.getString(R.string.DonViaUSDC))
            }

            DonateButton(text = stringResource(R.string.DonViaSHIB)) {
                openDonateUrl(context, "https://ncwallet.net/pay/18spile", context.getString(R.string.DonViaSHIB))
            }

            DonateButton(text = stringResource(R.string.DonViaDOGE)) {
                openDonateUrl(context, "https://ncwallet.net/pay/30allie", context.getString(R.string.DonViaDOGE))
            }

            DonateButton(text = stringResource(R.string.DonateViaTRON)) {
                openDonateUrl(context, "https://ncwallet.net/pay/15gown", context.getString(R.string.DonateViaTRON))
            }

            DonateButton(text = stringResource(R.string.DonViaLTC)) {
                openDonateUrl(context, "https://ncwallet.net/pay/77pudgy", context.getString(R.string.DonViaLTC))
            }

            DonateButton(text = stringResource(R.string.DonViaBNB)) {
                openDonateUrl(context, "https://ncwallet.net/pay/02hanch", context.getString(R.string.DonViaBNB))
            }

            DonateButton(text = stringResource(R.string.DonViaPEPE)) {
                openDonateUrl(context, "https://ncwallet.net/pay/73enow", context.getString(R.string.DonViaPEPE))
            }

            DonateButton(text = stringResource(R.string.DonViaSOL)) {
                openDonateUrl(context, "https://ncwallet.net/pay/54fled", context.getString(R.string.DonViaSOL))
            }

            DonateButton(text = stringResource(R.string.DonViaDAI)) {
                openDonateUrl(context, "https://ncwallet.net/pay/27thio", context.getString(R.string.DonViaDAI))
            }

            DonateButton(text = stringResource(R.string.DonViaTON)) {
                openDonateUrl(context, "https://ncwallet.net/pay/22frisk", context.getString(R.string.DonViaTON))
            }

            DonateButton(text = stringResource(R.string.DonViaPOL)) {
                openDonateUrl(context, "https://ncwallet.net/pay/23patas", context.getString(R.string.DonViaPOL))
            }

            DonateButton(text = stringResource(R.string.DonViaOptimism)) {
                openDonateUrl(context, "https://ncwallet.net/pay/77salvy", context.getString(R.string.DonViaOptimism))
            }

            DonateButton(text = stringResource(R.string.DonViaARB)) {
                openDonateUrl(context, "https://ncwallet.net/pay/80arui", context.getString(R.string.DonViaARB))
            }

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

private fun openDonateUrl(context: Context, url: String, title: String) {
    val intent = Intent(context, DonateWebViewActivity::class.java).apply {
        putExtra("url", url)
        putExtra("title", title)
    }
    context.startActivity(intent)
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
