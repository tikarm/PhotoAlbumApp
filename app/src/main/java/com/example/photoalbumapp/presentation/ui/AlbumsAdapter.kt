package com.example.photoalbumapp.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.photoalbumapp.databinding.ItemAlbumBinding
import com.example.photoalbumapp.domain.model.Album

class AlbumsAdapter :
    RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>() {

    private var albumsList = ArrayList<Album>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val binding = ItemAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        if (albumsList.size > 0) {
            val positionInList = position % albumsList.size
            holder.bind(albumsList[positionInList])
        }
    }

    class AlbumViewHolder(private val binding: ItemAlbumBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(album: Album) {
            binding.tvTitleItemAlbum.text = album.title

            val layoutManager =
                LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
            binding.rvPhotosItemAlbum.layoutManager = layoutManager
            binding.rvPhotosItemAlbum.setHasFixedSize(true)

            val photosAdapter = PhotosAdapter()
            photosAdapter.addItems(album.photoList)

            val photosCount = album.photoList?.size

            binding.rvPhotosItemAlbum.adapter = photosAdapter

            if (photosCount != null) {
                layoutManager.scrollToPosition(
                    ((Integer.MAX_VALUE / 2) - ((Integer.MAX_VALUE / 2) % photosCount))
                )
            }
        }
    }


    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }

    fun addItems(albums: List<Album>) {
        albumsList.clear()
        albumsList.addAll(albums)
        notifyItemInserted(0)
    }
}