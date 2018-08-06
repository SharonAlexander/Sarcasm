package com.sharonalexander.sarcasm;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdListener;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.google.gson.GsonBuilder;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter_extensions.scroll.EndlessRecyclerOnScrollListener;
import com.sharonalexander.sarcasm.helper.Constants;
import com.sharonalexander.sarcasm.helper.InternetConnectionChecker;
import com.sharonalexander.sarcasm.model.Datum;
import com.sharonalexander.sarcasm.model.FirstResponse;

public class ContentActivity extends Fragment {

    AccessToken accessToken;
    RecyclerView recyclerView;
    FastItemAdapter<Datum> fastAdapter;
    FirstResponse fr, f;
    String after;
    String id;
    int page_pic;
    EndlessRecyclerOnScrollListener endlessRecyclerOnScrollListener;
    InternetConnectionChecker internetConnectionChecker;
    Preferences preferences;
    LinearLayout adViewBannerMain;
    private AdView adView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.troll_layout, container, false);

        preferences = new Preferences(getActivity());
        adView = new AdView(getActivity(), "2111173035815618_2112236772375911", AdSize.BANNER_HEIGHT_50);
        adViewBannerMain = (LinearLayout) rootView.findViewById(R.id.adViewBannerMain);
        adViewBannerMain.addView(adView);
        if (!preferences.getPremiumInfo()) {
            adsInitialise();
        } else {
            adViewBannerMain.setVisibility(View.GONE);
        }

        getActivity().setTitle(getString(R.string.app_name));

        internetConnectionChecker = new InternetConnectionChecker(getActivity());
        if (!internetConnectionChecker.isOnline()) {
            internetConnectionChecker.showNotConnectedDialog();
        }

        id = getArguments().getString("id");
        page_pic = getArguments().getInt("pic");

        accessToken = new AccessToken(Constants.facebook_app_token, Constants.facebook_app_id, Constants.facebook_app_id, null, null, null, null, null);

        recyclerView = rootView.findViewById(R.id.troll_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        fastAdapter = new FastItemAdapter<>();
        fastAdapter.setHasStableIds(true);
        fastAdapter.withSelectable(true);
        fastAdapter.withMultiSelect(true);
        fastAdapter.withSelectOnLongClick(true);

        fastAdapter.withEventHook(new Datum.VisitFB());
        fastAdapter.withEventHook(new Datum.MainShare());
        fastAdapter.withEventHook(new Datum.Download());

        recyclerView.setAdapter(fastAdapter);
        endlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore(final int currentPage) {
                if (internetConnectionChecker.isOnline()) {
                    makeTheCall();
                } else {
                    internetConnectionChecker.showNotConnectedDialog();
                }
            }
        };
        recyclerView.addOnScrollListener(endlessRecyclerOnScrollListener);
        makeFirstCall();
        return rootView;
    }

    private void makeFirstCall() {
        GraphRequest request = GraphRequest.newGraphPathRequest(
                accessToken,
                id,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        fr = new GsonBuilder().create().fromJson(response.getRawResponse(), FirstResponse.class);
                        fastAdapter.clear();
                        fastAdapter.add(fr.getData());
                        for (int i = 0; i < fr.getData().size(); i++) {
                            fr.getData().get(i).setPagepic(page_pic);
                        }
                        after = fr.getPaging().getCursors().getAfter();
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", Constants.feed_fields);
        parameters.putString("limit", Constants.feed_limit);
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void makeTheCall() {
        final GraphRequest request = GraphRequest.newGraphPathRequest(
                accessToken,
                id,
                new GraphRequest.Callback() {
                    @Override
                    public void onCompleted(GraphResponse response) {
                        f = new GsonBuilder().create().fromJson(response.getRawResponse(), FirstResponse.class);
                        fastAdapter.add(f.getData());
                        for (int i = 0; i < f.getData().size(); i++) {
                            f.getData().get(i).setPagepic(page_pic);
                        }
                        after = f.getPaging().getCursors().getAfter();
                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("pretty", "0");
        parameters.putString("fields", Constants.feed_fields);
        parameters.putString("limit", Constants.feed_limit);
        parameters.putString("after", after);
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void adsInitialise() {
        adView.loadAd();
        adView.setAdListener(new AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                adViewBannerMain.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });
    }

    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}
