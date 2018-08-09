package com.sharonalexander.sarcasm.helper;

import android.os.Environment;

public class Constants {

    // WHOEVER IS COPYING MY APP PLEASE CREATE AND USE YOUR OWN API LICENCE, FACEBOOK APP ID AND
    // APP TOKEN. PLEASE TAKE CARE TO USE YOUR OWN ADMOB AD IDS SO YOU'LL GET YOUR MONEY.
    //
    // IT'S OK TO COPY THE CODE. BUT TRY YOURSELF SOME MODIFICATIONS AND CHANGES AND BE BETTER IN CODING
    // ITS HIGHLY APPRECIATED TO DROP A MAIL BEFORE YOU COPY MY CODE.
    // THANKS - SHARON ALEXANDER


    //apilicense
    public static String apilicence = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAhCdo9CBO3Y7AGL+7R4g2uck4qSXLkde70UQkUwCSY3dIl5F4EY4H9JgSrjpfmmAERbHHR7Cr9sDEmcIvhOxuzcDVeUU+wNjyxlacrkztomR32HJmh96YuIoa5/60Dc/lWglL5YZYC8PMrep8DRSjGoRbBpkhzDBZfxylUwrtVMFdyOjX05D/ix/CL2MVxCkvA5vnNd2mOgsr+DyL/ePARGevjVzd1JtdQ0tMf361xVKMRGlkpOjjvwm61nGvBGGx3resTq5H/NQZu8KQWaPnLKLj9NHWI13fNNdjwfnVnve3ECY+o16XpLoYwETe35IWH+i742kxTBBxWEUwoV2GLQIDAQAB";

    public static String SKU_NAME = "com.sharonalexander.sarcasm";
    //    Facebook Creds
    public static String facebook_app_id = "2111173035815618";

    // Those who copy the code please create your own app token from facebook. Thanks
    public static String facebook_app_token = "482856168756600|Oz-RWx6e1U5z011vOooc1Io1v2c";

    //Ads
    public static String facebook_banner_main = "2111173035815618_2112236772375911";
    public static String facebook_interstitialpages = "2111173035815618_2112246529041602";
    public static String facebook_interstitialaddremove = "2111173035815618_2112245749041680";

    //    Pages ID
    public static String id_sarcasm = "/204253186879892/posts";
    public static String id_belikebro = "/584802188351007/posts";
    public static String id_dude = "/312936062424124/posts";
    public static String id_dude2 = "/203979960317942/posts";
    public static String id_dudeandyobro = "/1021241924615460/posts";
    public static String id_mclovin = "/598667513517486/posts";
    public static String id_memes = "/527507080600217/posts";
    public static String id_noonecares = "/271958689824808/posts";
    public static String id_carmemes = "/308506372538478/posts";
    public static String id_maleslife = "/1066590986765150/posts";
    public static String id_correctbro = "/1050850821660526/posts";
    public static String id_sadcasm = "/458104154581498/posts";
    public static String id_serioushumor = "/964998683549555/posts";
    public static String id_trollfootball1 = "/573730156043059/posts";
    public static String id_trollfootball2 = "/1476658519224874/posts";
    public static String id_funnyordie = "/17614953850/posts";
    public static String id_auntyacid = "/200144556761182/posts";

    //    Feed
    public static String feed_limit = "25";
    public static String feed_fields = "full_picture,message,from,permalink_url,type,link,source,picture";

    //    File Management
    public static String folder_name = "/Sarcasm/";
    public static String hidden_folder_for_app_share = ".share/";
    public static String image_extention = ".jpg";
    public static String video_extention = ".mp4";
    public static String folder_main_path = Environment.getExternalStorageDirectory().getPath();

    //    Helpers
    public static String whatsapp_package = "com.whatsapp";

    //    Common
    public static String alert_developer_info = "Developed by MadRabbits\u00A9";
    public static String added_share_message = "\nShared via Sarcasm Dude & Be Like Bro App: https://goo.gl/L7hzRo";
}
