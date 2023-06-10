package com.example.FoodIepal.Dialogs

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import com.example.FoodIepal.databinding.DialogSendimageLayoutBinding
import java.io.ByteArrayOutputStream

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
            val name = arguments?.getString("name").toString()
            val prep = arguments?.getString("prep").toString()
            val Kkal = arguments?.getInt("Kkal")
            val time = arguments?.getInt("time")
            val photo: ByteArray = getBytesFromImageView(image)!!
            DialogSetItems.newInstance(name, prep, Kkal!!, time!!, photo).show(parentFragmentManager, "")
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

    private fun getBytesFromImageView(imageView: ImageView): ByteArray? {
        val drawable = imageView.drawable ?: return null
        val bitmap = (drawable as? BitmapDrawable)?.bitmap ?: return null

        val scaledBitmap = Bitmap.createScaledBitmap(bitmap, 300, 200, true)

        val outputStream = ByteArrayOutputStream()
        scaledBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)

        return outputStream.toByteArray()
    }


    companion object{
        fun newInstance(name: String, prep: String, Kkal: Int, time: Int): DialogSetPhoto {
            val args = Bundle()
            args.putString("name", name)
            args.putString("prep", prep)
            args.putInt("Kkal", Kkal)
            args.putInt("time", time)
            val fragment = DialogSetPhoto()
            fragment.arguments = args
            return fragment
        }
    }
}