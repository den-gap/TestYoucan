package ru.dengap.testyoucan.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.airbnb.mvrx.MavericksView
import com.airbnb.mvrx.fragmentViewModel
import com.airbnb.mvrx.sample.utils.viewBinding
import com.airbnb.mvrx.withState
import kotlinx.android.synthetic.main.test_toast.*
import kotlinx.android.synthetic.main.test_toast.view.*
import ru.dengap.testyoucan.R
import ru.dengap.testyoucan.databinding.FragmentMainBinding

class MainFragment : Fragment(), MavericksView {
    private val binding: FragmentMainBinding by viewBinding()
    private val viewModel: TimeViewModel by fragmentViewModel()
//    private val bindingToast: TestToastBinding by viewBinding()

    override fun invalidate() = withState(viewModel) { state ->
        binding.hoursTV.text = viewModel.hourToText(state.hh)
        binding.minutesTV.text = viewModel.minToText(state.mm)
        binding.secondsTV.text = viewModel.secToText(state.ss)
        binding.button.setOnClickListener {
            getToast(viewModel.curTimeText(state)).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private fun getToast(text: String): Toast {
        val inflater = layoutInflater
        val layout: ViewGroup =
            inflater.inflate(R.layout.test_toast, test_toast_container) as ViewGroup
        layout.toast_text.text = text
        return Toast(requireContext()).apply {
            duration = Toast.LENGTH_LONG
            view = layout
        }
    }
}