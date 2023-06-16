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
import androidx.fragment.app.activityViewModels
import com.example.FoodIepal.Utils.DataModel
import com.example.FoodIepal.databinding.DialogSendimageLayoutBinding
import java.io.ByteArrayOutputStream

class DialogSetPhoto : DialogFragment() {
    private lateinit var binding: DialogSendimageLayoutBinding
    private lateinit var image: ImageView
    private val dataModel: DataModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DialogSendimageLayoutBinding.inflate(inflater)

        image = binding.image

        onClick()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(1100, 1000)
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

            dataModel.image.value = getBytesFromImageView(image)

            DialogSetItems.newInstance().show(parentFragmentManager, "")
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

        val outputStream = ByteArrayOutputStream()

        val maxSizeBytes = 1024 * 1024
        val quality = 100

        val scaledBitmap = if (bitmap.byteCount > maxSizeBytes) {
            val scale = kotlin.math.sqrt(maxSizeBytes.toDouble() / bitmap.byteCount.toDouble()).toFloat()
            val scaledWidth = (bitmap.width * scale).toInt()
            val scaledHeight = (bitmap.height * scale).toInt()
            Bitmap.createScaledBitmap(bitmap, scaledWidth, scaledHeight, false)
        } else {
            bitmap
        }

        scaledBitmap.compress(Bitmap.CompressFormat.PNG, quality, outputStream)

        return outputStream.toByteArray()
    }


    companion object {
        fun newInstance(): DialogSetPhoto = DialogSetPhoto()
    }
}