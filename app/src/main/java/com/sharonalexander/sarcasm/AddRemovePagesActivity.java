package com.sharonalexander.sarcasm;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;


public class AddRemovePagesActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    CheckBox sarcasm, beLikeBro,dude,dude2,dudeandyobro,mclovin,memes,
            noonecares,carmemes,maleslife,correctbro,sadcasm,serioushumor,
            trollfootball1,trollfootball2,funnyordie,auntyacid;
    Preferences preferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_remove_layout);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layout_to_add);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            linearLayout.setLayoutDirection(LinearLayout.LAYOUT_DIRECTION_RTL);
        }

        preferences = new Preferences(this);

        initialiseCheckBoxes();
        setChecks();
        setCheckListeners();
    }


    private void initialiseCheckBoxes() {
        sarcasm = (CheckBox) findViewById(R.id.sarcasm);
        beLikeBro = (CheckBox) findViewById(R.id.beLikeBro);
        dude = (CheckBox) findViewById(R.id.dude);
        dude2 = (CheckBox) findViewById(R.id.dude2);
        dudeandyobro = (CheckBox) findViewById(R.id.dudeandyobro);
        mclovin = (CheckBox) findViewById(R.id.mclovin);
        memes = (CheckBox) findViewById(R.id.memes);
        noonecares = (CheckBox) findViewById(R.id.noonecares);
        carmemes = (CheckBox) findViewById(R.id.carmemes);
        maleslife = (CheckBox) findViewById(R.id.maleslife);
        correctbro = (CheckBox) findViewById(R.id.correctbro);
        sadcasm = (CheckBox) findViewById(R.id.sadcasm);
        serioushumor = (CheckBox) findViewById(R.id.serioushumor);
        trollfootball1 = (CheckBox) findViewById(R.id.trollfootball1);
        trollfootball2 = (CheckBox) findViewById(R.id.trollfootball2);
        funnyordie = (CheckBox) findViewById(R.id.funnyordie);
        auntyacid = (CheckBox) findViewById(R.id.auntyacid);
    }

    private void setChecks() {
        sarcasm.setChecked(preferences.getCheckPref("sarcasm"));
        beLikeBro.setChecked(preferences.getCheckPref("belikebro"));
        dude.setChecked(preferences.getCheckPref("dude"));
        dude2.setChecked(preferences.getCheckPref("dude2"));
        dudeandyobro.setChecked(preferences.getCheckPref("dudeandyobro"));
        mclovin.setChecked(preferences.getCheckPref("mclovin"));
        memes.setChecked(preferences.getCheckPref("memes"));
        noonecares.setChecked(preferences.getCheckPref("noonecares"));
        carmemes.setChecked(preferences.getCheckPref("carmemes"));
        maleslife.setChecked(preferences.getCheckPref("maleslife"));
        correctbro.setChecked(preferences.getCheckPref("correctbro"));
        sadcasm.setChecked(preferences.getCheckPref("sadcasm"));
        serioushumor.setChecked(preferences.getCheckPref("serioushumor"));
        trollfootball1.setChecked(preferences.getCheckPref("trollfootball1"));
        trollfootball2.setChecked(preferences.getCheckPref("trollfootball2"));
        funnyordie.setChecked(preferences.getCheckPref("funnyordie"));
        auntyacid.setChecked(preferences.getCheckPref("auntyacid"));
    }

    private void setCheckListeners() {
        sarcasm.setOnCheckedChangeListener(this);
        beLikeBro.setOnCheckedChangeListener(this);
        dude.setOnCheckedChangeListener(this);
        dude2.setOnCheckedChangeListener(this);
        dudeandyobro.setOnCheckedChangeListener(this);
        mclovin.setOnCheckedChangeListener(this);
        memes.setOnCheckedChangeListener(this);
        noonecares.setOnCheckedChangeListener(this);
        carmemes.setOnCheckedChangeListener(this);
        maleslife.setOnCheckedChangeListener(this);
        correctbro.setOnCheckedChangeListener(this);
        sadcasm.setOnCheckedChangeListener(this);
        serioushumor.setOnCheckedChangeListener(this);
        trollfootball1.setOnCheckedChangeListener(this);
        trollfootball2.setOnCheckedChangeListener(this);
        funnyordie.setOnCheckedChangeListener(this);
        auntyacid.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.sarcasm:
                if (compoundButton.isChecked()) {
//                    MainActivity.result.addItemAtPosition(MainActivity.item_sarcasm, 1);
                    sarcasm.setChecked(true);
//                    preferences.putCheckPref("icu", true);
                } else {
//                    MainActivity.result.removeItem(1);
                    sarcasm.setChecked(true);
//                    preferences.putCheckPref("icu", false);
                }
                break;
            case R.id.beLikeBro:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_belikebro, 2);
                    beLikeBro.setChecked(true);
                    preferences.putCheckPref("belikebro", true);
                } else {
                    MainActivity.result.removeItem(2);
                    beLikeBro.setChecked(false);
                    preferences.putCheckPref("belikebro", false);
                }
                break;case R.id.dude:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_dude, 3);
                    dude.setChecked(true);
                    preferences.putCheckPref("dude", true);
                } else {
                    MainActivity.result.removeItem(3);
                    dude.setChecked(false);
                    preferences.putCheckPref("dude", false);
                }
                break;case R.id.dude2:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_dude2, 4);
                    dude2.setChecked(true);
                    preferences.putCheckPref("dude2", true);
                } else {
                    MainActivity.result.removeItem(4);
                    dude2.setChecked(false);
                    preferences.putCheckPref("dude2", false);
                }
                break;case R.id.dudeandyobro:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_belikebro, 5);
                    dudeandyobro.setChecked(true);
                    preferences.putCheckPref("dudeandyobro", true);
                } else {
                    MainActivity.result.removeItem(5);
                    dudeandyobro.setChecked(false);
                    preferences.putCheckPref("dudeandyobro", false);
                }
                break;case R.id.mclovin:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_mclovin, 6);
                    mclovin.setChecked(true);
                    preferences.putCheckPref("mclovin", true);
                } else {
                    MainActivity.result.removeItem(6);
                    mclovin.setChecked(false);
                    preferences.putCheckPref("mclovin", false);
                }
                break;case R.id.memes:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_memes, 7);
                    memes.setChecked(true);
                    preferences.putCheckPref("memes", true);
                } else {
                    MainActivity.result.removeItem(7);
                    memes.setChecked(false);
                    preferences.putCheckPref("memes", false);
                }
                break;case R.id.noonecares:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_noonecares, 8);
                    noonecares.setChecked(true);
                    preferences.putCheckPref("noonecares", true);
                } else {
                    MainActivity.result.removeItem(8);
                    noonecares.setChecked(false);
                    preferences.putCheckPref("noonecares", false);
                }
                break;case R.id.carmemes:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_carmemes, 9);
                    carmemes.setChecked(true);
                    preferences.putCheckPref("carmemes", true);
                } else {
                    MainActivity.result.removeItem(9);
                    carmemes.setChecked(false);
                    preferences.putCheckPref("carmemes", false);
                }
                break;case R.id.maleslife:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_maleslife, 10);
                    maleslife.setChecked(true);
                    preferences.putCheckPref("maleslife", true);
                } else {
                    MainActivity.result.removeItem(10);
                    maleslife.setChecked(false);
                    preferences.putCheckPref("maleslife", false);
                }
                break;case R.id.correctbro:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_correctbro, 11);
                    correctbro.setChecked(true);
                    preferences.putCheckPref("correctbro", true);
                } else {
                    MainActivity.result.removeItem(11);
                    correctbro.setChecked(false);
                    preferences.putCheckPref("correctbro", false);
                }
                break;case R.id.sadcasm:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_sadcasm, 12);
                    sadcasm.setChecked(true);
                    preferences.putCheckPref("sadcasm", true);
                } else {
                    MainActivity.result.removeItem(12);
                    sadcasm.setChecked(false);
                    preferences.putCheckPref("sadcasm", false);
                }
                break;case R.id.serioushumor:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_serioushumor, 13);
                    serioushumor.setChecked(true);
                    preferences.putCheckPref("serioushumor", true);
                } else {
                    MainActivity.result.removeItem(13);
                    serioushumor.setChecked(false);
                    preferences.putCheckPref("serioushumor", false);
                }
                break;case R.id.trollfootball1:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_trollfootball1, 14);
                    trollfootball1.setChecked(true);
                    preferences.putCheckPref("trollfootball1", true);
                } else {
                    MainActivity.result.removeItem(14);
                    trollfootball1.setChecked(false);
                    preferences.putCheckPref("trollfootball1", false);
                }
                break;case R.id.trollfootball2:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_trollfootball2, 15);
                    trollfootball2.setChecked(true);
                    preferences.putCheckPref("trollfootball2", true);
                } else {
                    MainActivity.result.removeItem(15);
                    trollfootball2.setChecked(false);
                    preferences.putCheckPref("trollfootball2", false);
                }
                break;case R.id.funnyordie:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_funnyordie, 16);
                    funnyordie.setChecked(true);
                    preferences.putCheckPref("funnyordie", true);
                } else {
                    MainActivity.result.removeItem(16);
                    funnyordie.setChecked(false);
                    preferences.putCheckPref("funnyordie", false);
                }
                break;case R.id.auntyacid:
                if (compoundButton.isChecked()) {
                    MainActivity.result.addItemAtPosition(MainActivity.item_belikebro, 17);
                    auntyacid.setChecked(true);
                    preferences.putCheckPref("auntyacid", true);
                } else {
                    MainActivity.result.removeItem(17);
                    auntyacid.setChecked(false);
                    preferences.putCheckPref("auntyacid", false);
                }
                break;
        }
    }
}
