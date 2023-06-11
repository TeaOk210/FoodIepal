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

//    private fun cropImage(bitmap: Bitmap, width: Int, height: Int): Bitmap {
//        val imageWidth = bitmap.width
//        val imageHeight = bitmap.height
//
//        val cropWidth = if (imageWidth >= width) width else imageWidth
//        val cropHeight = if (imageHeight >= height) height else imageHeight
//
//        val cropX = (imageWidth - cropWidth) / 2
//        val cropY = (imageHeight - cropHeight) / 2
//
//        val offsetX = (width - cropWidth) / 2
//        val offsetY = (height - cropHeight) / 2
//
//        val croppedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
//        val canvas = Canvas(croppedBitmap)
//        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
//
//        canvas.drawBitmap(bitmap, Rect(cropX, cropY, cropX + cropWidth, cropY + cropHeight), Rect(offsetX, offsetY, offsetX + cropWidth, offsetY + cropHeight), paint)
//
//        return croppedBitmap
//    }

    private fun getBytesFromImageView(imageView: ImageView): ByteArray? {
        val drawable = imageView.drawable ?: return null
        val bitmap = (drawable as? BitmapDrawable)?.bitmap ?: return null

        val croppedBitmap = Bitmap.createScaledBitmap(bitmap, 1200, 1200, false)
//        val croppedBitmap = cropImage(bitmap, binding.image.width, binding.image.height)

        val outputStream = ByteArrayOutputStream()
        croppedBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)

        return outputStream.toByteArray()
    }

    companion object {
        fun newInstance(): DialogSetPhoto = DialogSetPhoto()
    }
}