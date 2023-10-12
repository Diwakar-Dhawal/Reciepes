package com.example.reciepes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.reciepes.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var rvAdapter: PopularAdapter
    private lateinit var dataList: ArrayList<Recipe>
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        binding.search.setOnClickListener{
            startActivity(Intent(this,SearchActivity::class.java))
        }
        binding.salad.setOnClickListener{
            var myIntent =Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITLE","Salad")
            myIntent.putExtra("CATEGORY","Salad")
            startActivity(myIntent)
        }
        binding.main.setOnClickListener{
            var myIntent =Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITLE","Main Dish")
            myIntent.putExtra("CATEGORY","Dish")
            startActivity(myIntent)
        }
        binding.drink.setOnClickListener{
            var myIntent =Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITLE","Drinks")
            myIntent.putExtra("CATEGORY","Drinks")
            startActivity(myIntent)
        }
        binding.desserts.setOnClickListener{
            var myIntent =Intent(this@HomeActivity,CategoryActivity::class.java)
            myIntent.putExtra("TITLE","Desserts")
            myIntent.putExtra("CATEGORY","Desserts")
            startActivity(myIntent)
        }



    }


    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.rvPopular.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        var db = Room.databaseBuilder(this@HomeActivity,appDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject = db.getDao()
        var recipes = daoObject.getAll()
        for(i in recipes!!.indices){
            if(recipes[i]!!.category.contains("Popular"))
            {
                dataList.add(recipes[i]!!)
            }
            rvAdapter = PopularAdapter(dataList,this)
            binding.rvPopular.adapter =rvAdapter
        }
    }
}