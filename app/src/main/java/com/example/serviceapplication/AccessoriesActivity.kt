package com.example.serviceapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.serviceapplication.adapter.ShopListAdapter
import com.example.serviceapplication.model.ShopModel
import com.google.gson.Gson
import java.io.*

class AccessoriesActivity : AppCompatActivity(), ShopListAdapter.ShopListClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_accessories)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setTitle("Auto-Parts Shop List")

        val shopModel = getShopData()
        initRecyclerView(shopModel)
    }

    private fun initRecyclerView(shopList: List<ShopModel?>?){
        val recycler_view_shop = findViewById<RecyclerView>(R.id.recycler_view_shop)
        recycler_view_shop.layoutManager = LinearLayoutManager(this)
        val adapter = ShopListAdapter(shopList, this)
        recycler_view_shop.adapter = adapter
    }

    private fun getShopData(): List<ShopModel?>? {
        val inputStream: InputStream = resources.openRawResource(R.raw.shop)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        try {
            val reader: Reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1){
                writer.write(buffer, 0, n)
            }

        }catch (e: Exception){}
        val jsonStr: String = writer.toString()
        val gson = Gson()
        val shopModel = gson.fromJson<Array<ShopModel>>(jsonStr, Array<ShopModel>::class.java).toList()
        return shopModel
    }

    override fun onItemClick(shopModel: ShopModel) {
        val intent = Intent (this@AccessoriesActivity, VehicleAutoParts::class.java)
        intent.putExtra("ShopModel", shopModel)
        startActivity(intent)
    }
}