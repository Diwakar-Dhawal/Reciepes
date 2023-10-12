package com.example.reciepes

import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.reciepes.databinding.ActivityCategoryBinding
import com.example.reciepes.databinding.ActivityRecipeBinding

class RecipeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecipeBinding
    var imgCrop=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImg)
        binding.title.text = intent.getStringExtra("title")

        binding.stepsData.text = intent.getStringExtra("des")
        var ing=intent.getStringExtra("ing")?.split("\n".toRegex())?.dropLastWhile { it.isEmpty() }?.toTypedArray()
        binding.time.text = ing?.get(0)

        for(i in 1 until ing!!.size)
        {
            binding.ingData.text=
                """${binding.ingData.text} 🟢 ${ing[i].trimIndent()} 
                    
                """.trimIndent()
        }
        binding.steps.background = null
        binding.steps.setTextColor(getColor(R.color.black))
        binding.steps.setOnClickListener {
            binding.steps.setBackgroundResource(R.drawable.btn_ing)
            binding.steps.setTextColor(getColor(R.color.white))
            binding.ingredients.setTextColor(getColor(R.color.black))
            binding.ingredients.background = null
            binding.stepsSv.visibility =View.VISIBLE
            binding.ingSv.visibility = View.GONE
        }
        binding.ingredients.setOnClickListener {
            binding.ingredients.setBackgroundResource(R.drawable.btn_ing)
            binding.ingredients.setTextColor(getColor(R.color.white))
            binding.steps.setTextColor(getColor(R.color.black))
            binding.steps.background = null
            binding.ingSv.visibility =View.VISIBLE
            binding.stepsSv.visibility = View.GONE
        }
        binding.fullScreen.setOnClickListener{
            if(imgCrop)
            {
                binding.itemImg.scaleType = ImageView.ScaleType.FIT_CENTER
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImg)
                binding.fullScreen.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
                binding.shades.visibility = View.GONE
                imgCrop = !imgCrop
            }
            else
            {
                binding.itemImg.scaleType = ImageView.ScaleType.CENTER_CROP
                Glide.with(this).load(intent.getStringExtra("img")).into(binding.itemImg)
                binding.fullScreen.setColorFilter(null)
                binding.shades.visibility = View.VISIBLE
                imgCrop = !imgCrop
            }

        }
        binding.backBtn.setOnClickListener {
            finish()
        }
    }
}