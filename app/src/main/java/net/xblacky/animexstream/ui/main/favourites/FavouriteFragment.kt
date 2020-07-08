package net.xblacky.animexstream.ui.main.favourites

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import net.xblacky.animexstream.R
import net.xblacky.animexstream.databinding.FragmentFavouriteBinding
import net.xblacky.animexstream.databinding.FragmentSearchBinding
import net.xblacky.animexstream.ui.main.favourites.epoxy.FavouriteController
import net.xblacky.animexstream.utils.ItemOffsetDecoration
import net.xblacky.animexstream.utils.Utils
import net.xblacky.animexstream.utils.model.FavouriteModel

@AndroidEntryPoint
class FavouriteFragment : Fragment(),
    FavouriteController.EpoxySearchAdapterCallbacks,
    View.OnClickListener {

    private val viewModel: FavouriteViewModel by viewModels()
    private val favouriteController by lazy {
        FavouriteController(this)
    }

    private lateinit var favouriteBinding: FragmentFavouriteBinding
    private lateinit var searchBinding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favouriteBinding = FragmentFavouriteBinding.inflate(inflater, container, false)
        searchBinding = FragmentSearchBinding.inflate(inflater, container, false)

        setAdapters()
        transitionListener()
        setClickListeners()

        return favouriteBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setObserver()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            favouriteController.spanCount = 5
            (searchBinding.searchRecyclerView.layoutManager as GridLayoutManager).spanCount = 5
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            favouriteController.spanCount = 3
            (searchBinding.searchRecyclerView.layoutManager as GridLayoutManager).spanCount = 3
        }
    }

    private fun setObserver() {
        viewModel.favouriteList.observe(viewLifecycleOwner, Observer {
            favouriteController.setData(it)
        })
    }

    private fun setAdapters() {
        favouriteController.spanCount = Utils.calculateNoOfColumns(requireContext(), 150f)
        favouriteBinding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, Utils.calculateNoOfColumns(requireContext(), 150f))
            adapter = favouriteController.adapter
            (layoutManager as GridLayoutManager).spanSizeLookup = favouriteController.spanSizeLookup
        }
        favouriteBinding.recyclerView.addItemDecoration(
            ItemOffsetDecoration(
                context,
                R.dimen.episode_offset_left
            )
        )
    }

    private fun getSpanCount(): Int {
        val orientation = resources.configuration.orientation
        return if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            5
        } else {
            3
        }
    }

    private fun transitionListener() {
        favouriteBinding.motionLayout.setTransitionListener(
            object : MotionLayout.TransitionListener {
                override fun onTransitionTrigger(
                    p0: MotionLayout?,
                    p1: Int,
                    p2: Boolean,
                    p3: Float
                ) {

                }

                override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {
                    favouriteBinding.topView.cardElevation = 0F
                }

                override fun onTransitionChange(
                    p0: MotionLayout?,
                    startId: Int,
                    endId: Int,
                    progress: Float
                ) {
                    if (startId == R.id.start) {
                        favouriteBinding.topView.cardElevation = 20F * progress
                        favouriteBinding.toolbarText.alpha = progress
                    } else {
                        favouriteBinding.topView.cardElevation = 10F * (1 - progress)
                        favouriteBinding.toolbarText.alpha = (1 - progress)
                    }
                }

                override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                }

            }
        )
    }

    private fun setClickListeners() {
        favouriteBinding.back.setOnClickListener(this)
    }

    override fun animeTitleClick(model: FavouriteModel) {
        findNavController().navigate(
            FavouriteFragmentDirections.actionFavouriteFragmentToAnimeInfoFragment(
                categoryUrl = model.categoryUrl
            )
        )
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.back -> {
                findNavController().popBackStack()
            }
        }
    }

}

