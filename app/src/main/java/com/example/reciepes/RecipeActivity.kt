package com.example.reciepes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.reciepes.databinding.ActivityCategoryBinding
import com.example.reciepes.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var imgCrop=true
        binding.fullScreen.setOnClickListener{
            if(imgCrop)
            {
                binding.itemImg.scaleType = ImageView.ScaleType.FIT_CENTER
                imgCrop = !imgCrop
            }
            else
            {
                binding.itemImg.scaleType = ImageView.ScaleType.CENTER_CROP
                imgCrop = !imgCrop
            }
        }
    }
}