package com.magicworld.dccomics.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.magicworld.dccomics.data.local.SuperheroeLocal
import com.magicworld.dccomics.databinding.FavoritesFragmentBinding


class FavoritesFragment : Fragment() {

    private lateinit var favoritesBinding: FavoritesFragmentBinding
    private  val viewModel: FavoritesViewModel by viewModels()
    private var favoritesSuperheroeList: ArrayList<SuperheroeLocal> = arrayListOf()
    private lateinit var favoritesAdapter : FavoritesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        favoritesBinding = FavoritesFragmentBinding.inflate(inflater, container, false)
        return favoritesBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getFavoritesSuperheroe()

        viewModel.onSuperheroeLoaded.observe(viewLifecycleOwner, {result->
            onSuperHeroeLoadedSubscribe(result)
        })
        favoritesAdapter = FavoritesAdapter(favoritesSuperheroeList, onItemClicked = {onFavoriteSuperheroeCLicked(it)})

        favoritesBinding.favoritesRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = favoritesAdapter
            setHasFixedSize(false)
        }

    }


    private fun onFavoriteSuperheroeCLicked(superheroe: SuperheroeLocal) {

    }

    private fun onSuperHeroeLoadedSubscribe(result: MutableList<SuperheroeLocal>?) {
        result?.let{listSuperheroeList->
            favoritesAdapter.appendItems(listSuperheroeList as ArrayList<SuperheroeLocal>)
        }
    }
}