package com.example.sneakers_admin_app.features.sneakers.presentation.screens

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddToPhotos
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.sneakers_admin_app.features.sneakers.models.SneakerCondition
import com.example.sneakers_admin_app.features.sneakers.presentation.viewmodel.PublishSneakerViewModel
import com.example.sneakers_admin_app.shared.components.ErrorModalScreen
import com.example.sneakers_admin_app.shared.components.PrimaryTextField
import com.example.sneakers_admin_app.shared.components.SelectorField
import com.example.sneakers_admin_app.shared.components.layout.ScreenHeader
import com.example.sneakers_admin_app.ui.theme.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PublishSneakerScreen(
    navController: NavController,
    viewModel: PublishSneakerViewModel = viewModel()
) {
    LaunchedEffect(viewModel.isSuccess) {
        if(viewModel.isSuccess) {
            navController.popBackStack()
        }
    }

    var showConditionSheet by remember {
        mutableStateOf(false)
    }

    val selectedConditionLabel =
        SneakerCondition.entries
            .find { it.value == viewModel.condition }
            ?.label
            ?: ""

    val launcher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetMultipleContents()
        ) { uris ->

            if (uris.isNotEmpty()) {
                viewModel.addImages(uris)
            }
        }

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            ScreenHeader(
                title = "Publish sneaker",
                onBackClick = {navController.popBackStack()},
                actions = {
                    Text(
                        modifier = Modifier.clickable(
                            enabled = !viewModel.isLoading
                        ) {
                            viewModel.publishSneaker(context)
                        },
                        text = "Publish",
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            )

            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                if(viewModel.selectedImages.isEmpty()) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Surface(
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        ) {
                            IconButton(
                                onClick = {
                                    launcher.launch("image/*")
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AddToPhotos,
                                    contentDescription = null
                                )
                            }
                        }

                        Text("Add photos", fontSize = 12.sp)
                    }

                    viewModel.selectedImagesError?.let { Text(it, color = MaterialTheme.colorScheme.error, fontSize = 12.sp) }
                } else {
                    LazyRow(
                        modifier = Modifier.height(100.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        items(viewModel.selectedImages) {
                                uri ->

                            Box {
                                AsyncImage(
                                    model = uri,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(100.dp)
                                        .clip(RoundedCornerShape(6.dp)),
                                    contentScale = ContentScale.Crop
                                )

                                Surface(
                                    modifier = Modifier
                                        .align(Alignment.TopEnd)
                                        .size(30.dp)
                                        .padding(4.dp),
                                    shape = CircleShape,
                                    color = Color.Black.copy(alpha = 0.6f)
                                ) {
                                    IconButton(
                                        onClick = {
                                            viewModel.removeImage(uri)
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Close,
                                            contentDescription = null
                                        )
                                    }
                                }
                            }
                        }

                        item {
                            Column(
                                modifier = Modifier
                                    .padding(start = 8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                            ) {
                                Surface(
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = CircleShape
                                ) {
                                    IconButton(
                                        onClick = {
                                            launcher.launch("image/*")
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.AddToPhotos,
                                            contentDescription = null
                                        )
                                    }
                                }

                                Text("Add photos", fontSize = 12.sp)
                            }
                        }
                    }
                }
                PrimaryTextField(
                    value = viewModel.brand,
                    onValueChange = viewModel::onBrandChange,
                    placeholder = "Brand",
                    isError = viewModel.brandError != null,
                    errorText = viewModel.brandError
                )

                PrimaryTextField(
                    value = viewModel.model,
                    onValueChange = viewModel::onModelChange,
                    placeholder = "Model",
                    isError = viewModel.modelError != null,
                    errorText = viewModel.modelError
                )

                PrimaryTextField(
                    value = viewModel.sku,
                    onValueChange = viewModel::onSkuChange,
                    placeholder = "SKU",
                    isError = viewModel.skuError != null,
                    errorText = viewModel.skuError
                )

                PrimaryTextField(
                    value = viewModel.price,
                    onValueChange = viewModel::onPriceChange,
                    placeholder = "Price",
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    isError = viewModel.priceError != null,
                    errorText = viewModel.priceError
                )

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            showConditionSheet = true
                        }
                ) {
                    SelectorField(
                        value = selectedConditionLabel,
                        placeholder = "Condition",
                        onClick = {
                            showConditionSheet = true
                        },
                        isError = viewModel.conditionError != null,
                        errorText = viewModel.conditionError
                    )
                }

                if (showConditionSheet) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            showConditionSheet = false
                        }
                    ) {
                        Column {
                            SneakerCondition.entries.forEach{ option ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            viewModel.onConditionChange(option.value)
                                            showConditionSheet = false
                                        }
                                        .padding(
                                            horizontal = 18.dp,
                                            vertical = 10.dp
                                        ),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {

                                    Text(option.label)

                                    RadioButton(
                                        selected = viewModel.condition == option.value,
                                        onClick = null
                                    )
                                }
                            }
                        }
                    }
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    PrimaryTextField(
                        value = viewModel.description,
                        onValueChange = viewModel::onDescriptionChange,
                        placeholder = "Description",
                        maxLines = 6,
                        isError = viewModel.descriptionError != null,
                        errorText = viewModel.descriptionError
                    )

                    Text(
                        text = "Optional",
                        color = AppColors.TextMuted,
                        fontSize = 12.sp
                    )
                }
            }

            viewModel.generalError?.let {
                ErrorModalScreen(message = it, actionText = "Dismiss", onDismiss = {
                    viewModel.clearErrorMessage()
                }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          )
            }
        }
    }
}