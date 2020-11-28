package com.vogella.android.rxjava.instaclone.fragments;

import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.vogella.android.rxjava.instaclone.Post;
import com.vogella.android.rxjava.instaclone.fragments.PostFragment;

import java.util.List;

public class ProfileFragment extends PostFragment {

    @Override
    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(LIMIT);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if ( e != null) {
                    Log.e(TAG, "Error getting posts: " + e.getMessage(), e);
                    return;
                }
                allPosts.addAll(posts);
                postAdapter.notifyDataSetChanged();
            }
        });
    }
}
