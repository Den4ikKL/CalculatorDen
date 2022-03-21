package com.example.calculatorrr;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_clean,btn_del,btn_divide,btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,
            btn_multiply,btn_add,btn_minus,btn_point,btn_equal;
    TextView textView;
    boolean clear_flag; // знімаємо прапореуь
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        btn_0 = findViewById (R.id.btn_0);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_multiply = findViewById(R.id.btn_multiply);
        btn_divide = findViewById(R.id.btn_divide);
        btn_add = findViewById(R.id.btn_add);
        btn_minus = findViewById(R.id.btn_minus);
        btn_point = findViewById(R.id.btn_point);
        btn_del =findViewById(R.id.btn_del);
        btn_equal = findViewById(R.id.btn_equal);
        btn_clean = findViewById(R.id.btn_clean);

        textView = findViewById(R.id.textView);

        btn_0.setOnClickListener (this); // ініціалізація (оголошення) подій
        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_multiply.setOnClickListener(this);
        btn_del.setOnClickListener(this);
        btn_divide.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_add.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_clean.setOnClickListener(this);
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    public void onClick(View v) {
        String str = textView.getText().toString();
        switch(v.getId ()){
            case R.id.btn_0:
            case R.id.btn_1:
            case R.id.btn_2:
            case R.id.btn_3:
            case R.id.btn_4:
            case R.id.btn_5:
            case R.id.btn_6:
            case R.id.btn_7:
            case R.id.btn_8:
            case R.id.btn_9:
            case R.id.btn_point:
                if(clear_flag){
                    clear_flag=false;
                    str="";
                    textView.setText ("");
                }
                textView.setText(str+((Button)v).getText ());
                break;

            case R.id.btn_add:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                if(clear_flag){
                    clear_flag=false;
                    textView.setText("");
                }
                textView.setText(str+" "+((Button)v).getText()+" ");
                break;
            case R.id.btn_del:
                if(clear_flag){
                    clear_flag=false;
                    textView.setText ("");
                }else if (str != null && !str.equals ("")){
                    textView.setText (str.substring (0, str.length () - 1)); // Видаляємо символ
                }
                break;
            case R.id.btn_clean:
                clear_flag=false;
                str = "";
                textView.setText (""); // Встановлюємо пустий текст
                break;
            case R.id.btn_equal:
                getResult (); //Отримуємо результат
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void getResult () {// Алгоритм
        String s = textView.getText().toString();
        if(s == null  || s.equals ("")){
            return;
        }
        if (!s.contains("")){
            return;
        }
        if (clear_flag){
            clear_flag=false;
            return;
        }
        clear_flag=true;
        String [] str = s.split(" ");

        double result = 0;
        if (!str[0].equals ("")  && !str[2].equals ("")){
            double num1 = Double.parseDouble (str[0]);
            double num2 = Double.parseDouble(str[2]);

            if (str[1].equals ("+")){
                result = num1 + num2;
            }else if (str[1].equals ("-")){
                result = num1 - num2;
            }else if (str[1].equals ("/")){
                if (num2 == 0){
                    result = 0;
                }else {
                    result = num1/num2;
                }
            }else if (str[1].equals ("*")){
                result = num1*num2;
            }

            textView.setText(result+"");

        }else if (!str[0].equals ("") && str[2].equals ("")){
            textView.setText (s);
        }else if (str[0].equals ("") && !str[2].equals ("")){
            double num2 = Double.parseDouble(str[2]);
            if (s.equals ("+")){
                result = 0 + num2;
            }else if (s.equals("-")){
                result = 0 - num2;
            }else if (s.equals("×")){
                result = 0;
            }else if (s.equals("÷")){
                result = 0;
            }
            if (!str[2].contains (".")) {
                int r = (int) result;
                textView.setText (r + "");
            } else {
                textView.setText (result + "");
            }
        } else {
            textView.setText ("");
        }
    }
}