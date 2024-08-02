package uk.ac.tees.mad.d3649527

import android.content.Context
import androidx.room.Room
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TodoAppModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "todo-db").build()

    @Provides
    @Singleton
    fun provideTodoDao(appDatabase: AppDatabase): TodoDao = appDatabase.todoDao()

    @Provides
    @Singleton
    fun provideTodoRepository(todoDao: TodoDao): TodoRepository = TodoRepository(todoDao)

    @Provides
    @Singleton
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideFirebase(
        @ApplicationContext
        context: Context
    ) = FirebaseApp.initializeApp(context)

    @Provides
    fun provideFirebaseAuthInstance() =
        FirebaseAuth
            .getInstance()

    @Provides
    fun provideUserRepo(
        context: FirebaseAuth
    ): IAuthRepo = AuthRepo(context)
}