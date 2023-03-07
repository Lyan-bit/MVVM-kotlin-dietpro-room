package com.example.dietpro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.dietpro.viewmodel.UserCRUDViewModel
import com.google.android.material.tabs.TabLayout

class MainActivity : AppCompatActivity(), ListMealFragment.OnListMealFragmentInteractionListener, ListUserFragment.OnListUserFragmentInteractionListener  {
        
	private lateinit var model: MealCRUDViewModel
	private lateinit var modelUser: UserCRUDViewModel
       	
       	override fun onCreate(savedInstanceState: Bundle?) {
       	      super.onCreate(savedInstanceState)
       	      setContentView(R.layout.activity_main)
       	
       	      val myPagerAdapter = PagerAdapter(this, supportFragmentManager)
       	      val viewpager: ViewPager = findViewById(R.id.view_pager)
       	      viewpager.adapter = myPagerAdapter
       	      val tabs: TabLayout = findViewById(R.id.tabs)
       	      tabs.setupWithViewPager(viewpager)
       	      model = MealCRUDViewModel.getInstance(this)
			modelUser = UserCRUDViewModel.getInstance(this)
       	}
       	
       	override fun onListMealFragmentInteraction(item: MealEntity) {
       		  model.setSelectedMeal(item)
       	}
       		 	       	
       	override fun onListUserFragmentInteraction(item: UserVO) {
       		  modelUser.setSelectedUser(item)
       	}
       		 	       	

    }
