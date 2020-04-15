package com.dvoineu.crnvirus.repository

import com.dvoineu.crnvirus.app.Injection

object RemoteRepository: Repository {
    private const val TAG = "RemoteRepository"

    private val api = Injection.provideCRNVApi()



}