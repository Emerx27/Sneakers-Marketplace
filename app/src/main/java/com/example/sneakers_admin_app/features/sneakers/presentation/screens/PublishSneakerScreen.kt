package com.example.sneakers_admin_app.features.sneakers.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sneakers_admin_app.features.sneakers.models.SneakerCondition
import com.example.sneakers_admin_app.features.sneakers.presentation.viewmodel.PublishSneakerViewModel
import com.example.sneakers_admin_app.shared.components.BackButton
import com.example.sneakers_admin_app.shared.components.ErrorModalScreen
import com.example.sneakers_admin_app.shared.components.PrimaryTextField
import com.example.sneakers_admin_app.shared.components.SelectorField
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

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 18.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    BackButton { navController.popBackStack() }

                    Text(
                        text = "Publish sneaker",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    modifier = Modifier.clickable(
                        enabled = !viewModel.isLoading
                    ) {
                        viewModel.publishSneaker()
                    },
                    text = "Publish",
                    color = MaterialTheme.colorScheme.primary
                )
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

            viewModel.generalError?.let {
                ErrorModalScreen(message = it, actionText = "Dismiss", onDismiss = {
                    viewModel.clearErrorMessage()
                }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          )
            }
        }
    }
}