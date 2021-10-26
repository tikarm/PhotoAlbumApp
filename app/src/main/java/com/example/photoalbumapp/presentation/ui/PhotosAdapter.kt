package com.example.photoalbumapp.presentation.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.photoalbumapp.R
import com.example.photoalbumapp.databinding.ItemPhotoBinding
import com.example.photoalbumapp.domain.model.Photo

class PhotosAdapter :
    RecyclerView.Adapter<PhotosAdapter.PhotoViewHolder>() {

    private var photosList = ArrayList<Photo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val binding = ItemPhotoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PhotoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        if (photosList.size > 0) {
            val positionInList = position % photosList.size
            holder.bind(photosList[positionInList])
        } else {
            holder.handleError()
        }
    }

    class PhotoViewHolder(private val binding: ItemPhotoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {

            val url = GlideUrl(
                photo.url, LazyHeaders.Builder()
                    .addHeader("User-Agent", "my-user-agent")
                    .build()
            )


            Glide.with(itemView.context)
                .load(url)
                .placeholder(createProgressBar(binding.root.context))
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.tvFailItemPhoto.visibility = VISIBLE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }
                })
                .into(binding.ivItemPhoto)
        }

        fun handleError() {
            binding.ivItemPhoto.setBackgroundResource(R.drawable.ic_baseline_broken_image_24)
        }

        private fun createProgressBar(context: Context): CircularProgressDrawable {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            return circularProgressDrawable
        }
    }


    override fun getItemCount(): Int {
        return Int.MAX_VALUE
    }


    fun addItems(photos: ArrayList<Photo>?) {
        photosList.clear()
        if (photos != null) {
            photosList.addAll(photos)
        }
        notifyDataSetChanged()
    }


}