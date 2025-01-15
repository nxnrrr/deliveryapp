package com.example.deliveryapp.com.example.deliveryapp

//package com.ag_apps.googlesignin

import com.example.deliveryapp.AuthModel
import android.app.Activity
import android.content.SharedPreferences
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import kotlinx.coroutines.tasks.await
import kotlin.coroutines.cancellation.CancellationException


class GoogleAuthClient(
    private val activity: Activity, // Changed from Context to Activity
) {
    private val tag = "GoogleAuthClient"
    private val credentialManager = CredentialManager.create(activity)
    private val firebaseAuth = FirebaseAuth.getInstance()

    fun isSignedIn(): Boolean {
        if (firebaseAuth.currentUser != null) {
            println(tag + "already signed in")
            println(firebaseAuth.currentUser)
            firebaseAuth.signOut()
            return true
        }
        return false
    }

    suspend fun signIn(sharedPreferences: SharedPreferences, authModel: AuthModel): Boolean {
        if (isSignedIn()) {
            return true
        }
        try {
            val credentialResponse = buildCredentialRequest()
            return handleSignIn(credentialResponse, sharedPreferences, authModel)
        } catch (e: Exception) {
            e.printStackTrace()
            if (e is CancellationException) throw e

            println("$tag: signIn error: ${e.message}")
            return false
        }
    }

    private suspend fun handleSignIn(
        result: GetCredentialResponse,
        sharedPreferences: SharedPreferences,
        authModel: AuthModel
    ): Boolean {
        val credential = result.credential

        if (credential is CustomCredential &&
            credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
        ) {
            try {
                val tokenCredential = GoogleIdTokenCredential.createFrom(credential.data)

                println("Name: ${tokenCredential.displayName}")
                println("Email: ${tokenCredential.id}")
                println("Image: ${tokenCredential.profilePictureUri}")

                // Appel Firebase pour valider les informations
                val authCredential = GoogleAuthProvider.getCredential(
                    tokenCredential.idToken, null
                )
                val authResult = firebaseAuth.signInWithCredential(authCredential).await()
                if (authResult.user != null) {
                    authModel.loginWithGoogle(tokenCredential.id)
                }
            } catch (e: GoogleIdTokenParsingException) {
                println("GoogleIdTokenParsingException: ${e.message}")
            } catch (e: Exception) {
                println("Error during sign-in: ${e.message}")
            }
        } else {
            println("Credential is not GoogleIdTokenCredential")
        }
        return false
    }

    private suspend fun buildCredentialRequest(): GetCredentialResponse {
        val request = GetCredentialRequest.Builder()
            .addCredentialOption(
                GetGoogleIdOption.Builder()
                    .setFilterByAuthorizedAccounts(false)
                    .setServerClientId("57091984739-sq00hv8padg9k447labb2sqrrqo6lrcd.apps.googleusercontent.com")
                    .setAutoSelectEnabled(false)
                    .build()
            )
            .build()

        // Ensure the correct context (activity) is passed
        return credentialManager.getCredential(
            request = request,
            context = activity
        )
    }

    suspend fun signOut() {
        try {
            credentialManager.clearCredentialState(ClearCredentialStateRequest())
            firebaseAuth.signOut()
            println("$tag: Successfully signed out")
        } catch (e: Exception) {
            println("$tag: Error during signOut: ${e.message}")
        }
    }
}
