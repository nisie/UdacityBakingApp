package com.nisie.udacitybakingapp.recipe.view.fragment;


import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.nisie.udacitybakingapp.R;
import com.nisie.udacitybakingapp.recipe.view.viewmodel.StepViewModel;

/**
 * @author by nisie on 9/2/17.
 */

public class StepFragment extends Fragment {
    private static final String ARGS_DATA = "ARGS_DATA";
    private static final String ARGS_POSITION = "ARGS_POSITION";
    StepViewModel data;
    TextView title;
    ImageView imgThumbnail;
    SimpleExoPlayerView playerView;
    SimpleExoPlayer player;
    private ProgressDialog progressDialog;
    private boolean playWhenReady;
    private int currentWindow;
    private long playbackPosition;
    ComponentListener componentListener;
    boolean isLandscape;

    public static Fragment createInstance(StepViewModel stepViewModel) {
        Fragment fragment = new StepFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_DATA, stepViewModel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            isLandscape = false;
        } else {
            isLandscape = true;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_step, container, false);
        if (!isLandscape)
            title = (TextView) v.findViewById(R.id.title);
        playerView = (SimpleExoPlayerView) v.findViewById(R.id.video_player);
        imgThumbnail = (ImageView) v.findViewById(R.id.img_thumbnail);
        componentListener = new ComponentListener();
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null
                && getArguments().getParcelable(ARGS_DATA) != null) {
            data = getArguments().getParcelable(ARGS_DATA);
        } else if (savedInstanceState != null
                && savedInstanceState.getParcelable(ARGS_DATA) != null) {
            data = savedInstanceState.getParcelable(ARGS_DATA);
        } else {
            getActivity().finish();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            onResume();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getUserVisibleHint()) {

            getActivity().setTitle(data.getShortDescription());

            if (!isLandscape) {
                title.setText(data.getDescription());
            }

            if (!TextUtils.isEmpty(data.getVideoURL())) {
                playerView.setVisibility(View.VISIBLE);
                initializePlayer();
            } else
                playerView.setVisibility(View.GONE);

            if (TextUtils.isEmpty(data.getVideoURL())
                    && !TextUtils.isEmpty(data.getThumbnailURL())) {
                playerView.setVisibility(View.GONE);
                imgThumbnail.setVisibility(View.VISIBLE);
            } else
                imgThumbnail.setVisibility(View.GONE);


        }
    }

    private void initializePlayer() {
        if (player == null) {
            player = ExoPlayerFactory.newSimpleInstance(
                    new DefaultRenderersFactory(getActivity()),
                    new DefaultTrackSelector(), new DefaultLoadControl());
            player.addListener(componentListener);

            playerView.setPlayer(player);

            player.setPlayWhenReady(playWhenReady);
            player.seekTo(currentWindow, playbackPosition);

            Uri uri = Uri.parse(data.getVideoURL());
            MediaSource mediaSource = buildMediaSource(uri);
            player.prepare(mediaSource, true, false);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    public void onStop() {
        super.onStop();
        releasePlayer();
    }

    private void releasePlayer() {
        if (player != null) {
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            playWhenReady = player.getPlayWhenReady();
            player.removeListener(componentListener);
            player.release();
            player = null;
        }
    }

    private MediaSource buildMediaSource(Uri uri) {
        return new ExtractorMediaSource(uri,
                new DefaultHttpDataSourceFactory("ua"),
                new DefaultExtractorsFactory(), null, null);
    }

    private void showLoadingProgress() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage(getString(R.string.buffering));
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    private void dismissLoadingProgress() {
        if (progressDialog != null)
            progressDialog.dismiss();
    }

    private class ComponentListener implements ExoPlayer.EventListener {
        @Override
        public void onTimelineChanged(Timeline timeline, Object manifest) {

        }

        @Override
        public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

        }

        @Override
        public void onLoadingChanged(boolean isLoading) {

        }

        @Override
        public void onPlayerStateChanged(boolean playWhenReady,
                                         int playbackState) {
            String stateString;
            switch (playbackState) {
                case ExoPlayer.STATE_IDLE:
                    stateString = "ExoPlayer.STATE_IDLE      -";
                    break;
                case ExoPlayer.STATE_BUFFERING:
                    stateString = "ExoPlayer.STATE_BUFFERING -";
                    break;
                case ExoPlayer.STATE_READY:
                    stateString = "ExoPlayer.STATE_READY     -";
                    break;
                case ExoPlayer.STATE_ENDED:
                    stateString = "ExoPlayer.STATE_ENDED     -";
                    break;
                default:
                    stateString = "UNKNOWN_STATE             -";
                    break;
            }
            Log.d(StepFragment.class.getSimpleName(), "changed state to " + stateString
                    + " playWhenReady: " + playWhenReady);
        }

        @Override
        public void onPlayerError(ExoPlaybackException error) {

        }

        @Override
        public void onPositionDiscontinuity() {

        }

        @Override
        public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong(ARGS_POSITION, playbackPosition);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            this.playbackPosition = savedInstanceState.getLong(ARGS_POSITION, 0);
        }
    }
}
