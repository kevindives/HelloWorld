package com.magicworld.dccomics.ui.detail

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.magicworld.dccomics.databinding.FragmentImageZoomBinding
import com.magicworld.dccomics.ui.main.MainActivity
import com.squareup.picasso.Picasso


class ImageZoomFragment : Fragment() {

    private lateinit var  imageBinding : FragmentImageZoomBinding
    private val args: ImageZoomFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        imageBinding= FragmentImageZoomBinding.inflate(inflater, container,false)
        return imageBinding.root
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val superheroe = args.superheroe
        Picasso.get().load(superheroe.urlPicture).into(imageBinding.zoomImageView)
        (activity as MainActivity).actionBar?.hide()
    }

}