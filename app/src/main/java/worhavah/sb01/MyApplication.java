package worhavah.sb01;

import android.content.Context;

import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.connection.FileDownloadUrlConnection;
import com.liulishuo.filedownloader.services.DownloadMgrInitialParams;
import com.morgoo.droidplugin.PluginHelper;
import com.qihoo360.replugin.RePluginApplication;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.net.Proxy;


/**
 * Created by Jacksgong on 12/17/15.
 */
public class MyApplication extends RePluginApplication {
    public static Context CONTEXT;
    private final static String TAG = "FileDownloadApplication";

    private final static String APPID = "f067837f8bf20c865302d723d96de666";
    public static IWXAPI wxapi;



    @Override
    public void onCreate() {
        super.onCreate();
        // for demo.
        CONTEXT = this;

        // just for open the log in this demo project.
        //FileDownloadLog.NEED_LOG = BuildConfig.DOWNLOAD_NEED_LOG;

        /**
         * just for cache Application's Context, and ':filedownloader' progress will NOT be launched
         * by below code, so please do not worry about performance.
         * @see FileDownloader#init(Context)
         */
        FileDownloader.init(getApplicationContext(), new DownloadMgrInitialParams.InitCustomMaker()
                .connectionCreator(new FileDownloadUrlConnection
                        .Creator(new FileDownloadUrlConnection.Configuration()
                        .connectTimeout(15_000) // set connection timeout.
                        .readTimeout(15_000) // set read timeout.
                        .proxy(Proxy.NO_PROXY) // set proxy
                )));

        regtoWX();
        PluginHelper.getInstance().applicationOnCreate(getBaseContext());

    }
    @Override
    protected void attachBaseContext(Context base) {
        PluginHelper.getInstance().applicationAttachBaseContext(base);
        super.attachBaseContext(base);
    }
    private void regtoWX() {
        wxapi= WXAPIFactory.createWXAPI(this,APPID,false);
        wxapi.registerApp(APPID);

    }
}
