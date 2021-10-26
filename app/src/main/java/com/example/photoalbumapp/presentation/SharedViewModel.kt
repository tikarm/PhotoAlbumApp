package com.example.photoalbumapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photoalbumapp.common.Resource
import com.example.photoalbumapp.domain.model.Album
import com.example.photoalbumapp.domain.model.Photo
import com.example.photoalbumapp.domain.use_cases.GetAlbumsUseCase
import com.example.photoalbumapp.domain.use_cases.GetPhotosUseCase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SharedViewModel(
    private val getAlbumsUseCase: GetAlbumsUseCase,
    private val getPhotosUseCase: GetPhotosUseCase
) : ViewModel() {

    private val albumsMutableLiveData = MutableLiveData<ArrayList<Album>?>()
    val albumsLiveData: LiveData<ArrayList<Album>?> = albumsMutableLiveData

    private val photosMutableLiveData = MutableLiveData<ArrayList<Photo>?>()
    val photosLiveData: LiveData<ArrayList<Photo>?> = photosMutableLiveData

    private val loadingMutableLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = loadingMutableLiveData

    private val textMessageMutableLiveData = MutableLiveData<Boolean>()
    val textMessageLiveData: LiveData<Boolean> = textMessageMutableLiveData


    fun refreshAlbums(userId: Int) {
        getAlbumsUseCase(userId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    albumsMutableLiveData.postValue(result.data)
                    loadingMutableLiveData.postValue(true)
                }
                is Resource.Error -> {
                    textMessageMutableLiveData.postValue(result.data == null)
                }

                is Resource.Loading -> {
                    loadingMutableLiveData.postValue(false)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAllPhotos(albumIdList: ArrayList<Int?>) {
        viewModelScope.launch {
            for (id in albumIdList) {
                getPhotosUseCase.getPhotos(id).collect{
                    when(it){
                        is Resource.Success -> {
                            photosMutableLiveData.postValue(it.data)
                        }
                    }
                }
            }
        }
    }

    fun fillAlbumWithPhotos(albumId: Int?, photos: ArrayList<Photo>) {
        val albums = albumsLiveData.value
        val album = albums?.find { a -> a.id == albumId }
        album?.photoList = photos
    }
}