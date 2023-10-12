package com.example.reciepes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.reciepes.databinding.ActivityCategoryBinding
import com.example.reciepes.databinding.ActivityHomeBinding
import com.example.reciepes.databinding.CategoryRvBinding

class CategoryActivity : AppCompatActivity() {
    private lateinit var rvAdapter: CategoryAdapter
    private lateinit var dataList: ArrayList<Recipe>
    private  val binding by lazy {
        ActivityCategoryBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.title.text = intent.getStringExtra("TITLE")
        setUpRecyclerView()
        binding.backBtn.setOnClickListener{
            finish()
        }
    }
    private fun setUpRecyclerView() {
        dataList = ArrayList()
        binding.categoryRv.layoutManager = LinearLayoutManager(this)
        var db = Room.databaseBuilder(this@CategoryActivity,appDatabase::class.java,"db_name")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .createFromAsset("recipe.db")
            .build()
        var daoObject = db.getDao()
        var recipes = daoObject.getAll()
        for(i in recipes!!.indices){
            if(recipes[i]!!.category.contains(intent.getStringExtra("CATEGORY")!!))
            {
                dataList.add(recipes[i]!!)
            }
            rvAdapter = CategoryAdapter(dataList,this)
            binding.categoryRv.adapter =rvAdapter
        }
    }
}