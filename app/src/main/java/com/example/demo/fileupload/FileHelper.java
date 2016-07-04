package com.example.demo.fileupload;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by guozhk on 16-6-24.
 */
public class FileHelper {

    public static void startUpload(final File file, final String RequestURL) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("d", "d");
                params.put("d1", "d1");
                FileUploader.uploadFile(file, RequestURL, params, new FileUploader.IUploadInterface() {
                    @Override
                    public void succ(String result) {
                        System.out.println("result:" + result);
                    }

                    @Override
                    public void fail() {
                        System.out.println("fail");
                    }
                });
            }
        }).start();
    }



    public static void startUpload(final List<File> file, final String RequestURL) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("files1", "files1");
                params.put("files2", "files2");
                FileUploader1.uploadFile(file, RequestURL, params, new FileUploader1.IUploadInterface() {
                    @Override
                    public void succ(String result) {
                        System.out.println("result:" + result);
                    }

                    @Override
                    public void fail() {
                        System.out.println("fail");
                    }
                });
            }
        }).start();
    }


}
