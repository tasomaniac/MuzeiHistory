package com.tasomaniac.muzei.history.main;

import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.squareup.picasso.Picasso;
import com.tasomaniac.muzei.history.BaseActivity;
import com.tasomaniac.muzei.history.R;
import com.tasomaniac.muzei.history.data.Injector;
import com.tasomaniac.muzei.history.data.artwork.Artwork;
import com.tasomaniac.muzei.history.helper.TransitionHelper;
import com.tasomaniac.muzei.history.widget.OffsetDecoration;

import javax.inject.Inject;
import java.util.List;

import nl.qbusict.cupboard.Cupboard;
import nl.qbusict.cupboard.QueryResultIterable;

public class MainActivity extends BaseActivity implements LoaderCallbacks<Cursor>{

    private ArtworkAdapter mAdapter;

    @Inject Cupboard cupboard;
    @Inject Uri artworkUri;
    @Inject Picasso picasso;
    @Inject float imageHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.obtain(this).inject(this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setupArtworksGrid((RecyclerView) findViewById(R.id.artworks));
    }

    private void setupArtworksGrid(RecyclerView artworksView) {
        final int spacing = getResources()
                .getDimensionPixelSize(R.dimen.spacing_nano);
        artworksView.addItemDecoration(new OffsetDecoration(spacing));
        mAdapter = new ArtworkAdapter(this, picasso, imageHeight);
        mAdapter.setOnItemClickListener(
                new ArtworkAdapter.OnItemClickListener() {
                    @Override
                    public void onClick(View v, int position) {
                        startGalleryActivityWithTransition(MainActivity.this,
                                v.findViewById(R.id.artwork_title),
                                mAdapter.getItem(position));
                    }
                });
        artworksView.setAdapter(mAdapter);

        getSupportLoaderManager().initLoader(0, null, this);
    }

    private void startGalleryActivityWithTransition(Activity activity, View toolbar,
                                                    Artwork artwork) {

        final Pair[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, false,
                new Pair<>(toolbar, activity.getString(R.string.transition_toolbar)));
        @SuppressWarnings("unchecked")
        ActivityOptionsCompat sceneTransitionAnimation = ActivityOptionsCompat
                .makeSceneTransitionAnimation(activity, pairs);

        // Start the activity with the participants, animating from one to the other.
        final Bundle transitionBundle = sceneTransitionAnimation.toBundle();
//        Intent startIntent = GalleryActivity.getStartIntent(activity, artwork);
//        ActivityCompat.startActivityForResult(activity,
//                startIntent,
//                REQUEST_GALLERY,
//                transitionBundle);
        //TODO start gallery viewer.
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, artworkUri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        QueryResultIterable<Artwork> iterate = cupboard.withCursor(data).iterate(Artwork.class);
        List<Artwork> artworks = iterate.list(true);
        mAdapter.setArtworks(artworks);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
