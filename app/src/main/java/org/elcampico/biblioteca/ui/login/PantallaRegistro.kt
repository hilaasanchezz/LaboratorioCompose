package org.elcampico.biblioteca.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.elcampico.biblioteca.R
import org.elcampico.biblioteca.ui.theme.BibliotecaTheme

// Definimos la fuente aquí para que el archivo sea independiente

@Composable
fun PantallaRegistro(
    onCrear: (nombre: String, correo: String, clave: String) -> Unit,
    onIrALogin: () -> Unit,
    modifier: Modifier = Modifier
) {
    var nombre by rememberSaveable { mutableStateOf("") }
    var correo by rememberSaveable { mutableStateOf("") }
    var clave by rememberSaveable { mutableStateOf("") }
    var terminos by rememberSaveable { mutableStateOf(false) }
    var verClave by rememberSaveable { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        // 1. Fondo
        Image(
            painter = painterResource(R.drawable.fondo_login),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        // 2. Capa Oscura
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Black.copy(alpha = 0.6f))
        )

        // 3. Contenedor principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
                .imePadding()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Crear cuenta",
                color = Color.White,
                fontFamily = Montserrat,
                fontWeight = FontWeight.Black,
                fontSize = 34.sp
            )

            Text(
                text = "Solo te llevará un minuto",
                color = Color.White.copy(alpha = 0.85f),
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(Modifier.height(32.dp))

            // Tarjeta del formulario
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.92f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = nombre,
                        onValueChange = { nombre = it },
                        label = { Text("Nombre") },
                        leadingIcon = { Icon(Icons.Default.Person, null) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = correo,
                        onValueChange = { correo = it },
                        label = { Text("Correo") },
                        leadingIcon = { Icon(Icons.Default.Email, null) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = clave,
                        onValueChange = { clave = it },
                        label = { Text("Contraseña") },
                        leadingIcon = { Icon(Icons.Default.Lock, null) },
                        trailingIcon = {
                            IconButton(onClick = { verClave = !verClave }) {
                                val icono = if (verClave) Icons.Default.VisibilityOff else Icons.Default.Visibility
                                Icon(icono, contentDescription = "Alternar contraseña")
                            }
                        },
                        visualTransformation = if (verClave) VisualTransformation.None else PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Checkbox(
                            checked = terminos,
                            onCheckedChange = { terminos = it }
                        )
                        Text("Acepto las normas de la biblioteca")
                    }

                    Button(
                        onClick = { onCrear(nombre, correo, clave) },
                        enabled = nombre.isNotBlank() && correo.isNotBlank() && clave.isNotBlank() && terminos,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text("Crear cuenta", fontFamily = Montserrat, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(Modifier.height(16.dp))

            // Fila inferior para ir al Login
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "¿Ya tienes cuenta? ", color = Color.White)
                TextButton(onClick = onIrALogin) {
                    Text(
                        text = "Inicia sesión",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

// Vista previa necesaria para la Fase 5
@Preview(showSystemUi = true)
@Composable
private fun PantallaRegistroPreview() {
    BibliotecaTheme {
        PantallaRegistro(
            onCrear = { _, _, _ -> },
            onIrALogin = { }
        )
    }
}