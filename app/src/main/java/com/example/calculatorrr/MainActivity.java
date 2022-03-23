package com.example.calculatorrr;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Оголошення об'єктів кнопок (які є на розмітці інтерфейсу)
    Button btn_clean,btn_del,btn_divide,btn_0,btn_1,btn_2,btn_3,btn_4,btn_5,btn_6,btn_7,btn_8,btn_9,
            btn_multiply,btn_add,btn_minus,btn_point,btn_equal;
    TextView textView; //Оголошення об'єктів текстового поля з результатом та вводом прикладів
    boolean clear_flag; // знімаємо прапореуь

    //Перевантажений метод (викликється при створені, побудови програми)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState); //батьківський метод, встановлюється збережений стан екземпляра
        setContentView (R.layout.activity_main); //встановлюється перший інтерфейс запуску програми
        //Кнопки. Ініціалізація даних кнопок за допомогою пошуку  по Id на формі (інтерфейсу)
        btn_0 = findViewById (R.id.btn_0); //0
        btn_1 = findViewById(R.id.btn_1);//1
        btn_2 = findViewById(R.id.btn_2);//2
        btn_3 = findViewById(R.id.btn_3);//3
        btn_4 = findViewById(R.id.btn_4);//4
        btn_5 = findViewById(R.id.btn_5);//5
        btn_6 = findViewById(R.id.btn_6);//6
        btn_7 = findViewById(R.id.btn_7);//7
        btn_8 = findViewById(R.id.btn_8);//8
        btn_9 = findViewById(R.id.btn_9);//9
        btn_multiply = findViewById(R.id.btn_multiply);//*
        btn_divide = findViewById(R.id.btn_divide);// /
        btn_add = findViewById(R.id.btn_add);// +
        btn_minus = findViewById(R.id.btn_minus); // -
        btn_point = findViewById(R.id.btn_point); // .
        btn_del =findViewById(R.id.btn_del); // видалення 1 символа
        btn_equal = findViewById(R.id.btn_equal); // = дорівнює
        btn_clean = findViewById(R.id.btn_clean); // очищення текстового поля з результатом

        textView = findViewById(R.id.textView); // ініціалізація текстового поля результату

        // ініціалізація (оголошення) подій при натиску на кнопці
        btn_0.setOnClickListener (this);
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

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"}) //Інтернаціоналізація
    public void onClick(View v) { // шаблонний медот при натиску на любу з кнопок з форми. Виконує пошук кнопки по якій було натиснено
        String str = textView.getText().toString(); //Беремо текст з поля результат
        switch(v.getId ()){ // шукаємо кнопку в кнпках з цифрами по id натиснутої кнопки
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
                if(clear_flag){ //якщо прапорець істинна то очищаємо текст та в результаті теж.
                    clear_flag=false; //прапорець встановлюємо на хибність
                    str="";
                    textView.setText ("");
                }
                textView.setText(str+((Button)v).getText ()); // додаємо до раніше введеного значання, значення натиснутої кнопки
                break;

            case R.id.btn_add:
            case R.id.btn_minus:
            case R.id.btn_multiply:
            case R.id.btn_divide:
                if(clear_flag){
                    clear_flag=false;
                    textView.setText("");
                }
                textView.setText(str+" "+((Button)v).getText()+" "); // встановлення знаку
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
                getResult (); //Отримуємо результат (метод)
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    private void getResult () {// Алгоритм отримання результату
        String s = textView.getText().toString(); //записується строка з прикладом
        if(s == null  || s.equals ("")){ // перевірка на пустоту
            return;
        }
        if (!s.contains("")){ // перевірка на пустоту ( ще одна)
            return;
        }
        if (clear_flag){ // якщо прапоресь істинна то встановлюємо хибним і виходимо
            //означає що приклад не повний
            clear_flag=false;
            return;
        }
        clear_flag=true;
        String [] str = s.split(" "); //розділяємо приклад на три частини

        double result = 0;
        if (!str[0].equals ("")  && !str[2].equals ("")){ //перевірка на пустоту
            double num1 = Double.parseDouble (str[0]); //конвертація в дабл 1- числа
            double num2 = Double.parseDouble(str[2]); //конвертація в дабл 2- числа

            //Пошук знаку та виконання відповідної дії по ньому
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

            textView.setText(result+""); //записується результат обчислення

        }else if (!str[0].equals ("") && str[2].equals ("")){ // перевірки на одна значення 0 (пустота)
            textView.setText (s);
        }else if (str[0].equals ("") && !str[2].equals ("")){// перевірки на одна значення 0
            double num2 = Double.parseDouble(str[2]); // обчислення такого прикладу з нулем
            if (s.equals ("+")){
                result = 0 + num2;
            }else if (s.equals("-")){
                result = 0 - num2;
            }else if (s.equals("×")){
                result = 0;
            }else if (s.equals("÷")){
                result = 0;
            }
            //перевірка чи дійсне число результату
            if (!str[2].contains (".")) {
                int r = (int) result; // конвертація в цілочисельний результат
                textView.setText (r + ""); // встановлення результату
            } else {
                textView.setText (result + ""); // встановлення результату
            }
        } else {
            textView.setText (""); // встановлення результату
        }
    }
}