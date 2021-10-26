package com.example.photoalbumapp.domain.use_cases

import com.example.photoalbumapp.common.Resource
import com.example.photoalbumapp.data.repository.PhotoRepositoryImpl
import com.example.photoalbumapp.domain.model.Photo
import com.example.photoalbumapp.domain.repository.PhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val photoRepository: PhotoRepository
) {

    operator fun invoke(albumId: Int?): Flow<Resource<ArrayList<Photo>?>> = flow {
        try {
            emit(Resource.Loading())
            val photos = photoRepository.getPhotos(albumId)
            emit(Resource.Success(photos))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach the server. Check your internet connection"))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun getPhotos(albumId: Int?): Flow<Resource<ArrayList<Photo>?>> {
        return flow {
            try {
                emit(Resource.Loading())
                // get the photo Data from the api
                val request = photoRepository.getPhotos(albumId)
                // Emit this data wrapped in
                emit(Resource.Success(request))
            } catch (e: HttpException) {
                emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
            } catch (e: IOException) {
                emit(Resource.Error("Couldn't reach the server. Check your internet connection"))
            }

        }.flowOn(Dispatchers.IO)
    }
}
