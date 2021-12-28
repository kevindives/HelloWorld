package com.magicworld.dccomics.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.magicworld.dccomics.R
import com.magicworld.dccomics.databinding.FragmentDetailBinding
import com.magicworld.dccomics.ui.main.MainActivity
import com.squareup.picasso.Picasso


class DetailFragment : Fragment() {

    private lateinit var detailBinding: FragmentDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    private val args: DetailFragmentArgs by navArgs()
    private var isFavorite = false

    private val callback = OnMapReadyCallback {googleMap ->
        val superheroe = args.superheroe
        val lugares = LatLng(superheroe.lat,superheroe.lng)
        googleMap.addMarker(
            MarkerOptions()
                .position(lugares)
                .title(superheroe.city)
                .snippet(superheroe.alias)
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lugares, superheroe.zoom))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //(activity as MainActivity?)?.showIcon()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        detailBinding = FragmentDetailBinding.inflate(inflater, container, false)

        return detailBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val superheroe = args.superheroe
        with(detailBinding) {
            nameTextView.text = superheroe.name
            aliasTextView.text = superheroe.alias
            alturaTextView.text = superheroe.altura.toString()
            ciudadTextView.text = superheroe.city
            facebookTextView.text = superheroe.facebook
            ocupacionTextView.text = superheroe.occupation
            powersTextView.text = superheroe.powers
            Picasso.get().load(superheroe.urlPicture).into(pictureImageView)

            imageButton.setOnClickListener{
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToMapsFragment(superheroe=superheroe))

            }
            pictureImageView.setOnClickListener{
                findNavController().navigate(DetailFragmentDirections.actionDetailFragmentToImageZoomFragment(superheroe=superheroe))
            }
            favoriteImageView.setOnClickListener{

                if (!isFavorite) {
                    detailViewModel.saveInFavorites(superheroe)
                    favoriteImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite))
                    isFavorite = true
                }else{
                    detailViewModel.deleteInFavorites(superheroe)
                    favoriteImageView.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_border))
                }
            }

        }

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }
}
