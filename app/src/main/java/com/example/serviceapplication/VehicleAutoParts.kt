package com.example.serviceapplication

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.serviceapplication.adapter.AutoPartsListAdapter
import com.example.serviceapplication.model.AutoParts
import com.example.serviceapplication.model.ShopModel
import kotlinx.android.synthetic.main.activity_vehicle_auto_parts.*

class VehicleAutoParts : AppCompatActivity(), AutoPartsListAdapter.AutoPartsListClickListener {

    private var itemInTheCartList: MutableList<AutoParts?>? = null
    private var totalItemInCartCount = 0
    private var autoPartsList: List<AutoParts?>? = null
    private var autoPartsListAdapter: AutoPartsListAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vehicle_auto_parts)

        val shopModel = intent?.getParcelableExtra<ShopModel>("ShopModel")

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setTitle(shopModel?.name)
        actionBar?.setSubtitle(shopModel?.address)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        autoPartsList = shopModel?.autoParts

        initRecyclerView(autoPartsList)
        btn_checkout.setOnClickListener {
            if(itemInTheCartList != null && itemInTheCartList!!.size <= 0){
                Toast.makeText(this@VehicleAutoParts, "Please add some items in cart", Toast.LENGTH_LONG).show()
            }
            else{
                shopModel?.autoParts = itemInTheCartList
                val intent = Intent(this@VehicleAutoParts, PlaceYourOrderActivity::class.java)
                intent.putExtra("ShopModel", shopModel)
                startActivityForResult(intent, 1000)
            }
        }
    }
    private fun initRecyclerView(autoParts: List<AutoParts?>?){
        autopartsRecyclerView.layoutManager = GridLayoutManager(this, 2)
        autoPartsListAdapter = AutoPartsListAdapter(autoParts, this)
        autopartsRecyclerView.adapter = autoPartsListAdapter
    }

    override fun addtoCartClickListener(autoParts: AutoParts) {
        if (itemInTheCartList==null){
            itemInTheCartList = ArrayList()
        }
        itemInTheCartList?.add(autoParts)
        totalItemInCartCount = 0
        for (autoParts in itemInTheCartList!!){
            totalItemInCartCount = totalItemInCartCount + autoParts?.totalInCart!!
        }
        btn_checkout.text = "Checkout (" +  totalItemInCartCount + ") Items"
    }

    override fun updateCartClickListener(autoParts: AutoParts) {
        val index = itemInTheCartList!!.indexOf(autoParts)
        itemInTheCartList?.removeAt(index)
        itemInTheCartList?.add(autoParts)
        totalItemInCartCount = 0
        for (autoParts in itemInTheCartList!!){
            totalItemInCartCount = totalItemInCartCount + autoParts?.totalInCart!!
        }
        btn_checkout.text = "Checkout (" +  totalItemInCartCount + ") Items"
    }

    override fun removeFromCartClickListener(autoParts: AutoParts) {
        if (itemInTheCartList!!.contains(autoParts)){
            itemInTheCartList?.remove(autoParts)
            totalItemInCartCount = 0
            for (autoParts in itemInTheCartList!!){
                totalItemInCartCount = totalItemInCartCount + autoParts?.totalInCart!!
            }
            btn_checkout.text = "Checkout (" +  totalItemInCartCount + ") Items"
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> finish()
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000 && resultCode == RESULT_OK){
            finish()
        }
    }
}