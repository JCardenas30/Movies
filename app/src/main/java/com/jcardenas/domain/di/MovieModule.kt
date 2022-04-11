package com.jcardenas.domain.di

import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage
import com.jcardenas.data.api.MoviesApi
import com.jcardenas.data.db.AppDatabase
import com.jcardenas.data.db.MovieDao
import com.jcardenas.data.db.UserDao
import com.jcardenas.data.mappers.MovieApiResponseMapper
import com.jcardenas.data.mappers.MovieEntityMapper
import com.jcardenas.data.mappers.UserEntityMapper
import com.jcardenas.data.repositories.images.ImageRemoteDataSource
import com.jcardenas.data.repositories.images.ImageRemoteDataSourceImpl
import com.jcardenas.data.repositories.images.ImageRepositoryImpl
import com.jcardenas.data.repositories.location.LocationRemoteDataSource
import com.jcardenas.data.repositories.location.LocationRemoteDataSourceImpl
import com.jcardenas.data.repositories.location.LocationRepositoryImpl
import com.jcardenas.data.repositories.movies.*
import com.jcardenas.data.repositories.users.*
import com.jcardenas.domain.common.Constants
import com.jcardenas.domain.repository.ImageRepository
import com.jcardenas.domain.repository.LocationRepository
import com.jcardenas.domain.repository.MovieRepository
import com.jcardenas.domain.repository.UserRepository
import com.jcardenas.domain.usecases.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
@Module
@InstallIn(SingletonComponent::class)
class MovieModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context) = AppDatabase.getDatabase(context)


    @Singleton
    @Provides
    fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao()
    }

    @Singleton
    @Provides
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMoviesApi(retrofit: Retrofit): MoviesApi {
        return retrofit.create(MoviesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMovieEntityMapper() = MovieEntityMapper()

    @Singleton
    @Provides
    fun provideMovieApiResponseMapper() = MovieApiResponseMapper()

    @Singleton
    @Provides
    fun provideMovieLocalDataSource(movieDao: MovieDao, mapper: MovieEntityMapper): MovieLocalDataSource {
        return MovieLocalDataSourceImpl(movieDao, mapper)
    }

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(moviesApi: MoviesApi, mapper: MovieApiResponseMapper): MovieRemoteDataSource {
        return MovieRemoteDataSourceImpl(moviesApi, mapper)
    }

    @Singleton
    @Provides
    fun provideMovieRepository(movieLocalDataSource: MovieLocalDataSource, movieRemoteDataSource: MovieRemoteDataSource): MovieRepository{
        return MovieRepositoryImpl(movieLocalDataSource, movieRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideGetMovieUseCase(movieRepository: MovieRepository): GetMovieUseCase {
        return GetMovieUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideInsertMovieUseCase(movieRepository: MovieRepository): InsertMovieUseCase{
        return InsertMovieUseCase(movieRepository)
    }

    @Singleton
    @Provides
    fun provideFirestore() = Firebase.firestore

    @Singleton
    @Provides
    fun provideLocationRemoteDataSource(firestore: FirebaseFirestore): LocationRemoteDataSource {
        return LocationRemoteDataSourceImpl(firestore)
    }

    @Singleton
    @Provides
    fun provideLocationRepository(locationRemoteDataSource: LocationRemoteDataSource): LocationRepository {
        return LocationRepositoryImpl(locationRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideGetLocationUseCase(locationRepository: LocationRepository): GetLocationUseCase {
        return GetLocationUseCase(locationRepository)
    }

    @Singleton
    @Provides
    fun provideSaveLocationUseCase(locationRepository: LocationRepository): SaveLocationUseCase {
        return SaveLocationUseCase(locationRepository)
    }

    @Singleton
    @Provides
    fun provideStorage() = Firebase.storage

    @Singleton
    @Provides
    fun provideImageRemoteDataSource(storage: FirebaseStorage): ImageRemoteDataSource {
        return ImageRemoteDataSourceImpl(storage)
    }

    @Singleton
    @Provides
    fun provideImageRepository(imageRemoteDataSource: ImageRemoteDataSource): ImageRepository {
        return ImageRepositoryImpl(imageRemoteDataSource)
    }

    @Singleton
    @Provides
    fun provideSaveImageUseCase(imageRepository: ImageRepository): SaveImageUseCase {
        return SaveImageUseCase(imageRepository)
    }

    @Singleton
    @Provides
    fun provideListAllImageUseCase(imageRepository: ImageRepository): ListAllImageUseCase {
        return ListAllImageUseCase(imageRepository)
    }

    @Singleton
    @Provides
    fun provideUserEntityMapper(): UserEntityMapper {
        return UserEntityMapper()
    }

    @Singleton
    @Provides
    fun provideUserLocalDataSource(userDao: UserDao, userMapper: UserEntityMapper): UserLocalDataSource {
        return UserLocalDataSourceImpl(userDao, userMapper)
    }

    @Singleton
    @Provides
    fun provideUserRemoteDataSource(storage: FirebaseStorage): UserRemoteDataSource {
        return UserRemoteDataSourceImpl(storage)
    }

    @Singleton
    @Provides
    fun provideUserRepository(localDataSource: UserLocalDataSource, remoteDataSource: UserRemoteDataSource): UserRepository {
        return UserRepositoryImpl(localDataSource, remoteDataSource)
    }

    @Singleton
    @Provides
    fun provideGetUserUseCase(userRepository: UserRepository): GetUserUseCase {
        return GetUserUseCase(userRepository)
    }

    @Singleton
    @Provides
    fun provideInsertUserUseCase(userRepository: UserRepository): InsertUserUseCase {
        return InsertUserUseCase(userRepository)
    }

    @Singleton
    @Provides
    fun provideUploadProfileUseCase(userRepository: UserRepository): UploadProfileUseCase {
        return UploadProfileUseCase(userRepository)
    }
}

