package com.yunis.parstagram.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.parse.ParseQuery
import com.yunis.parstagram.MainActivity
import com.yunis.parstagram.Post
import com.yunis.parstagram.PostAdapter
import com.yunis.parstagram.R

open class FeedFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: PostAdapter
    var allPosts:MutableList<Post> = mutableListOf()
    lateinit var swipeContainer: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.postRecyclerView)
        adapter = PostAdapter(requireContext(), allPosts)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        swipeContainer = view.findViewById(R.id.swipeContainer)
        swipeContainer.setOnRefreshListener {
            queryPosts()
        }
    }


    open fun queryPosts(){
        val query: ParseQuery<Post> = ParseQuery.getQuery(Post::class.java)
        query.include(Post.KEY_USER)
        query.limit = 20
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
                    allPosts.addAll(posts)
                    //adapter.notifyDataSetChanged()
                    swipeContainer.setRefreshing(false)

                }
            }
        }
    }
    companion object{
        const val TAG = "FeedFragment"
    }
}