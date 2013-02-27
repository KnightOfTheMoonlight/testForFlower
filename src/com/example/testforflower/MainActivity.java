package com.example.testforflower;
import  com.example.testforflower.R;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
 
 
public class MainActivity extends Activity {
  MyView mv;
@Override
            public void onCreate(Bundle savedInstanceState) {
                    super.onCreate(savedInstanceState);
                    setContentView(R.layout.activity_main);
            
                    
                    Bitmap mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.alice);
                    mv = (MyView) findViewById(R.id.myview);
                    mv.setImageBitmap(mBitmap);
  
                   
                    }
         
       
        
        
}
