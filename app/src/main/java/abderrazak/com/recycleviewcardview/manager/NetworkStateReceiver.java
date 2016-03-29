package abderrazak.com.recycleviewcardview.manager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import abderrazak.com.recycleviewcardview.BaseApplication;
import abderrazak.com.recycleviewcardview.util.EventBusHelper;
import abderrazak.com.recycleviewcardview.util.NetworkUtil;

/**
 * Created by abderrazak on 16/03/2016.
 */
public class NetworkStateReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        String status = NetworkUtil.getConnectivityStatusString(context);
        BaseApplication.getBus().post(new EventBusHelper(status));
    }
}
