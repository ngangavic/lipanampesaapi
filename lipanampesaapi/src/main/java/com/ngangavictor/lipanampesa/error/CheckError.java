package com.ngangavictor.lipanampesa.error;

import android.util.Log;

import com.ngangavictor.lipanampesa.settings.SandBox;

public class CheckError {

    String error;

    private  boolean checkAccessTokenUrl(){

        return SandBox.access_token_url.equals(null);
    }

    private boolean checkCKey(){
        error="consumer_key";
        return SandBox.getConsumerKey().isEmpty();
    }

    private boolean checkCSecret(){
        error="consumer_secret";
        return SandBox.getConsumerSecret().isEmpty();
    }

    private boolean checkBSCode(){
        error="short_code";
        return SandBox.getBusinessShortCode().isEmpty();
    }

    private boolean checkPassKey(){
        error="pass_key";
        return SandBox.getPassKey().isEmpty();
    }

    private boolean checkStkUrl(){
        error="stk_url";
        return SandBox.getStk_push_url().isEmpty();
    }

    public  void getError(){
        if(checkAccessTokenUrl()){
            Log.d(SandBox.access_token_url,"Access token url cannot be empty");
            System.exit(1);
        }else if (checkCKey()){
            Log.d(SandBox.consumerKey,"Consumer key cannot be empty");
            System.exit(1);
        }else if (checkCSecret()){
            Log.d(SandBox.consumerSecret,"Consumer secret cannot be empty");
            System.exit(1);
        }else if (checkBSCode()){
            Log.d(SandBox.businessShortCode,"Business short code cannot be empty");
            System.exit(1);

        }else if (checkStkUrl()){
            Log.d(SandBox.stk_push_url,"STK Push url cannot be empty");
            System.exit(1);
        }else if (checkPassKey()){
            Log.d(SandBox.passKey,"Passkey cannot be empty");
            System.exit(1);
        }
    }



}
