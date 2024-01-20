package com.example.phonebook.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.phonebook.R
import com.example.phonebook.domain.model.Contact
import com.example.phonebook.presentation.home.HomeScreen
import com.example.phonebook.ui.theme.PhonebookTheme

@Composable
fun ContactCard(
    contact: Contact,
    modifier: Modifier = Modifier,
    onDeleteContact: (Contact) -> Unit,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        )
    ){

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly,
        ) {

            ContactIconCard(
                photoSrc = contact.profilePicture
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = "${contact.name} ${contact.surname}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = contact.phone
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                IconButton(
                    onClick = {
                        onDeleteContact(contact)
                    }
                ) {

                    Icon(
                        modifier = Modifier
                            .size(34.dp),
                        imageVector = Icons.Default.Delete,
                        contentDescription = stringResource(R.string.delete_contact)
                    )
                    
                }
            }


        }

    }

}



@Composable
fun ContactIconCard(photoSrc: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )

    ) {

        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current).data(photoSrc)
                .crossfade(true).build(),
            error = painterResource(R.drawable.broken_image),
            placeholder = painterResource(R.drawable.image_holder),
            contentDescription = "profile icon",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(64.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ContactCardPrev() {
    PhonebookTheme (dynamicColor = false) {
        ContactCard(
            contact = Contact(
                name = "Bartu",
                surname = "Uzun",
                phone = "00000000000",
                profilePicture = "ehe",
            ),
            onDeleteContact = {},
        )
    }
}
