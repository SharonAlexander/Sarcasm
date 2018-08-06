package com.sharonalexander.sarcasm;

import android.content.Context;

import com.sharonalexander.sarcasm.helper.Constants;
import com.sharonalexander.sarcasm.util.IabHelper;
import com.sharonalexander.sarcasm.util.IabResult;
import com.sharonalexander.sarcasm.util.Inventory;
import com.sharonalexander.sarcasm.util.Purchase;


public class CheckPurchase {

    static final String ITEM_SKU_SMALL = Constants.SKU_NAME;
    public static boolean isPremium = false;
    static IabHelper mHelper;
//        static final String ITEM_SKU_SMALL = "com.test.purchased";
    static Preferences prefManager;
    static IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            if (mHelper == null) return;
            if (result.isFailure()) {
            } else {
                Purchase premiumPurchase = inventory.getPurchase(ITEM_SKU_SMALL);
                if (inventory.hasPurchase(ITEM_SKU_SMALL)) {
                    boolean pre = true;
                }
                boolean premium = (premiumPurchase != null && verifyDeveloperPayload(premiumPurchase));
                prefManager.putPremiumInfo(premium);
            }
        }
    };

    public static void checkpurchases(Context context) {
        prefManager = new Preferences(context);
        String base64EncodedPublicKey = licensekey();
        mHelper = new IabHelper(context, base64EncodedPublicKey);
        mHelper.enableDebugLogging(true);
        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    return;
                }
                if (mHelper == null) return;
                try {
                    mHelper.queryInventoryAsync(mGotInventoryListener);
                } catch (IabHelper.IabAsyncInProgressException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    static boolean verifyDeveloperPayload(Purchase p) {
        String payload = p.getDeveloperPayload();
        return true;
    }

    private static String licensekey() {
        return Constants.apilicence;
    }

    public static void dispose() {
        if (mHelper != null) {
            mHelper.disposeWhenFinished();
            mHelper = null;
        }
    }
}