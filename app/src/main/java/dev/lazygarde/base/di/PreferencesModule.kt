package dev.lazygarde.base.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.lazygarde.flow.preferences.FlowSharedPreferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PreferencesModule {


    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharedPreferencesEditor(sharedPreferences: SharedPreferences): SharedPreferences.Editor {
        return sharedPreferences.edit()
    }

    @Provides
    @Singleton
    fun provideFlowSharedPreferences(sharedPreferences: SharedPreferences): FlowSharedPreferences {
        return FlowSharedPreferences(sharedPreferences)
    }
}