package ru.example.wsrfood.ui.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import ru.example.wsrfood.ui.MainActivity
import ru.example.wsrfood.viewmodel.core.BaseViewModel

abstract class BaseFragment<T : BaseViewModel, VB : ViewBinding>
    : Fragment() {

    // Переменная для вью модели
    protected abstract val viewModel: T

    // Внутренняя переменная для биндинга
    private var _binding: VB? = null
    val binding get() = _binding!!

    // Инициализация биндинга
    abstract fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    // Переменная MainActivity для использования во фрагментах
    protected val mainActivity: MainActivity get() = requireActivity() as MainActivity

     /**
     * Переменная для указания видимости навигации. При значении false делает навигацию невидимой.
     */
    protected var isNavigationEnabled: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateViewBinding(inflater, container)
        return binding.root
    }

    abstract fun setupViews()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}