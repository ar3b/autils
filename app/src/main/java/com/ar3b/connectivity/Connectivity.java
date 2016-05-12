package com.ar3b.connectivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

public class Connectivity {

    public static NetworkInfo getNetworkInfo(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo();
    }

    public static boolean isConnected(Context context){
        NetworkInfo info = Connectivity.getNetworkInfo(context);
        return (info != null && info.isConnected());
    }

    public static boolean isConnectedWifi(Context context){
        NetworkInfo info = Connectivity.getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_WIFI);
    }

    public static boolean isConnectedMobile(Context context){
        NetworkInfo info = Connectivity.getNetworkInfo(context);
        return (info != null && info.isConnected() && info.getType() == ConnectivityManager.TYPE_MOBILE);
    }

    public static String getConnectionType(Context context){
        NetworkInfo info = Connectivity.getNetworkInfo(context);
        if (info != null && info.isConnected()) {
            int type = info.getType();
            int subType = info.getSubtype();
            if (type == ConnectivityManager.TYPE_WIFI) {
                return "WiFi, fast";
            } else if (type == ConnectivityManager.TYPE_MOBILE) {
                switch (subType) {
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                        return "1xRTT, 50-100 kbps, slow";
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                        return "CDMA, 14-64 kbps, slow" ;
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                        return "EDGE, 50-100 kbps, slow";
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                        return "EVDO_0, 400-1000 kbps, fast";
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                        return "EVDO_A, 600-1400 kbps, fast";
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                        return "GPRS, 100 kbps, slow";
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                        return "HSDPA, 2-14 Mbps, fast";
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                        return "HSPA, 700-1700 kbps, fast";
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                        return "HSUPA, 1-23 Mbps, fast";
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                        return "UMTS, 400-7000 kbps, fast";
                    case TelephonyManager.NETWORK_TYPE_EHRPD:
                        return "EHRPD, 1-2 Mbps, fast";
                    case TelephonyManager.NETWORK_TYPE_EVDO_B:
                        return "EVDO_B, 5 Mbps, fast";
                    case TelephonyManager.NETWORK_TYPE_HSPAP:
                        return "HSPAP, 10-20 Mbps, fast";
                    case TelephonyManager.NETWORK_TYPE_IDEN:
                        return "IDEN, 25 kbps, slow";
                    case TelephonyManager.NETWORK_TYPE_LTE:
                        return "LTE, 10+ Mbps, fast";
                    case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                    default:
                        return "unknown";
                }
            } else {
                return "unknown";
            }
        } else {
            return "unknown";
        }
    }

}
