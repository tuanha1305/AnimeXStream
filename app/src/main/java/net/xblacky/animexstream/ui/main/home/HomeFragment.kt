package net.xblacky.animexstream.ui.main.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import net.xblacky.animexstream.BuildConfig
import net.xblacky.animexstream.R
import net.xblacky.animexstream.databinding.FragmentHomeBinding
import net.xblacky.animexstream.ui.main.home.epoxy.HomeController
import net.xblacky.animexstream.utils.constants.Const
import net.xblacky.animexstream.utils.model.AnimeMetaModel
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment(), View.OnClickListener, HomeController.EpoxyAdapterCallbacks {

    private var doubleClickLastTime = 0L
    private val viewModel: HomeViewModel by viewModels()
    private val homeController by lazy {
        HomeController(this)
    }

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        setAdapter()
        setClickListeners()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelObserver()
    }

    private fun setAdapter() {
        homeController.isDebugLoggingEnabled = true
        val homeRecyclerView = binding.recyclerView
        homeRecyclerView.layoutManager = LinearLayoutManager(context)
        homeRecyclerView.adapter = homeController.adapter
    }

    private fun viewModelObserver() {
        viewModel.animeList.observe(viewLifecycleOwner, Observer {
            homeController.setData(it)
        })

        viewModel.updateModel.observe(viewLifecycleOwner, Observer {
            Timber.e(it.whatsNew)
            if (it.versionCode > BuildConfig.VERSION_CODE) {
                showDialog(it.whatsNew)
            }
        })
    }

    private fun setClickListeners() {
        binding.header.setOnClickListener(this)
        binding.search.setOnClickListener(this)
        binding.favorite.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.header -> {
                doubleClickLastTime = if (System.currentTimeMillis() - doubleClickLastTime < 300) {
                    binding.recyclerView.smoothScrollToPosition(0)
                    0L
                } else {
                    System.currentTimeMillis()
                }

            }
            R.id.search -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToSearchFragment())
            }
            R.id.favorite -> {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFavouriteFragment())
            }
        }
    }

    override fun recentSubDubEpisodeClick(model: AnimeMetaModel) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToVideoPlayerActivity(
                episodeUrl = model.episodeUrl,
                animeName = model.title,
                episodeNumber = model.episodeNumber
            )
        )
    }

    override fun animeTitleClick(model: AnimeMetaModel) {
        if (!model.categoryUrl.isNullOrBlank()) {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToAnimeInfoFragment(
                    categoryUrl = model.categoryUrl
                )
            )
        }

    }

    private fun showDialog(whatsNew: String) {
        AlertDialog.Builder(requireContext()).setTitle("New Update Available")
            .setMessage("What's New ! \n$whatsNew")
            .setCancelable(false)
            .setPositiveButton("Update") { _, _ ->
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(Const.GIT_DOWNLOAD_URL)
                startActivity(i)
            }
            .setNegativeButton("Not now") { dialog, _ ->
                dialog.cancel()
            }.show()
    }

}