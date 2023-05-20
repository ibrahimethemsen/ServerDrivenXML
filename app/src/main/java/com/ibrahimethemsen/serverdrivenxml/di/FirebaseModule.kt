package com.ibrahimethemsen.serverdrivenxml.di

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.gson.Gson
import com.ibrahimethemsen.serverdrivenxml.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
object FirebaseModule {
    @[Provides Singleton]
    fun provideRemoteConfig(): FirebaseRemoteConfig {
        return FirebaseRemoteConfig.getInstance().apply {
            val remoteConfigSettings =
                FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(2).build()
            setConfigSettingsAsync(remoteConfigSettings)
            setDefaultsAsync(R.xml.map_remote_config)
        }
    }
    @[Provides Singleton]
    fun provideGson(): Gson {
        return Gson()
    }
}