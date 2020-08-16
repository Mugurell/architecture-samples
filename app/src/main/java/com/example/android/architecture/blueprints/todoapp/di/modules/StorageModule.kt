package com.example.android.architecture.blueprints.todoapp.di.modules

import android.content.Context
import androidx.room.Room
import com.example.android.architecture.blueprints.todoapp.data.source.local.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
/**
 * Private qualifier - dependencies using this will be available only in this module.
 */
internal annotation class InternalApi

@InstallIn(ApplicationComponent::class)
@Module
object StorageModule {
    @Provides
    // Unscoped. Used in only one Provider, app wide singleton.
    fun providesTasksDao(@InternalApi db: ToDoDatabase) = db.taskDao()

    @Provides
    @InternalApi
    // Unscoped. Used only by the above provider.
    fun provideDatabase(@ApplicationContext context: Context): ToDoDatabase {
        return Room.databaseBuilder(
            context,
            ToDoDatabase::class.java,
            "NothingToSee.Here"
        ).build()
    }
}
