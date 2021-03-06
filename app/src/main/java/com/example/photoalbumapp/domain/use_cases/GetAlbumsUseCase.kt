package com.example.photoalbumapp.domain.use_cases

import com.example.photoalbumapp.common.Resource
import com.example.photoalbumapp.data.repository.AlbumRepositoryImpl
import com.example.photoalbumapp.domain.model.Album
import com.example.photoalbumapp.domain.repository.AlbumRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val albumRepository: AlbumRepository
) {

    operator fun invoke(userId : Int): Flow<Resource<ArrayList<Album>?>> = flow {
        try {
            emit(Resource.Loading())
            val albums = albumRepository.getAlbums(userId)
            emit(Resource.Success(albums))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach the server. Check your internet connection"))
        }
    }
}