package com.example.mad_lab_10;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    ListView rssListView;
    ProgressBar progressBar;
    ArrayList<String> titles = new ArrayList<>();
    ArrayList<String> links = new ArrayList<>();
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final Handler uiHandler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rssListView = findViewById(R.id.rssListView);
        progressBar = findViewById(R.id.progressBar);

        new FetchRSSFeed(this).fetchRSS("https://feeds.bbci.co.uk/news/technology/rss.xml"); // BBC Tech News

        rssListView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(links.get(position)));
            startActivity(intent);
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }

    private static class FetchRSSFeed {
        private final WeakReference<MainActivity> activityRef;

        public FetchRSSFeed(MainActivity activity) {
            this.activityRef = new WeakReference<>(activity);
        }

        public void fetchRSS(String urlString) {
            MainActivity activity = activityRef.get();
            if (activity == null) return;

            activity.progressBar.setVisibility(View.VISIBLE);

            activity.executorService.execute(() -> {
                ArrayList<String> fetchedTitles = new ArrayList<>();
                ArrayList<String> fetchedLinks = new ArrayList<>();

                try {
                    URL url = new URL(urlString);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = connection.getInputStream();

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = factory.newPullParser();
                    parser.setInput(inputStream, null);

                    boolean insideItem = false;
                    String title = "", link = "";
                    int eventType = parser.getEventType();

                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        String tagName = parser.getName();
                        if (eventType == XmlPullParser.START_TAG) {
                            if ("item".equalsIgnoreCase(tagName)) insideItem = true;
                            else if (insideItem && "title".equalsIgnoreCase(tagName)) title = parser.nextText();
                            else if (insideItem && "link".equalsIgnoreCase(tagName)) link = parser.nextText();
                        } else if (eventType == XmlPullParser.END_TAG && "item".equalsIgnoreCase(tagName)) {
                            fetchedTitles.add(title);
                            fetchedLinks.add(link);
                            insideItem = false;
                        }
                        eventType = parser.next();
                    }

                    inputStream.close();
                } catch (Exception e) {
                    Log.e("RSS Fetch Error", "Error fetching RSS feed", e);
                }

                activity.uiHandler.post(() -> {
                    MainActivity activityPost = activityRef.get();
                    if (activityPost == null) return;

                    activityPost.progressBar.setVisibility(View.GONE);
                    activityPost.titles.clear();
                    activityPost.links.clear();
                    activityPost.titles.addAll(fetchedTitles);
                    activityPost.links.addAll(fetchedLinks);

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(activityPost, android.R.layout.simple_list_item_1, activityPost.titles);
                    activityPost.rssListView.setAdapter(adapter);
                });
            });
        }
    }
}
