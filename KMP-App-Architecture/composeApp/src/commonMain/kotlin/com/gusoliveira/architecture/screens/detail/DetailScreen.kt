package com.gusoliveira.architecture.screens.detail

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.gusoliveira.architecture.screens.EmptyScreenContent
import domain.model.MuseumObject
import io.github.aakira.napier.Napier
import kmp_app_architecture.composeapp.generated.resources.Res
import kmp_app_architecture.composeapp.generated.resources.back
import kmp_app_architecture.composeapp.generated.resources.label_artist
import kmp_app_architecture.composeapp.generated.resources.label_credits
import kmp_app_architecture.composeapp.generated.resources.label_date
import kmp_app_architecture.composeapp.generated.resources.label_department
import kmp_app_architecture.composeapp.generated.resources.label_dimensions
import kmp_app_architecture.composeapp.generated.resources.label_medium
import kmp_app_architecture.composeapp.generated.resources.label_repository
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DetailScreen(
    objectId: Int,
    navigateBack: () -> Unit,
) {
    Napier.e("DetailScreen - Composable: $objectId")
    val viewModel = koinViewModel<DetailViewModel>()
    var obj by remember { mutableStateOf<MuseumObject?>(null) }

    LaunchedEffect(objectId) {
        Napier.e("DetailScreen - LaunchedEffect: $objectId")
        viewModel.getObject(objectId).collect { result ->
            Napier.e("DetailScreen - Object collected: ${result?.objectID}")
            obj = result
        }
    }

    AnimatedContent(obj != null) { objectAvailable ->
        if (objectAvailable) {
            Napier.e("DetailScreen - Object available: ${obj?.objectID}")
            ObjectDetails(obj!!, onBackClick = navigateBack)
        } else {
            Napier.e("DetailScreen - No object available")
            EmptyScreenContent(Modifier.fillMaxSize())
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ObjectDetails(
    obj: MuseumObject,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Napier.e("ObjectDetails - Composable: ${obj.objectID}")
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(obj.title) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(Res.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            AsyncImage(
                model = obj.primaryImage,
                contentDescription = obj.title,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
            )

            Column(Modifier.padding(16.dp)) {
                Text(obj.title, style = MaterialTheme.typography.headlineMedium)
                Spacer(Modifier.height(8.dp))
                Text(
                    stringResource(Res.string.label_artist, obj.artistDisplayName),
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    stringResource(Res.string.label_date, obj.objectDate),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    stringResource(Res.string.label_medium, obj.medium),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    stringResource(Res.string.label_dimensions, obj.dimensions),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    stringResource(Res.string.label_department, obj.department),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    stringResource(Res.string.label_repository, obj.repository),
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    stringResource(Res.string.label_credits, obj.creditLine),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
private fun LabeledText(
    label: String,
    text: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}
