package com.example.kjh_stock01.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kjh_stock01.ui.home.post.JsonPlaceHolderPostApi;
import com.example.kjh_stock01.ui.home.post.Post;
import com.example.kjh_stock01.ui.home.post.PostItemClicked;
import com.example.kjh_stock01.ui.home.post.PostListAdapter;
import com.example.kjh_stock01.R;
import com.example.kjh_stock01.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment implements PostItemClicked {

    private FragmentHomeBinding binding;
    RecyclerView recyclerView;
    PostListAdapter mAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);

        recyclerView = root.findViewById(R.id.home_recylerView);
        fetch_data();

        mAdapter = new PostListAdapter(this);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    private void fetch_data() {
        Log.d(getClass().getName(), "fetch_date()");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderPostApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderPostApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPost();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    Log.d(getClass().getName(), "Code : " + response.code());
                    return;
                }

                List<Post> posts = response.body();
                ArrayList<Post> postArrayList = new ArrayList<Post>();

                for (Post post : posts) {
                    String content = "";
                    content += "ID : " + post.getId() + "\n";
                    content += "User ID : " + post.getUserId() + "\n";
                    content += "Title : " + post.getTitle() + "\n";
                    content += "Text : " + post.getText() + "\n";

//                    Log.d(getClass().getName(), content);

                    Post postitem = new Post(
                        "ID : " + post.getId(),
                        "UserId : " + post.getUserId(),
                        "Title : " + post.getTitle(),
                        "Text : " + post.getText()
                    );
                    postArrayList.add(postitem);
                }
                mAdapter.updateNews(postArrayList);
            }
            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.d(getClass().getName(), t.getMessage().toString());
            }
        });
//        MySingleton.getInstance(getContext()).addToRequestQueue();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onItemclicked(Post post) {

    }
}