package worhavah.sb01.demos.myview;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXTextObject;

import java.io.File;
import java.lang.ref.WeakReference;

import worhavah.sb01.MyApplication;
import worhavah.sb01.R;

/**
 * Created by Administrator on 2017/3/17.
 */

public class WXShareActivity extends Activity {

    private TextView tv;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.item_wx);

    tv= (TextView) findViewById(R.id.textView);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
share2WX();
            }


        });
    }

    private void share2WX() {

        WXTextObject w=new WXTextObject();
        w.text=" w.text";

        WXMediaMessage msg=new WXMediaMessage();
        msg.mediaObject=w;
        msg.description="msg.description";

        SendMessageToWX.Req req=new SendMessageToWX.Req();
        req.transaction="req.transaction";

        req.message=msg;
        req.scene=SendMessageToWX.Req.WXSceneTimeline;
        MyApplication.wxapi.sendReq(req);


    }



}
