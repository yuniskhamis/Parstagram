package com.yunis.parstagram.fragments

import android.util.Log
import com.parse.ParseQuery
import com.parse.ParseUser
import com.yunis.parstagram.MainActivity
import com.yunis.parstagram.Post

class ProfileFragment: FeedFragment() {
    override fun queryPosts() {
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USER)
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser())
        query.addDescendingOrder("createdAt")

        // Only return the most recent 20 posts
        query.limit = 20

        query.findInBackground { posts, e ->
            if (e != null) {
                e.printStackTrace()
            } else {
                if (posts != null) {
                    for (p in posts) {
                        Log.i(TAG, "done: " + p.getDescription()+" usernamre="+p.getUser()?.username)
                    }
                    allPosts.addAll(posts)
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }
    companion object{
        const val TAG = "ProfileFragment"
    }
}