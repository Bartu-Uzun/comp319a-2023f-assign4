package com.example.phonebook.presentation.add_contact

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.phonebook.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactScreen(
    responseEvents: Flow<AddContactViewModel.ResponseEvent>,
    onNavigateBack: () -> Unit,
    onSubmitButtonClick: () -> Unit,
    onContactToAddNameChange: (String) -> Unit,
    onContactToAddSurnameChange: (String) -> Unit,
    onContactToAddPhoneChange: (String) -> Unit,
    contactToAddName: String,
    contactToAddSurname: String,
    contactToAddPhone: String,
    errorMessage: String,
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = context) { // LaunchedEffect(key = context) --> when context changes, this affect will be relaunched
        responseEvents.collect {event: AddContactViewModel.ResponseEvent ->

            when (event) {
                AddContactViewModel.ResponseEvent.NavigateBack -> {
                    onNavigateBack()
                }
            }

        }
    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = {
                    Column (
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        Text(
                            text = stringResource(id = R.string.add_contact),
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                },
                navigationIcon = {
                    IconButton(
                        onClick = onNavigateBack,
                        modifier = Modifier
                            .size(70.dp)
                            .padding(horizontal = 6.dp)
                    ) {
                        Icon(
                            imageVector= Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.go_back),
                            modifier = Modifier
                                .size(48.dp)
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            )
        },
    ) {

        Surface (
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = MaterialTheme.colorScheme.background
                )
                .padding(it)
        ) {

            val scrollState = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = stringResource(R.string.enter_name),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onKeyEvent { event: KeyEvent ->
                            if (event.key == Key.Enter) {
                                /* do nothing */
                                true
                            }
                            false
                        },
                    value = contactToAddName,
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        focusedLabelColor = MaterialTheme.colorScheme.primary,
                        cursorColor = MaterialTheme.colorScheme.primary,
                    ),
                    onValueChange = {contactName: String ->
                        onContactToAddNameChange(contactName)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                )

                Spacer(modifier = Modifier.height(40.dp))


                Text(
                    text = stringResource(R.string.enter_surname),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onKeyEvent { event: KeyEvent ->
                            if (event.key == Key.Enter) {
                                /* do nothing */
                                true
                            }
                            false
                        },
                    value = contactToAddSurname,
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        focusedLabelColor = MaterialTheme.colorScheme.primary,
                        cursorColor = MaterialTheme.colorScheme.primary,
                    ),
                    onValueChange = {contactSurname: String ->
                        onContactToAddSurnameChange(contactSurname)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                )

                Spacer(modifier = Modifier.height(40.dp))

                Text(
                    text = stringResource(R.string.enter_phone),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .onKeyEvent { event: KeyEvent ->
                            if (event.key == Key.Enter) {
                                /* do nothing */
                                true
                            }
                            false
                        },
                    value = contactToAddPhone,
                    singleLine = true,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        focusedLabelColor = MaterialTheme.colorScheme.primary,
                        cursorColor = MaterialTheme.colorScheme.primary,
                    ),
                    onValueChange = {contactPhone: String ->
                        onContactToAddPhoneChange(contactPhone)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                )

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = errorMessage,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.error
                )


                Column (
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Button(
                        onClick = onSubmitButtonClick,
                        modifier = Modifier
                            .wrapContentSize(),
                        contentPadding = PaddingValues(),
                        colors = ButtonDefaults.buttonColors(Color.Transparent)
                    ) {

                        Box(
                            modifier = Modifier
                                .size(150.dp, 50.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.secondaryContainer,
                                    shape = RoundedCornerShape(8.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(id = R.string.submit),
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )

                        }
                    }
                }


            }
        }

    }

}