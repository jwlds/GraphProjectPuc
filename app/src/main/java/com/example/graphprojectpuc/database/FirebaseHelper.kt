package com.example.graphprojectpuc.database

import com.google.firebase.firestore.FirebaseFirestore


class FirebaseHelper {

    companion object {
        // Obtém a instância do banco de dados do Firestore
        fun getDatabase() =  FirebaseFirestore.getInstance()
    }
}