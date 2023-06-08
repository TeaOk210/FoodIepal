package com.example.FoodIepal.Dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.FoodIepal.databinding.DialogSenditemsLayoutBinding

class DialogSetItems: DialogFragment() {
    private lateinit var binding: DialogSenditemsLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSenditemsLayoutBinding.inflate(inflater)

        onClick()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(1100,900)
    }

    private fun onClick() {
        binding.textView17.setOnClickListener {
            dismiss()
        }
        binding.textView16.setOnClickListener {

        }
        binding.itemAdd.setOnClickListener {
//            val linearLayoutItems = binding.LinearLayoutItems
//            val scrollView = binding.scroll
//
//            val newRelativeLayout = RelativeLayout(requireContext())
//            val newEditText = EditText(requireContext())
//            val newImageView = ImageView(requireContext())
//
//            newRelativeLayout.layoutParams = LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//            )
//
//            newEditText.layoutParams = RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.MATCH_PARENT,
//                RelativeLayout.LayoutParams.MATCH_PARENT
//            ).apply {
//                setMargins(40, 0, 0, 0)
//            }
//
//            newImageView.layoutParams = RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.WRAP_CONTENT,
//                RelativeLayout.LayoutParams.WRAP_CONTENT
//            ).apply {
//                addRule(RelativeLayout.ALIGN_PARENT_END)
//                addRule(RelativeLayout.ALIGN_PARENT_TOP)
//                setMargins(0, 0, 0, 20)
//
//            }
//
//            newRelativeLayout.addView(newImageView)
//            newRelativeLayout.addView(newEditText)
//
//            linearLayoutItems.addView(newRelativeLayout)
//
//            scrollView.post {
//                scrollView.scrollTo(0, scrollView.bottom)
//            }
//
//            binding.itemAdd.visibility = View.GONE
        }

    }
}