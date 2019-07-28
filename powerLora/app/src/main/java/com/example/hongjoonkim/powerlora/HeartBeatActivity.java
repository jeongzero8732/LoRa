package com.example.hongjoonkim.powerlora;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class HeartBeatActivity extends AppCompatActivity {

    TextView t1;
    Button btn;
    int check = 0;
    String live_beat;
    Util util;
    private static final int PROGRESS = 0x01;
    private ProgressBar mProgress;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            t1.setText(live_beat);
            check = Integer.parseInt(t1.getText().toString());
            if(check > 64 && check < 86) // 정상 심박수
            {
                btn.setText("정상");
            }
            else // 비정상 심박수.
            {
                btn.setText("비정상");
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_beat);
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    t1 = findViewById(R.id.result01);
                    btn = (Button)findViewById(R.id.btn);

                    String page = util.REGISTER_URL2+"beat.jsp";
                    HttpClient http = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost(page);

                    // 서버로 요청을 함 -> 서버에서는 제이슨을 생성하기 시작함. 제이슨을 생성하고 나서 제이슨을 보내줌.
                    // http.execute(httpPost) -> 서버에 요청
                    // 서버의 응답(json)이 response로 리턴됨.
                    HttpResponse response = http.execute(httpPost);  // response에 서버에서 생성된 제이슨 객체가 담김.
                    // httpReponse를 스트링으로 변환.
                    String body = EntityUtils.toString(response.getEntity()); // String으로 변환하는 작업.

                    // 스트링을 json 객체로 변환.
                    JSONObject jsonObj = new JSONObject(body);
                    JSONArray jArray = (JSONArray)jsonObj.get("sendData");

                    for(int i=0; i<jArray.length(); i++)
                    {
                        JSONObject row = jArray.getJSONObject(i);
                        live_beat = (row.getString("beat"));
                    }

                    handler.sendEmptyMessage(0);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });
        th.start();

    }
}
