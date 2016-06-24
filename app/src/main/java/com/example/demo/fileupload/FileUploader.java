package com.example.demo.fileupload;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.UUID;

/**
 * Created by gzhenkai on 16/6/23.
 */
public class FileUploader {

    private static final String TAG = "uploadFile";
    private static final int TIME_OUT = 10 * 1000; // 超时时间
    private static final String CHARSET = "utf-8"; // 设置编码

    /**
     * @param file       需要上传的文件
     * @param RequestURL 请求的rul
     * @param params     需要上传的账号和密码
     * @return 返回响应的内容
     */

    public static int uploadFile(File file, String RequestURL, Map<String, String> params) {
        int res = 0;
        String result = null;
        String BOUNDARY = UUID.randomUUID().toString(); // 边界标识 随机生成
        String PREFIX = "--", LINE_END = "\r\n";
        String CONTENT_TYPE = "multipart/form-data"; // 内容类型

        URL url;
        try {
            url = new URL(RequestURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(TIME_OUT);
            conn.setConnectTimeout(TIME_OUT);
            conn.setDoInput(true); // 允许输入流
            conn.setDoOutput(true); // 允许输出流
            conn.setUseCaches(false); // 不允许使用缓存
            conn.setRequestMethod("POST"); // 请求方式
            conn.setRequestProperty("Charset", CHARSET); // 设置编码
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
                    + BOUNDARY);

            if (file != null) {

// 当文件不为空时执行上传

                DataOutputStream dos = new DataOutputStream(
                        conn.getOutputStream());

                StringBuilder textEntity = new StringBuilder();
                for (Map.Entry<String, String> entry : params.entrySet()) {// 构造文本类型参数的实体数据
                    textEntity.append("--");
                    textEntity.append(BOUNDARY);
                    textEntity.append("\r\n");
                    textEntity.append("Content-Disposition: form-data; name=\""
                            + entry.getKey() + "\"\r\n\r\n");
                    textEntity.append(entry.getValue());
                    textEntity.append("\r\n");
                }

                dos.write(textEntity.toString().getBytes());

                StringBuffer sb = new StringBuffer();
                sb.append(PREFIX);
                sb.append(BOUNDARY);
                sb.append(LINE_END);

// 这里重点注意： name里面的值为服务器端需要key 只有这个key 才可以得到对应的文件
//* filename是文件的名字，包含后缀名

                sb.append("Content-Disposition: form-data; name=\"chat_file\"; filename=\""
                        + file.getName() + "\"" + LINE_END);
                sb.append("Content-Type: application/octet-stream; charset="
                        + CHARSET + LINE_END);
                sb.append(LINE_END);
                dos.write(sb.toString().getBytes());
                Log.i("EEE", "SB=" + sb.toString());
                InputStream is = new FileInputStream(file);
                byte[] bytes = new byte[1024];
                int len = 0;
                while ((len = is.read(bytes)) != -1) {
                    dos.write(bytes, 0, len);
                }
                is.close();
                dos.write(LINE_END.getBytes());
                byte[] end_data = (PREFIX + BOUNDARY + PREFIX + LINE_END)
                        .getBytes();
                dos.write(end_data);
                dos.flush();

// 获取响应码 200=成功 当响应成功，获取响应的流

                res = conn.getResponseCode();
                Log.e(TAG, "response code:" + res);
                if (res == 200) {
                    Log.e(TAG, "request success");
                    InputStream input = conn.getInputStream();
                    StringBuffer sb1 = new StringBuffer();
                    int ss;
                    while ((ss = input.read()) != -1) {
                        sb1.append((char) ss);
                    }
                    result = sb1.toString();
                    Log.e(TAG, "result : " + result);
                } else {
                    Log.e(TAG, "request error");
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return 0;

    }


}
