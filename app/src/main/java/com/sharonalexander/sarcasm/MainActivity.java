package com.sharonalexander.sarcasm;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Toast;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;
import com.sharonalexander.sarcasm.helper.Constants;
import com.sharonalexander.sarcasm.helper.ShareHelper;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity {

    static Drawer result;
    static PrimaryDrawerItem item_sarcasm, item_belikebro, item_dude, item_dude2, item_dudeandyobro,
            item_mclovin, item_memes, item_noonecares, item_carmemes, item_maleslife, item_correctbro,
            item_sadcasm, item_serioushumor, item_trollfootball1, item_trollfootball2, item_funnyordie, item_auntyacid;
    Toolbar toolbar;
    AccountHeader headerResult;
    DividerDrawerItem item_divider;
    SecondaryDrawerItem item_settings, item_about, item_shareTheApp, item_addRemove;
    Preferences preferences;
    boolean isPremium = false;
    int adCount = 0;
    private InterstitialAd mInterstitialAdforPages, mInterstitialAdforAddRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);

        CheckPurchase.checkpurchases(this);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        preferences = new Preferences(this);

        isPremium = preferences.getPremiumInfo();
        if (!isPremium) {
            mInterstitialAdforPages = new InterstitialAd(this, Constants.facebook_interstitialpages);
            mInterstitialAdforAddRemove = new InterstitialAd(this, Constants.facebook_interstitialaddremove);
            mInterstitialAdforPages.loadAd();
            mInterstitialAdforAddRemove.loadAd();
            mInterstitialAdforPages.setAdListener(new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    mInterstitialAdforPages.loadAd();
                }

                @Override
                public void onError(Ad ad, AdError adError) {
                }

                @Override
                public void onAdLoaded(Ad ad) {

                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            });
            mInterstitialAdforAddRemove.setAdListener(new InterstitialAdListener() {
                @Override
                public void onInterstitialDisplayed(Ad ad) {

                }

                @Override
                public void onInterstitialDismissed(Ad ad) {
                    mInterstitialAdforAddRemove.loadAd();
                    startActivity(new Intent(MainActivity.this, AddRemovePagesActivity.class));
                }

                @Override
                public void onError(Ad ad, AdError adError) {

                }

                @Override
                public void onAdLoaded(Ad ad) {

                }

                @Override
                public void onAdClicked(Ad ad) {

                }

                @Override
                public void onLoggingImpression(Ad ad) {

                }
            });
        }

        if (preferences.isFirstTimeLaunch()) {
            setDefaultDrawerItemChecks();
        }
        checkingFolders(MainActivity.this);
        completeNavigationDrawer();
    }

    private void setDefaultDrawerItemChecks() {
        preferences.putCheckPref("sarcasm", true);
        preferences.putCheckPref("belikebro", true);
        preferences.putCheckPref("dude", true);
        preferences.putCheckPref("dude2", true);
        preferences.putCheckPref("dudeandyobro", true);
        preferences.putCheckPref("mclovin", true);
        preferences.putCheckPref("memes", true);
        preferences.putCheckPref("noonecares", true);
        preferences.putCheckPref("carmemes", true);
        preferences.putCheckPref("maleslife", true);
        preferences.putCheckPref("correctbro", true);
        preferences.putCheckPref("sadcasm", true);
        preferences.putCheckPref("serioushumor", true);
        preferences.putCheckPref("trollfootball1", true);
        preferences.putCheckPref("trollfootball2", true);
        preferences.putCheckPref("funnyordie", true);
        preferences.putCheckPref("auntyacid", true);

        preferences.setFirstTimeLaunch(false);
    }

    private void completeNavigationDrawer() {
        createNavigationHeader();
        createDrawerLayout();
        createDrawerItems();
        addDrawerItems();
        createDrawerClicks();

        //The First Page is ICU. And cannot be changed from addremove
        result.setSelection(1);
        result.getActionBarDrawerToggle().setDrawerIndicatorEnabled(true);
    }

    @Override
    public void onBackPressed() {
        Fragment frag = getFragmentManager().findFragmentByTag("settings");
        if (result.isDrawerOpen()) {
            result.closeDrawer();
        } else if (frag != null) {
            Fragment fragment = new ContentActivity();
            Bundle bundle = new Bundle();
            bundle.putString("id", Constants.id_sarcasm);
            bundle.putInt("pic", R.drawable.icon_sarcasm);
            fragment.setArguments(bundle);
            this.getFragmentManager().beginTransaction().replace(R.id.mainFrame, fragment).commit();
            result.closeDrawer();
        } else {
            CheckPurchase.dispose();
            alertExitPic();
        }
    }

    private void createNavigationHeader() {
        headerResult = new AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.nav_header)
                .addProfiles(
                        new ProfileDrawerItem().withName(getString(R.string.app_name)).withIcon(getResources().getDrawable(R.mipmap.ic_launcher))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();
    }

    private void createDrawerLayout() {
        result = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(toolbar)
                .withAccountHeader(headerResult)
                .withTranslucentStatusBar(true)
                .build();
    }

    private void createDrawerItems() {
        item_sarcasm = new PrimaryDrawerItem().withIdentifier(1).withName(getString(R.string.pagename_sarcasm)).withIcon(R.drawable.icon_sarcasm);
        item_belikebro = new PrimaryDrawerItem().withIdentifier(2).withName(getString(R.string.pagename_belikebro)).withIcon(R.drawable.icon_belikebro);
        item_dude = new PrimaryDrawerItem().withIdentifier(3).withName(getString(R.string.pagename_dude)).withIcon(R.drawable.icon_dude);
        item_dude2 = new PrimaryDrawerItem().withIdentifier(4).withName(getString(R.string.pagename_dude2)).withIcon(R.drawable.icon_dude2);
        item_dudeandyobro = new PrimaryDrawerItem().withIdentifier(5).withName(getString(R.string.pagename_dudeandyobro)).withIcon(R.drawable.icon_dudeandyobro);
        item_mclovin = new PrimaryDrawerItem().withIdentifier(6).withName(getString(R.string.pagename_mclovin)).withIcon(R.drawable.icon_mclovin);
        item_memes = new PrimaryDrawerItem().withIdentifier(7).withName(getString(R.string.pagename_memes)).withIcon(R.drawable.icon_memes);
        item_noonecares = new PrimaryDrawerItem().withIdentifier(8).withName(getString(R.string.pagename_noonecares)).withIcon(R.drawable.icon_noonecares);
        item_carmemes = new PrimaryDrawerItem().withIdentifier(9).withName(getString(R.string.pagename_carmemes)).withIcon(R.drawable.icon_carmemes);
        item_maleslife = new PrimaryDrawerItem().withIdentifier(10).withName(getString(R.string.pagename_maleslife)).withIcon(R.drawable.icon_maleslife);
        item_correctbro = new PrimaryDrawerItem().withIdentifier(11).withName(getString(R.string.pagename_correctbro)).withIcon(R.drawable.icon_correctbro);
        item_sadcasm = new PrimaryDrawerItem().withIdentifier(12).withName(getString(R.string.pagename_sadcasm)).withIcon(R.drawable.icon_sadcasm);
        item_serioushumor = new PrimaryDrawerItem().withIdentifier(13).withName(getString(R.string.pagename_serioushumor)).withIcon(R.drawable.icon_serioushumor);
        item_trollfootball1 = new PrimaryDrawerItem().withIdentifier(14).withName(getString(R.string.pagename_trollfootball1)).withIcon(R.drawable.icon_trollfootball1);
        item_trollfootball2 = new PrimaryDrawerItem().withIdentifier(15).withName(getString(R.string.pagename_trollfootball2)).withIcon(R.drawable.icon_trollfootball2);
        item_funnyordie = new PrimaryDrawerItem().withIdentifier(16).withName(getString(R.string.pagename_funnyordie)).withIcon(R.drawable.icon_funnyordie);
        item_auntyacid = new PrimaryDrawerItem().withIdentifier(17).withName(getString(R.string.pagename_auntyacid)).withIcon(R.drawable.icon_auntyacid);

        item_divider = new DividerDrawerItem();

        item_settings = new SecondaryDrawerItem().withIdentifier(100).withName(getString(R.string.action_settings)).withIcon(R.drawable.settings).withSelectable(false);
        item_about = new SecondaryDrawerItem().withIdentifier(101).withName(getString(R.string.about)).withIcon(R.drawable.about).withSelectable(false);
        item_shareTheApp = new SecondaryDrawerItem().withIdentifier(102).withName(R.string.sharetheapp).withIcon(R.drawable.share).withSelectable(false);
        item_addRemove = new SecondaryDrawerItem().withIdentifier(103).withName(R.string.add_remove).withIcon(R.drawable.add_remove).withSelectable(false);
    }

    private void addDrawerItems() {
        Preferences preferences = new Preferences(this);
        if (preferences.getCheckPref("sarcasm")) {
            result.addItem(item_sarcasm);
        }
        if (preferences.getCheckPref("belikebro")) {
            result.addItem(item_belikebro);
        }
        if (preferences.getCheckPref("dude")) {
            result.addItem(item_dude);
        }
        if (preferences.getCheckPref("dude2")) {
            result.addItem(item_dude2);
        }
        if (preferences.getCheckPref("dudeandyobro")) {
            result.addItem(item_dudeandyobro);
        }
        if (preferences.getCheckPref("mclovin")) {
            result.addItem(item_mclovin);
        }
        if (preferences.getCheckPref("memes")) {
            result.addItem(item_memes);
        }
        if (preferences.getCheckPref("noonecares")) {
            result.addItem(item_noonecares);
        }
        if (preferences.getCheckPref("carmemes")) {
            result.addItem(item_carmemes);
        }
        if (preferences.getCheckPref("maleslife")) {
            result.addItem(item_maleslife);
        }
        if (preferences.getCheckPref("correctbro")) {
            result.addItem(item_correctbro);
        }
        if (preferences.getCheckPref("sadcasm")) {
            result.addItem(item_sadcasm);
        }
        if (preferences.getCheckPref("serioushumor")) {
            result.addItem(item_serioushumor);
        }
        if (preferences.getCheckPref("trollfootball1")) {
            result.addItem(item_trollfootball1);
        }
        if (preferences.getCheckPref("trollfootball2")) {
            result.addItem(item_trollfootball2);
        }
        if (preferences.getCheckPref("funnyordie")) {
            result.addItem(item_funnyordie);
        }
        if (preferences.getCheckPref("auntyacid")) {
            result.addItem(item_auntyacid);
        }

        result.addItems(item_divider, item_addRemove, item_settings, item_about, item_shareTheApp);
    }

    private void createDrawerClicks() {
        result.setOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                Fragment fragment = new ContentActivity();
                Bundle bundle = new Bundle();
                adCount = adCount + 1;
                switch ((int) drawerItem.getIdentifier()) {
                    case 1:
                        bundle.putString("id", Constants.id_sarcasm);
                        bundle.putInt("pic", R.drawable.icon_sarcasm);
                        break;
                    case 2:
                        bundle.putString("id", Constants.id_belikebro);
                        bundle.putInt("pic", R.drawable.icon_belikebro);
                        break;
                    case 3:
                        bundle.putString("id", Constants.id_dude);
                        bundle.putInt("pic", R.drawable.icon_dude);
                        break;
                    case 4:
                        bundle.putString("id", Constants.id_dude2);
                        bundle.putInt("pic", R.drawable.icon_dude2);
                        break;
                    case 5:
                        bundle.putString("id", Constants.id_dudeandyobro);
                        bundle.putInt("pic", R.drawable.icon_dudeandyobro);
                        break;
                    case 6:
                        bundle.putString("id", Constants.id_mclovin);
                        bundle.putInt("pic", R.drawable.icon_mclovin);
                        break;
                    case 7:
                        bundle.putString("id", Constants.id_memes);
                        bundle.putInt("pic", R.drawable.icon_memes);
                        break;
                    case 8:
                        bundle.putString("id", Constants.id_noonecares);
                        bundle.putInt("pic", R.drawable.icon_noonecares);
                        break;
                    case 9:
                        bundle.putString("id", Constants.id_carmemes);
                        bundle.putInt("pic", R.drawable.icon_carmemes);
                        break;
                    case 10:
                        bundle.putString("id", Constants.id_maleslife);
                        bundle.putInt("pic", R.drawable.icon_maleslife);
                        break;
                    case 11:
                        bundle.putString("id", Constants.id_correctbro);
                        bundle.putInt("pic", R.drawable.icon_correctbro);
                        break;
                    case 12:
                        bundle.putString("id", Constants.id_sadcasm);
                        bundle.putInt("pic", R.drawable.icon_sadcasm);
                        break;
                    case 13:
                        bundle.putString("id", Constants.id_serioushumor);
                        bundle.putInt("pic", R.drawable.icon_serioushumor);
                        break;
                    case 14:
                        bundle.putString("id", Constants.id_trollfootball1);
                        bundle.putInt("pic", R.drawable.icon_trollfootball1);
                        break;
                    case 15:
                        bundle.putString("id", Constants.id_trollfootball2);
                        bundle.putInt("pic", R.drawable.icon_trollfootball2);
                        break;
                    case 16:
                        bundle.putString("id", Constants.id_funnyordie);
                        bundle.putInt("pic", R.drawable.icon_funnyordie);
                        break;
                    case 17:
                        bundle.putString("id", Constants.id_auntyacid);
                        bundle.putInt("pic", R.drawable.icon_auntyacid);
                        break;


                    case 100://settings
                        getFragmentManager().beginTransaction().replace(R.id.mainFrame, new Settings(), "settings").commit();
                        result.closeDrawer();
                        return true;
                    case 101://about
                        showAlertAboutUs();
                        return true;
                    case 102://share the app
                        shareApp(MainActivity.this);
                        return true;
                    case 103: //add remove pages
                        if (!isPremium && mInterstitialAdforAddRemove.isAdLoaded()) {
                            mInterstitialAdforAddRemove.show();
                        } else {
                            startActivity(new Intent(MainActivity.this, AddRemovePagesActivity.class));
                        }
                        return true;
                    default:
                        showPageError();
                        return true;
                }
                //check this implementation for on adclosed
                if (adCount == 5 && !isPremium) {
                    if (mInterstitialAdforPages.isAdLoaded()) {
                        mInterstitialAdforPages.show();
                    }
                    adCount = 0;
                }
                fragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.mainFrame, fragment).commit();
                result.closeDrawer();
                return true;
            }
        });
    }

    private void alertExitPic() {
        final AlertDialog.Builder alertdialog = new AlertDialog.Builder(
                new ContextThemeWrapper(MainActivity.this, R.style.AlertDialogTheme));
        alertdialog.setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, R.string.exit_toast, Toast.LENGTH_SHORT).show();
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    private void showPageError() {
        new AlertDialog.Builder(this)
                .setTitle("Page Error")
                .setMessage("The page cannot be displayed. \nTry another page instead")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setIcon(R.mipmap.ic_launcher)
                .show();
    }

    private void showAlertAboutUs() {
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = pInfo != null ? pInfo.versionName : "";
        new AlertDialog.Builder(this)
                .setTitle(R.string.app_name)
                .setMessage("Version:" + version + "\n" + Constants.alert_developer_info)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(R.mipmap.ic_launcher)
                .show();
    }

    @AfterPermissionGranted(005)
    public void shareApp(Context context) {
        String[] perms = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(context, perms)) {
            new ShareHelper().shareAppDetails((Activity) context);
        } else {
            EasyPermissions.requestPermissions((Activity) context, context.getString(R.string.storage_permission_prompt_message),
                    005, perms);
        }
    }

    @AfterPermissionGranted(006)
    public void checkingFolders(Context context) {
        String[] perms = {android.Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (EasyPermissions.hasPermissions(context, perms)) {
            new ShareHelper().checkFolders();
        } else {
            EasyPermissions.requestPermissions((Activity) context, context.getString(R.string.storage_permission_prompt_message),
                    006, perms);
        }
    }
}
