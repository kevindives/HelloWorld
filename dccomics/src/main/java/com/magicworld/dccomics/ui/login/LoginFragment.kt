package com.magicworld.dccomics.ui.login

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.magicworld.dccomics.databinding.LoginFragmentBinding
import com.magicworld.dccomics.ui.main.MainActivity
import com.magicworld.dccomics.utils.isEmailValid

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var loginBinding: LoginFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        (activity as MainActivity).hideIcon()
        loginBinding = LoginFragmentBinding.inflate(inflater, container, false)

        return loginBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onUserLoggedIn.observe(viewLifecycleOwner, { result ->
            onUserLoggedInSubscribe(result)
        })

        with(loginBinding) {
            loginButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                if (email.isEmpty() || password.isEmpty())
                    Toast.makeText(context, "Debe digitar correo y contraseña", Toast.LENGTH_SHORT)
                        .show()
                else
                    if (!isEmailValid(email))
                        Toast.makeText(
                            context,
                            "El correo no tiene un formato válido",
                            Toast.LENGTH_SHORT
                        ).show()
                    else
                        viewModel.login(email, password)
            }

        }
    }

    private fun onUserLoggedInSubscribe(result: String?) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show()
        if (result.equals("Bienvenido")) {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToListFragment())
        }
    }

    override fun onStart() {
        super.onStart()
        loginBinding.registerTextView.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}