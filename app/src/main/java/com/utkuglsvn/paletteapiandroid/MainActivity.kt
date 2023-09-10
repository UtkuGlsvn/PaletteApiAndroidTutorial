package com.utkuglsvn.paletteapiandroid

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.drawable.VectorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.palette.graphics.Palette

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //img bitmap decode
        val myBitmap = bitmapDecode(R.drawable.ic_action_name) //image
        val vectorToBitmap = vectorToBitmap(R.drawable.baseline_group_24) //svg
        //create palette
        val palette = generatePalette(myBitmap)
        val palette2 = generatePalette(vectorToBitmap)
        //extract color
        extractColor(palette)
        //other properties
        palette2.vibrantSwatch?.let {
            Log.i("rgb color","${it.rgb}")
            Log.i("title text color","${it.titleTextColor}")
            Log.i("body text color","${it.bodyTextColor}")
        }

    }

    private fun vectorToBitmap(@DrawableRes vectorDrawableResourceId: Int,): Bitmap {
        val vectorDrawable = ContextCompat.getDrawable(this, vectorDrawableResourceId) as VectorDrawable
        val bitmap = Bitmap.createBitmap(vectorDrawable.intrinsicWidth, vectorDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        vectorDrawable.setBounds(0, 0, canvas.width, canvas.height)
        vectorDrawable.draw(canvas)
        return bitmap
    }

    private fun bitmapDecode(drawable: Int) =
        BitmapFactory.decodeResource(resources, drawable)

    private fun generatePalette(bitmap: Bitmap) =
        Palette.from(bitmap).generate()

    private fun extractColor(palette: Palette) {
        Log.i("vibrant", "${palette.vibrantSwatch}")
        Log.i("lightVibrantSwatch", "${palette.lightVibrantSwatch}")
        Log.i("darkVibrantSwatch", "${palette.darkVibrantSwatch}")
        Log.i("mutedSwatch", "${palette.mutedSwatch}")
        Log.i("lightMutedSwatch", "${palette.lightMutedSwatch}")
        Log.i("darkMutedSwatch", "${palette.darkMutedSwatch}")
    }
}