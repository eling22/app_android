package com.example.eling.first_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {
    EditText h;
    EditText w;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        h = (EditText)findViewById(R.id.et1);
        w = (EditText)findViewById(R.id.et2);
        Button submit = (Button)findViewById(R.id.button);
        submit.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View arg0){
                if(!("".equals(h.getText().toString()) || "".equals(w.getText().toString()) ))
                {
                    float fh= Float.parseFloat(h.getEditableText().toString());
                    float fw= Float.parseFloat(w.getEditableText().toString());
                    float fresult;
                    TextView result = (TextView)findViewById(R.id.tv3);
                    fh = fh/100;
                    fh = fh*fh;
                    NumberFormat uf = NumberFormat.getInstance();
                    uf.setMaximumFractionDigits(2);
                    fresult = fw/fh;
                    result.setText(uf.format(fresult) + "");
                    TextView dia= (TextView)findViewById(R.id.tv4);

                    if (fresult<18.5)
                        dia.setText("體重過輕");
                    else if (18.5 <= fresult && fresult< 24)
                        dia.setText("正常範圍");
                    else if (24 <=fresult && fresult < 27)
                        dia.setText("過    重");
                    else if (27 <=fresult && fresult < 30)
                        dia.setText("輕度肥胖");
                    else if (30 <= fresult && fresult < 35)
                        dia.setText("中度肥胖");
                    else if (fresult >= 35)
                        dia.setText("重度肥胖        ");

                }
            }
        });

        // Example of a call to a native method
        //TextView tv = (TextView) findViewById(R.id.sample_text);
        //tv.setText(stringFromJNI());
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}
