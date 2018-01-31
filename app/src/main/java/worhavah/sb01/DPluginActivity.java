package worhavah.sb01;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.qihoo360.replugin.RePlugin;
import com.qihoo360.replugin.model.PluginInfo;
import com.qihoo360.replugin.utils.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by hasee on 2018/1/29.
 */

public class DPluginActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plug);

        findViewById(R.id.btn_start_demo1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 刻意以“包名”来打开
                final ProgressDialog pd = ProgressDialog.show(DPluginActivity.this, "Installing...", "Please wait...", true, true);
                // FIXME: 仅用于安装流程演示 2017/7/24
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        simulateInstallExternalPlugin();
                        pd.dismiss();
                    }
                }, 1000);     }
        });

        findViewById(R.id.btn_start_plugin_for_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 刻意以“包名”来打开

                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        RePlugin.startActivity(DPluginActivity.this, RePlugin.createIntent("com.zztzt.android.simple.app", "com.zztzt.android.simple.app.MainActivity"));

                    }
                }, 1000);
            }
        });

    }

    /**
     * 模拟安装或升级（覆盖安装）外置插件
     * 注意：为方便演示，外置插件临时放置到Host的assets/external目录下，具体说明见README</p>
     */
    private void simulateInstallExternalPlugin() {
       // String demo3Apk= "demo3.apk";
        String demo3Apk= "productTztGtjaGgqqApp-debug.apk";
        String demo3apkPath = "external" + File.separator + demo3Apk;

        // 文件是否已经存在？直接删除重来
        String pluginFilePath = getFilesDir().getAbsolutePath() + File.separator + demo3Apk;
        File pluginFile = new File(pluginFilePath);
        if (pluginFile.exists()) {
            FileUtils.deleteQuietly(pluginFile);
        }

        // 开始复制
        copyAssetsFileToAppFiles(demo3apkPath, demo3Apk);
        PluginInfo info = null;
        if (pluginFile.exists()) {
            info = RePlugin.install(pluginFilePath);
        }

        if (info != null) {
            RePlugin.startActivity(DPluginActivity.this, RePlugin.createIntent(info.getName(), "com.zztzt.android.simple.app.MainActivity"));
        } else {
            Toast.makeText(DPluginActivity.this, "install external plugin failed", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 从assets目录中复制某文件内容
     *  @param  assetFileName assets目录下的Apk源文件路径
     *  @param  newFileName 复制到/data/data/package_name/files/目录下文件名
     */
    private void copyAssetsFileToAppFiles(String assetFileName, String newFileName) {
        InputStream is = null;
        FileOutputStream fos = null;
        int buffsize = 1024;

        try {
            is = this.getAssets().open(assetFileName);
            fos = this.openFileOutput(newFileName, Context.MODE_PRIVATE);
            int byteCount = 0;
            byte[] buffer = new byte[buffsize];
            while((byteCount = is.read(buffer)) != -1) {
                fos.write(buffer, 0, byteCount);
            }
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
