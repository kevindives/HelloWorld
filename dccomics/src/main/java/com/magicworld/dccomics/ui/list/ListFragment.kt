package com.magicworld.dccomics.ui.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.helloworld.SuperHeroesAdapter
import com.magicworld.dccomics.databinding.FragmentListBinding
import com.magicworld.dccomics.ui.main.MainActivity
import com.magicworld.dccomics.model.SuperheroeItem

class ListFragment : Fragment() {

    private lateinit var listBinding: FragmentListBinding
    private val listViewModel: ListViewModel by viewModels()
    private lateinit var superHeroesAdapter: SuperHeroesAdapter
    private var listSuperheroes: ArrayList<SuperheroeItem> = arrayListOf()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        listBinding = FragmentListBinding.inflate(inflater, container, false)
        return listBinding.root
    }

    override fun onStart() {
        super.onStart()
        listViewModel.checkUserConnected()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //(activity as MainActivity?)?.hideIcon()
        //listViewModel.loadMockSuperHeroesFromJson(context?.assets?.open("superheroes.json"))
        listViewModel.getSuperheroesFromServer()
        //listViewModel.getSuperheroesFromFireBase()
        listBinding.progressBar.visibility = View.VISIBLE

        listViewModel.onUserLoggedIn.observe(viewLifecycleOwner, { result ->
            onUserLoggedInSubscribe(result)
        })

        listViewModel.onSuperheroesLoaded.observe(viewLifecycleOwner, { result ->
            onSuperheroesLoadedSubscribe(result)
        })

        superHeroesAdapter = SuperHeroesAdapter(listSuperheroes, onItemClicked = { onSuperheroeCliked(it) })

        listBinding.superheoresRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = superHeroesAdapter
            setHasFixedSize((false))
        }
    }

    private fun onUserLoggedInSubscribe(result: Boolean?) {
        result?.let { isLoggedIn ->
            if (!isLoggedIn)
                findNavController().navigate(ListFragmentDirections.actionListFragmentToLoginFragment())
        }
    }

    private fun onSuperheroesLoadedSubscribe(result: ArrayList<SuperheroeItem>?) {
        result?.let { listSuperheroes ->
            superHeroesAdapter.appendItems(listSuperheroes)
            listBinding.progressBar.visibility = View.INVISIBLE
        }
    }

    private fun onSuperheroeCliked(superheroe: SuperheroeItem) {
        findNavController().navigate(
            ListFragmentDirections.actionListFragmentToDetailFragment(
                superheroe = superheroe
            )
        )
    }
}