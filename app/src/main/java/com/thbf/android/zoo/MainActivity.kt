package com.thbf.android.zoo

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.animal_ticket.view.*

class MainActivity : AppCompatActivity() {

    var listOfAnimals = ArrayList<Animal>()
    var adapter:AnimalAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //load animals
        listOfAnimals.add(Animal("Baboon","Baboon live in big place with tree",R.drawable.baboon,false))
        listOfAnimals.add(Animal("Bulldog","Bulldog live in big place with tree",R.drawable.bulldog,false))
        listOfAnimals.add(Animal("Panda","Panda live in big place with tree",R.drawable.panda,true))
        listOfAnimals.add(Animal("Swallow Bird","Swallow Bird live in big place with tree",R.drawable.swallow_bird,true))
        listOfAnimals.add(Animal("White Tiger","White Tiger live in big place with tree",R.drawable.white_tiger,false))
        listOfAnimals.add(Animal("Zebra","Zebra live in big place with tree",R.drawable.zebra,true))

        adapter = AnimalAdapter(this, listOfAnimals)
        lvListAnimal.adapter = adapter
    }

    fun delete(index:Int){
        listOfAnimals.removeAt(index)
        adapter!!.notifyDataSetChanged()
    }

    fun add(index:Int){
        listOfAnimals.add(listOfAnimals[index])
        adapter!!.notifyDataSetChanged()
    }

    inner class AnimalAdapter:BaseAdapter {

        var listOfAnimals = ArrayList<Animal>()
        var context:Context?=null
        constructor(context: Context, listOfAnimal: ArrayList<Animal>):super(){
            this.listOfAnimals= listOfAnimal
            this.context=context
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val animal = listOfAnimals[position]
            if (animal.isKiller==true){
                var myView = View.inflate(context, R.layout.animal_ticket_killer, null)
                myView.tvNameDet.text = animal.name
                myView.tvDesDet.text = animal.des
                myView.ivAnimalImageDet.setImageResource(animal.image!!)
                myView.setOnClickListener {
                    var intent = Intent(context, AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image", animal.image!!)
                    context!!.startActivity(intent)
                }
                return myView
            }else {
                var myView = View.inflate(context, R.layout.animal_ticket, null)
                myView.tvNameDet.text = animal.name
                myView.tvDesDet.text = animal.des
                myView.ivAnimalImageDet.setImageResource(animal.image!!)
                myView.setOnClickListener {
                    var intent = Intent(context, AnimalInfo::class.java)
                    intent.putExtra("name",animal.name!!)
                    intent.putExtra("des",animal.des!!)
                    intent.putExtra("image", animal.image!!)
                    context!!.startActivity(intent)
                }
                return myView
            }

        }

        override fun getItem(position: Int): Any {
            return listOfAnimals[position]
        }

        override fun getItemId(position: Int): Long {
            return position.toLong()
        }

        override fun getCount(): Int {
            return listOfAnimals.size
        }

    }
}