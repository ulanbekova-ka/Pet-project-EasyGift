package com.kay.prog.easygift.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kay.prog.easygift.R
import com.kay.prog.easygift.databinding.FragmentMainBinding
import com.kay.prog.easygift.extensions.showToast
import com.kay.prog.easygift.ui.authorisation.AuthorisationFragment
import com.kay.prog.easygift.ui.base.FragmentListener
import dagger.hilt.android.AndroidEntryPoint

//@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var fragmentListener: FragmentListener

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainBinding.bind(view)
        fragmentListener = context as FragmentListener

        setupViews()
    }

    private fun setupViews() {
        with(binding) {
            btnAuth.setOnClickListener {
                fragmentListener.openFragment(AuthorisationFragment(), false)
            }

            btnReg.setOnClickListener {
                showToast("РЕГИСТРАЦИЯ")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}