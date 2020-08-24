package com.example.android.architecture.blueprints.todoapp.di.modules

import com.example.android.architecture.blueprints.todoapp.data.source.FakeRepository
import com.example.android.architecture.blueprints.todoapp.data.source.TasksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
abstract class TestTasksModule {
    @Binds
    abstract fun bindTestTasksRepository(fake: FakeRepository): TasksRepository
}
