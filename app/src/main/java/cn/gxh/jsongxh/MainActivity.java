package cn.gxh.jsongxh;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTvContent = (TextView) findViewById(R.id.tv_content);
    }
    
    public void btnClick(View view){
        int id=view.getId();
        switch (id){
            case R.id.btn_01:
                modeToJson();
                break;
            case R.id.btn_02:
                jsonToMode();
                break;
        }
    }

    private void jsonToMode() {

        String json="{\"infos\":{\"birthday\":\"1988-12-1\",\"hobbies\":[{\"imgUrl\":\"http://www.baidu.com\",\"type\":\"旅游\"},{\"imgUrl\":\"http://www.baidu.com\",\"type\":\"阅读\"}],\"age\":29,\"height\":181},\"name\":\"李易峰\",\"password\":333,\"vip\":true}";

        User user= (User) cn.gxh.jsongxh.FastJson.parseObject(User.class,
                json);
        mTvContent.setText(user.toString());

    }

    private void modeToJson() {
        User user=new User("李易峰",333L,true);
        List<User.Info.Hobby> list=new ArrayList<>();
        list.add(new User.Info.Hobby("旅游","http://www.baidu.com"));
        list.add(new User.Info.Hobby("阅读","http://www.baidu.com"));
        User.Info info=new User.Info(29,"1988-12-1",181,list);
        user.setInfos(info);
        String json = cn.gxh.jsongxh.FastJson.toJson(user);
        Log.d("---toJson---",json);
        mTvContent.setText(json);
    }
}
