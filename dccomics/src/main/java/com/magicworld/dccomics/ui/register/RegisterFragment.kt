package com.magicworld.dccomics.ui.register

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.magicworld.dccomics.databinding.RegisterFragmentBinding
import com.magicworld.dccomics.ui.main.MainActivity

class RegisterFragment : Fragment() {

    private val viewModel: RegisterViewModel by viewModels()
    private lateinit var registerBinding: RegisterFragmentBinding
    private lateinit var email: String
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setHasOptionsMenu(true)
        (activity as MainActivity).showIcon()
        registerBinding = RegisterFragmentBinding.inflate(inflater, container, false)

        return registerBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.onUserCreate.observe(viewLifecycleOwner, { result ->
            onUserCreateSubscribe(result)
        })
        viewModel.onUserRegister.observe(viewLifecycleOwner, { result ->
            onUserRegisterSubscribe(result)
        })

        with(registerBinding) {
            registerButton.setOnClickListener {
                email = emailEditText.text.toString()
                username = usernameEditText.text.toString()
                val password = passwordEditText.text.toString()

                if (email.isEmpty() || password.isEmpty())
                    Toast.makeText(
                        context,
                        "Debe escribir un correo electronico y una contraseña",
                        Toast.LENGTH_SHORT
                    ).show()
                else
                    viewModel.register(email, password)
            }

        }
    }

    private fun onUserRegisterSubscribe(result: String?) {
        if (result.equals("Usuario registrado exitosamente")) {
            viewModel.createUserAccount(email, username)
        } else
            Toast.makeText(context, result, Toast.LENGTH_LONG).show()
    }

    private fun onUserCreateSubscribe(result: String?) {
        Toast.makeText(context, result, Toast.LENGTH_LONG).show()
        if (result.equals("Usuario creado de forma exitosa")) {
            findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }
}



