package com.example.android.architecture.blueprints.todoapp.di.modules

import com.example.android.architecture.blueprints.todoapp.data.source.DefaultTasksRepository
import com.example.android.architecture.blueprints.todoapp.data.source.TasksDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import com.example.android.architecture.blueprints.todoapp.data.source.local.TasksDao
import com.example.android.architecture.blueprints.todoapp.data.source.local.TasksLocalDataSource
import com.example.android.architecture.blueprints.todoapp.data.source.remote.TasksRemoteDataSource
import com.example.android.architecture.blueprints.todoapp.di.qualifiers.IODispatcher
import com.example.android.architecture.blueprints.todoapp.di.qualifiers.LocalTasksDataSource
import com.example.android.architecture.blueprints.todoapp.di.qualifiers.RemoteTasksDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class TasksModules {
    @LocalTasksDataSource
    @Binds
    // Unscoped. Used only by the Repository below which is a app wide singleton.
    abstract fun bindLocalTasksDataSource(source: TasksLocalDataSource): TasksDataSource

    // Used a new object here to obtain "static providers"
    @InstallIn(ApplicationComponent::class)
    @Module
    object TasksRepositoryModule{
        @RemoteTasksDataSource
        @Provides
        // Unscoped. Used only by the Repository below which is a app wide singleton.
        fun provideRemoteTasksDataSource(): TasksDataSource = TasksRemoteDataSource

        @Provides
        // Unscoped. Used only by the above Bind.
        fun provideLocalTasksDataSource(
            tasksDao: TasksDao,
            @IODispatcher dispatcher: CoroutineDispatcher
        ): TasksLocalDataSource = TasksLocalDataSource(tasksDao, dispatcher)

        @Provides
        @Singleton
        fun provideTasksRepository(
            @RemoteTasksDataSource remoteTasksSource: TasksDataSource,
            @LocalTasksDataSource localTasksSource: TasksDataSource,
            @IODispatcher dispatcher: CoroutineDispatcher
        ): TasksRepository = DefaultTasksRepository(remoteTasksSource, localTasksSource, dispatcher)
    }
}
