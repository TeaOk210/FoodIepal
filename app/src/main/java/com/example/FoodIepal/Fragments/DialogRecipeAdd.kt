package com.example.FoodIepal.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.example.FoodIepal.databinding.DialogSendimageLayoutBinding
import com.example.FoodIepal.databinding.DialogSendkkalLayoutBinding
import kotlin.properties.Delegates

class DialogRecipeAdd : DialogFragment() {
    private lateinit var binding: DialogSendimageLayoutBinding
    private lateinit var binding2: DialogSendkkalLayoutBinding
    private lateinit var image: ImageView
    private var kkal by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSendimageLayoutBinding.inflate(inflater)

        image = binding.image

        onClick()

        binding
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(1100, 900)
    }

    private fun onClick() {
        image.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, 1)
        }
        binding.textView17.setOnClickListener {
            dismiss()
        }
        binding.textView16.setOnClickListener {
            chandeGialogs()
        }
    }

    @SuppressLint("UseGetLayoutInflater")
    private fun chandeGialogs() {
        val inflater = LayoutInflater.from(requireContext())
        val newBinding = DialogSendkkalLayoutBinding.inflate(inflater)

        val parent = binding.root.parent as ViewGroup
        parent.removeView(binding.root)
        parent.addView(newBinding.root)
        binding2 = DialogSendkkalLayoutBinding.inflate(inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            val selectedPhotoURL = data?.data
            image.setImageURI(selectedPhotoURL)
        }
    }
}