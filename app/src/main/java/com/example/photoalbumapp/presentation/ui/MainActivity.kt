package com.example.photoalbumapp.presentation.ui

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.photoalbumapp.*
import com.example.photoalbumapp.databinding.ActivityMainBinding
import com.example.photoalbumapp.domain.model.Album
import com.example.photoalbumapp.presentation.SharedViewModel
import com.example.photoalbumapp.presentation.SharedViewModelFactory
import com.example.photoalbumapp.utils.AlbumUtils
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: SharedViewModelFactory

    private lateinit var sharedViewModel: SharedViewModel
    private lateinit var albumsRecyclerAdapter: AlbumsAdapter

    // binding
    private lateinit var binding: ActivityMainBinding

    private var dataFetched: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        val component = (application as MyApplication).appComponent

        component.inject(this)

        super.onCreate(savedInstanceState)

        if (savedInstanceState != null) {
            dataFetched = savedInstanceState.getBoolean("data_fetched")
        }

        setupViewBinding()

        setupViewModel()

        initAlbumsRecyclerView()

        setupObservers()

        // prevent secondary data fetching
        if (!dataFetched) {
            getAlbums()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putBoolean("data_fetched", dataFetched)
        super.onSaveInstanceState(outState)
    }

    private fun setupViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupViewModel() {
        sharedViewModel = ViewModelProviders.of(
            this, viewModelFactory
        ).get(SharedViewModel::class.java)
    }

    private fun initAlbumsRecyclerView() {
        albumsRecyclerAdapter = AlbumsAdapter()

        val layoutManager = LinearLayoutManager(this)
        binding.rvAlbumsActivityMain.layoutManager = layoutManager

        binding.rvAlbumsActivityMain.setHasFixedSize(true)
        binding.rvAlbumsActivityMain.adapter = albumsRecyclerAdapter

        val dividerItemDecoration = DividerItemDecoration(
            this,
            layoutManager.orientation
        )
        binding.rvAlbumsActivityMain.addItemDecoration(dividerItemDecoration)
    }

    private fun getAlbums() {
        sharedViewModel.refreshAlbums(1)
    }

    private fun getAllPhotos(albumIdList: ArrayList<Int?>) {
        sharedViewModel.getAllPhotos(albumIdList)
    }

    private fun setupObservers() {
        sharedViewModel.albumsLiveData.observe(this) { response ->
            if (response == null) {
                return@observe
            }

            // prevent secondary data fetching
            if (!dataFetched) {
                dataFetched = !dataFetched

                getAllPhotos(AlbumUtils.getAlbumIds(response))
            }
            binding.rvAlbumsActivityMain.visibility = VISIBLE
            addItemsToAlbumRecyclerView(response)
        }

        sharedViewModel.photosLiveData.observe(this) { response ->
            if (response == null) {
                return@observe
            }

            // fill album with its' corresponding photos
            sharedViewModel.fillAlbumWithPhotos(response[0].albumId, response)

            albumsRecyclerAdapter.notifyDataSetChanged()
        }

        sharedViewModel.loadingLiveData.observe(this) { state ->
            if (state) {
                binding.pbActivityMain.visibility = GONE
            } else {
                binding.pbActivityMain.visibility = VISIBLE
            }
        }

        sharedViewModel.textMessageLiveData.observe(this) { state ->
            if (state) {
                binding.tvMessageActivityMain.visibility = VISIBLE
            } else {
                binding.tvMessageActivityMain.visibility = GONE
            }
        }
    }

    private fun addItemsToAlbumRecyclerView(items: List<Album>) {
        albumsRecyclerAdapter.addItems(items)
        binding.rvAlbumsActivityMain.layoutManager?.scrollToPosition(((Integer.MAX_VALUE / 2) - ((Integer.MAX_VALUE / 2) % items.size)))
    }
}