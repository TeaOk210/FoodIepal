package com.example.FoodIepal.Dialogs

import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.example.FoodIepal.databinding.DialogSendimageLayoutBinding

class DialogSetPhoto : DialogFragment() {
    private lateinit var binding: DialogSendimageLayoutBinding
    private lateinit var image: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogSendimageLayoutBinding.inflate(inflater)

        image = binding.image

        onClick()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(1100,900)
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
            dismiss()
            DialogSetDetails().show(parentFragmentManager, "")
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && data != null) {
            val selectedPhotoURL = data.data
            image.setImageURI(selectedPhotoURL)
        }
    }
}