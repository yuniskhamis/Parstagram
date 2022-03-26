package com.yunis.parstagram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import com.parse.FindCallback
import com.parse.ParseException
import com.parse.ParseQuery

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
         queryPosts()


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