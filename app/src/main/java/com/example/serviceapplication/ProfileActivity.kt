package com.example.serviceapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import com.example.serviceapplication.databinding.ActivityProfileBinding
import com.example.serviceapplication.model.UserModel
import com.example.serviceapplication.model.VehicleModel

class ProfileActivity : AppCompatActivity() {

    lateinit var binding : ActivityProfileBinding
    lateinit var user : UserModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater);
        setContentView(binding.root)

        var users = SQLiteDB(applicationContext).readData()
        user = users.get(users.size-1)

        var vehicles = SQLiteDB(applicationContext).readVehicleNumers(user.username!!)
        if(!vehicles.isEmpty())
            binding.lstVehicle.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,vehicles!!)
        else
            binding.txtStatus.setText("No Vehicles Added Yet")

        binding.txtUsername.setText(user.username)
        binding.txtFirstname.setText(user.firstname)
        binding.txtLastname.setText(user.lastname)
        binding.txtAddress.setText(user.address)
        binding.txtTp.setText(user.tel.toString())

        binding.btnEdit.setOnClickListener{
            val view = layoutInflater.inflate(R.layout.layout_update_profile, null)
            val builder = android.app. AlertDialog.Builder(this).setView(view)

            val txtUsername : EditText = view.findViewById(R.id.txt_username)
            val txtFirstname : EditText = view.findViewById(R.id.txt_firstname)
            val txtLastname : EditText = view.findViewById(R.id.txt_lastname)
            val txtAddress : EditText = view.findViewById(R.id.txt_address)
            val txtTp : EditText = view.findViewById(R.id.txt_tp)

            txtUsername.setText(binding.txtUsername.text.toString())
            txtFirstname.setText(binding.txtFirstname.text.toString())
            txtLastname.setText(binding.txtLastname.text.toString())
            txtAddress.setText(binding.txtAddress.text.toString())
            txtTp.setText(binding.txtTp.text.toString())

            val alertDialog = builder.create()

            builder.setNegativeButton("Update") { dialog, which ->
                if(txtUsername.text.length==0) {
                    Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
                }
                else if(txtFirstname.text.length==0) {
                    Toast.makeText(this, "First Name cannot be empty", Toast.LENGTH_SHORT).show()
                }
                else if(txtLastname.text.length==0) {
                    Toast.makeText(this, "Last Name cannot be empty", Toast.LENGTH_SHORT).show()
                }
                else if(txtAddress.text.length==0) {
                    Toast.makeText(this, "Address cannot be empty", Toast.LENGTH_SHORT).show()
                }
                else if(txtTp.text.length==0) {
                    Toast.makeText(this, "Telephone Number cannot be empty", Toast.LENGTH_SHORT).show()
                }
                else {
                    this.user.username = txtUsername.text.toString()
                    binding.txtUsername.setText(user.username.toString())
                    this.user.firstname = txtFirstname.text.toString()
                    binding.txtFirstname.setText(user.firstname)
                    this.user.lastname = txtLastname.text.toString()
                    binding.txtLastname.setText(user.lastname)
                    this.user.address = txtAddress.text.toString()
                    binding.txtAddress.setText(user.address)
                    this.user.tel = txtTp.text.toString().toInt()
                    binding.txtTp.setText(user.tel.toString())
                    alertDialog.dismiss()
                    Toast.makeText(this, "Account Successfully Updated", Toast.LENGTH_SHORT).show()
                }
            }
            builder.setPositiveButton("Cancel") { dialog, which ->
                alertDialog.dismiss()
            }
            builder.setOnDismissListener {
                SQLiteDB(this).insertData(user)
                FirebaseDB().setDataUserDB(user)
            }

            builder.show()
        }

        binding.btnAddVehicle.setOnClickListener{
            val view = layoutInflater.inflate(R.layout.layout_add_vehicle, null)
            val builder = android.app. AlertDialog.Builder(this).setView(view)

            val txtRegno : EditText = view.findViewById(R.id.txt_regno)
            val txtType : EditText = view.findViewById(R.id.txt_type)
            val txtChassyno : EditText = view.findViewById(R.id.txt_chassyno)

            val alertDialog = builder.create()
            builder.setNeutralButton("Add") { dialog, which ->
                if (txtRegno.text.length==0){
                    Toast.makeText(this, "Register Number cannot be empty", Toast.LENGTH_SHORT).show()
                }
                else if (txtType.text.length==0) {
                    Toast.makeText(this, "Vehicle Type cannot be empty", Toast.LENGTH_SHORT).show()
                }
                else if (txtChassyno.text.length==0) {
                    Toast.makeText(this, "Chassy Number cannot be empty", Toast.LENGTH_SHORT).show()
                }
                else {
                    var newvehicle : VehicleModel = VehicleModel(txtRegno.text.toString(),txtType.text.toString(),txtChassyno.text.toString())
                    this.user.vehicles!!.add(newvehicle)
                    FirebaseDB().setDataUserDB(user)
                    SQLiteDB(this).insertVehicleData(user.username.toString(), newvehicle)
                    var vehicles = SQLiteDB(applicationContext).readVehicleNumers(user.username!!)
                    binding.lstVehicle.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,vehicles!!)
                    if(vehicles.size!=0){
                        binding.txtStatus.setText("")
                        Toast.makeText(this, "New Vehicle is added", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            builder.setNegativeButton("Cancel") { dialog, which ->
                alertDialog.dismiss()
            }
            builder.setOnDismissListener {
                binding.txtStatus.setText("")
            }
            builder.show()
        }

    }
}