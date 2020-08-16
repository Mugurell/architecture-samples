package com.example.android.architecture.blueprints.todoapp.di.modules

import com.example.android.architecture.blueprints.todoapp.di.qualifiers.IODispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.Dispatchers

@InstallIn(ApplicationComponent::class)
@Module
object UtilsModule {
    @Provides
    @IODispatcher
    // Unscoped. It's already a static property.
    fun provideIODispatcher() = Dispatchers.IO
}
