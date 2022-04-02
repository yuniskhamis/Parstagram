package com.yunis.parstagram

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.*
import com.yunis.parstagram.fragments.ComposeFragment
import com.yunis.parstagram.fragments.FeedFragment
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         //queryPosts()
        val fragmentManager: FragmentManager = supportFragmentManager


        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            item ->
            var fragmentToShow: Fragment? = null
            when(item.itemId){
                R.id.action_home-> {
                   fragmentToShow = FeedFragment()
                }
                R.id.action_compose ->{
                    fragmentToShow = ComposeFragment()
                }
                R.id.action_profile->{
                    //fragmentToShow = ProfileFragment()
                }
            }


            if (fragmentToShow != null) {
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }

            true
        }

        // Set default selection
        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home

        val logOutButton = findViewById<Button>(R.id.btnLogout)
        logOutButton.setOnClickListener {
            // Launch camera to let user take picture
            ParseUser.logOut()
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }
    }


    fun queryPosts(){
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USER)
        query.findInBackground { posts, e ->
            if (e != null) {
                // Something has went wrong
                Log.e(TAG, "Error fetching posts")
            } else {
                if (posts != null) {
                    for (post in posts) {
                        Log.i(
                            TAG,
                            "Post: " + post.getDescription() + " , username: " + post.getUser()?.username
                        )
                    }
                }
            }
        }
    }
    companion object {
        const val TAG = "MainActivity"
    }
}