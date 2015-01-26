package com.ea7jmf.instagramviewer;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.ea7jmf.instagramviewer.models.InstagramComment;
import com.ea7jmf.instagramviewer.models.InstagramPhoto;
import com.ea7jmf.instagramviewer.models.InstagramUser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class PhotosActivity extends ActionBarActivity {

    private static final String CLIENT_ID = "39e68d48b9dd403bb682d6acb9e89a62";
    private static final String INSTAGRAM_API = "https://api.instagram.com/v1";
    private static final String POPULAR_ENDPOINT = "/media/popular";

    private ListView lvPhotos;
    private SwipeRefreshLayout swipeContainer;

    private ArrayList<InstagramPhoto> photos = new ArrayList<>();
    private InstagramPhotoAdapter aPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        configureListView();

        fetchPopularPhotos();
    }

    private void configureListView() {

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchPopularPhotos();
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        aPhotos = new InstagramPhotoAdapter(this, photos);
        lvPhotos.setAdapter(aPhotos);
    }

    private void fetchPopularPhotos() {
        String requestUrl = INSTAGRAM_API + POPULAR_ENDPOINT + "?client_id=" + CLIENT_ID;

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(requestUrl, new JsonHttpResponseHandler() {
            // define success and failure

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.i("fetchPopularPhotos", response.toString());

                JSONArray photosJSON = null;
                try {
                    photosJSON = response.getJSONArray("data");
                    photos.clear();

                    for (int i = 0; i < photosJSON.length(); i++) {
                        JSONObject photoJSON = photosJSON.getJSONObject(i);
                        InstagramPhoto photo = InstagramPhoto.parse(photoJSON);

                        // Parse and set username
                        photo.setUser(InstagramUser.parse(photoJSON.getJSONObject("user")));

                        // Parse comments
                        ArrayList<InstagramComment> comments = photo.getComments();
                        JSONArray commentsJSON = photoJSON.getJSONObject("comments").getJSONArray("data");
                        for (int j = 0; j < commentsJSON.length(); j++) {
                            comments.add(InstagramComment.parse(commentsJSON.getJSONObject(j)));
                        }

                        // Add to photos list
                        photos.add(photo);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                    swipeContainer.setRefreshing(false);
                }
                aPhotos.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Log.d("fetchPopularPhotos", errorResponse.toString());
                swipeContainer.setRefreshing(false);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
