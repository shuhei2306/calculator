package com.example.shuhei.calculator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.math.BigDecimal;

import static java.lang.Math.*;


public class MainActivity extends ActionBarActivity {

//    テスト

    BigDecimal number1 = new BigDecimal("0");
    BigDecimal number2 = new BigDecimal("0");
    BigDecimal answer = new BigDecimal("0");
    int ope;
    int sw_point_num1;
    int sw_point_num2;
    String zero;
    String zero2;

    TextView num1Text;
    TextView opeText;
    TextView num2Text;
    TextView answerText;
    private View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        number1 = new BigDecimal("0");
        number2 = new BigDecimal("0");
        answer = new BigDecimal("0");
        ope=0;
        sw_point_num1 = 0;
        sw_point_num2 = 0;
        /*
        number1 =0;
        number2 =0;
        answer = 0;
        ope = 0;
        */

        //ラベル関連付け
        num1Text = (TextView)findViewById(R.id.num1);
        opeText = (TextView)findViewById(R.id.ope);
        num2Text = (TextView)findViewById(R.id.num2);
        answerText = (TextView)findViewById(R.id.answer);

        //ラベルの状態を初期状態に
        num1Text.setText("0");
        opeText.setText("");
        num2Text.setText("");
        answerText.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void btplus(View v) {
        ope=1;
        opeText.setText("+");
        opeinitilize();
    }
    public void btminus(View v) {
        ope=2;
        opeText.setText("-");
        opeinitilize();
    }
    public void btmultiply(View v) {
        ope=3;
        opeText.setText("×");
        opeinitilize();
    }
    public void btdivide(View v) {
        ope=4;
        opeText.setText("÷");
        opeinitilize();
    }
    public void btpoint(View v){
        if(ope <= 0) {
            sw_point_num1=1;
            num1Text.setText(number1 + ".");
        }
        else if(ope > 0){
            sw_point_num2=1;
            num2Text.setText(number2 + ".");
        }
    }
    public void btequal(View v) {
        if(ope == 1) {
            answer = new BigDecimal("0");
            answer = answer.add(number1.add(number2));

            //answer = number1 + number2;
            answerText.setText("="+answer);
            ope = -1;
        }
        else if(ope == 2) {
            answer = new BigDecimal("0");
            answer = answer.add(number1.subtract(number2));
           // answer = number1 - number2;
            answerText.setText("="+answer);
            ope = -1;
        }
        else if(ope == 3) {
            answer = new BigDecimal("0");
            answer = answer.add(number1.multiply(number2));
            //answer = number1 * number2;
            answerText.setText("="+answer);
            ope = -1;
        }
        else if(ope == 4) {
            if(number2.intValue() != 0) {
                answer = new BigDecimal("0");
                answer = answer.add(number1.divide(number2, 2, BigDecimal.ROUND_HALF_UP));


                //answer = number1 / number2;
                answerText.setText("=" + answer);
                ope = -1;
            }
            else{
                answerText.setText("= ERROR");
                ope = -1;
            }
        }
    }
    public void bt0(View v)
    {
        if(ope == 0) {


            if(sw_point_num1 == 0) {
                //number1 = number1 * 10 + 0;
                number1 = number1.multiply(BigDecimal.valueOf(10));
                num1Text.setText(number1 + "");
            }
            else{
               // number1 = number1.add(BigDecimal.valueOf(Math.pow( 0.1, sw_point_num1 ) ));
                zero="";
                for(int i =0; i<sw_point_num1; i++)
                {
                    zero += "0";
                }
                num1Text.setText(number1 + zero);
                sw_point_num1+=1;
            }
        }
        else if(ope > 0) {
            if (sw_point_num2 == 0) {
                //number1 = number1 * 10 + 0;
                number2 = number2.multiply(BigDecimal.valueOf(10));
                num1Text.setText(number2 + "");
            } else {
                //number2 = number2.add(BigDecimal.valueOf(Math.pow(0.1, sw_point_num2)));
                zero="";
                for(int i =0; i<sw_point_num2; i++)
                {
                    zero += "0";
                }
                num2Text.setText(number2 +zero2 );
                sw_point_num2 += 1;
            }
        }
        else
        {
            initilize();
         //   number1 = number1.multiply(BigDecimal.valueOf(10));
            number1= number1.add(BigDecimal.valueOf(0));
            num1Text.setText(number1 + "");
        }
    }

    public void bt1(View v)
    {
        if(ope == 0) {

            if(sw_point_num1 == 0) {
                //number1 = number1 * 10 + 0;
                number1 = number1.multiply(BigDecimal.valueOf(10));
                number1= number1.add(BigDecimal.valueOf(1));
                num1Text.setText(number1 + "");
            }
            else{
                number1 = number1.add(new BigDecimal(0.1).pow(sw_point_num1));
                number1 = number1.setScale(sw_point_num1, BigDecimal.ROUND_HALF_DOWN);
                num1Text.setText(number1 + "");
                sw_point_num1+=1;
            }
        }
        else if(ope > 0) {
            if (sw_point_num2 == 0) {
                //number1 = number1 * 10 + 0;
                number2 = number2.multiply(BigDecimal.valueOf(10));
                number2= number2.add(BigDecimal.valueOf(1));
                num2Text.setText(number2 + "");
            } else {
                number2 = number2.add(new BigDecimal(0.1).pow(sw_point_num1));
                number2 = number2.setScale(sw_point_num2, BigDecimal.ROUND_HALF_DOWN);
                num2Text.setText(number2 + "");
                sw_point_num2 += 1;
            }
        }
        else
        {
                initilize();
            //number1 = number1.multiply(BigDecimal.valueOf(10));
            number1= number1.add(BigDecimal.valueOf(1));
            num1Text.setText(number1 + "");
        }

    }

    public void bt2(View v)
    {
        if(ope == 0) {


            if(sw_point_num1 == 0) {
                //number1 = number1 * 10 + 0;
                number1 = number1.multiply(BigDecimal.valueOf(10));
                number1= number1.add(BigDecimal.valueOf(2));
                num1Text.setText(number1 + "");
            }
            else{
                number1 = number1.add(new BigDecimal("2").multiply(new BigDecimal(0.1).pow(sw_point_num1)));
                number1 = number1.setScale(sw_point_num1, BigDecimal.ROUND_HALF_DOWN);
                num1Text.setText(number1 + "");
                sw_point_num1+=1;
            }
        }
        else if(ope > 0) {
            if (sw_point_num2 == 0) {
                //number1 = number1 * 10 + 0;
                number2 = number2.multiply(BigDecimal.valueOf(10));
                number2= number2.add(BigDecimal.valueOf(2));
                num2Text.setText(number2 + "");
            } else {
                number2 = number2.add(new BigDecimal("2").multiply(new BigDecimal(0.1).pow(sw_point_num2)));
                number2 = number2.setScale(sw_point_num2, BigDecimal.ROUND_HALF_DOWN);
                num2Text.setText(number2 + "");
                sw_point_num2 += 1;
            }
        }
        else
        {
            initilize();
            number1 = number1.multiply(BigDecimal.valueOf(10));
            number1= number1.add(BigDecimal.valueOf(2));
            num1Text.setText(number1 + "");
        }

    }

    public void bt3(View v)
    {
        if(ope == 0) {


            if(sw_point_num1 == 0) {
                //number1 = number1 * 10 + 0;
                number1 = number1.multiply(BigDecimal.valueOf(10));
                number1= number1.add(BigDecimal.valueOf(3));
                num1Text.setText(number1 + "");
            }
            else{
                number1 = number1.add(new BigDecimal("3").multiply(new BigDecimal(0.1).pow(sw_point_num1)));
                number1 = number1.setScale(sw_point_num1, BigDecimal.ROUND_HALF_DOWN);
                num1Text.setText(number1 + "");
                sw_point_num1+=1;
            }
        }
        else if(ope > 0) {
            if (sw_point_num2 == 0) {
                //number1 = number1 * 10 + 0;
                number2 = number2.multiply(BigDecimal.valueOf(10));
                number2= number2.add(BigDecimal.valueOf(3));
                num2Text.setText(number2 + "");
            } else {
                number2 = number2.add(new BigDecimal("3").multiply(new BigDecimal(0.1).pow(sw_point_num2)));
                number2 = number2.setScale(sw_point_num2, BigDecimal.ROUND_HALF_DOWN);
                num2Text.setText(number2 + "");
                sw_point_num2 += 1;
            }
        }
        else
        {
            initilize();
            number1 = number1.multiply(BigDecimal.valueOf(10));
            number1= number1.add(BigDecimal.valueOf(3));
            num1Text.setText(number1 + "");
        }
    }

    public void bt4(View v) {
        if(ope == 0) {


            if(sw_point_num1 == 0) {
                //number1 = number1 * 10 + 0;
                number1 = number1.multiply(BigDecimal.valueOf(10));
                number1= number1.add(BigDecimal.valueOf(4));
                num1Text.setText(number1 + "");
            }
            else{
                number1 = number1.add(new BigDecimal("4").multiply(new BigDecimal(0.1).pow(sw_point_num1)));
                number1 = number1.setScale(sw_point_num1, BigDecimal.ROUND_HALF_DOWN);
                num1Text.setText(number1 + "");
                sw_point_num1+=1;
            }
        }
        else if(ope > 0) {
            if (sw_point_num2 == 0) {
                //number1 = number1 * 10 + 0;
                number2 = number2.multiply(BigDecimal.valueOf(10));
                number2= number2.add(BigDecimal.valueOf(4));
                num2Text.setText(number2 + "");
            } else {
                number2 = number2.add(new BigDecimal("4").multiply(new BigDecimal(0.1).pow(sw_point_num2)));
                number2 = number2.setScale(sw_point_num2, BigDecimal.ROUND_HALF_DOWN);
                num2Text.setText(number2 + "");
                sw_point_num2 += 1;
            }
        }
        else
        {
            initilize();
            number1 = number1.multiply(BigDecimal.valueOf(10));
            number1= number1.add(BigDecimal.valueOf(4));
            num1Text.setText(number1 + "");
        }
    }

    public void bt5(View v)
    {
        if(ope == 0) {


            if(sw_point_num1 == 0) {
                //number1 = number1 * 10 + 0;
                number1 = number1.multiply(BigDecimal.valueOf(10));
                number1= number1.add(BigDecimal.valueOf(5));
                num1Text.setText(number1 + "");
            }
            else{
                number1 = number1.add(new BigDecimal("5").multiply(new BigDecimal(0.1).pow(sw_point_num1)));
                number1 = number1.setScale(sw_point_num1, BigDecimal.ROUND_HALF_DOWN);
                num1Text.setText(number1 + "");
                sw_point_num1+=1;
            }
        }
        else if(ope > 0) {
            if (sw_point_num2 == 0) {
                //number1 = number1 * 10 + 0;
                number2 = number2.multiply(BigDecimal.valueOf(10));
                number2= number2.add(BigDecimal.valueOf(5));
                num2Text.setText(number2 + "");
            } else {
                number2 = number2.add(new BigDecimal("5").multiply(new BigDecimal(0.1).pow(sw_point_num2)));
                number2 = number2.setScale(sw_point_num2, BigDecimal.ROUND_HALF_DOWN);
                num2Text.setText(number2 + "");
                sw_point_num2 += 1;
            }
        }
        else
        {
            initilize();
            number1 = number1.multiply(BigDecimal.valueOf(10));
            number1= number1.add(BigDecimal.valueOf(5));
            num1Text.setText(number1 + "");
        }
    }

    public void bt6(View v)
    {
        if(ope == 0) {


            if(sw_point_num1 == 0) {
                //number1 = number1 * 10 + 0;
                number1 = number1.multiply(BigDecimal.valueOf(10));
                number1= number1.add(BigDecimal.valueOf(6));
                num1Text.setText(number1 + "");
            }
            else{
                number1 = number1.add(new BigDecimal("6").multiply(new BigDecimal(0.1).pow(sw_point_num1)));
                number1 = number1.setScale(sw_point_num1, BigDecimal.ROUND_HALF_DOWN);
                num1Text.setText(number1 + "");
                sw_point_num1+=1;
            }
        }
        else if(ope > 0) {
            if (sw_point_num2 == 0) {
                //number1 = number1 * 10 + 0;
                number2 = number2.multiply(BigDecimal.valueOf(10));
                number2= number2.add(BigDecimal.valueOf(6));
                num2Text.setText(number2 + "");
            } else {
                number2 = number2.add(new BigDecimal("6").multiply(new BigDecimal(0.1).pow(sw_point_num2)));
                number2 = number2.setScale(sw_point_num2, BigDecimal.ROUND_HALF_DOWN);
                num2Text.setText(number2 + "");
                sw_point_num2 += 1;
            }
        }
        else
        {
            initilize();
            number1 = number1.multiply(BigDecimal.valueOf(10));
            number1= number1.add(BigDecimal.valueOf(6));
            num1Text.setText(number1 + "");
        }
    }

    public void bt7(View v)
    {
        if(ope == 0) {

            if(sw_point_num1 == 0) {
                //number1 = number1 * 10 + 0;
                number1 = number1.multiply(BigDecimal.valueOf(10));
                number1= number1.add(BigDecimal.valueOf(7));
                num1Text.setText(number1 + "");
            }
            else{
                number1 = number1.add(new BigDecimal("7").multiply(new BigDecimal(0.1).pow(sw_point_num1)));
                number1 = number1.setScale(sw_point_num1, BigDecimal.ROUND_HALF_DOWN);
                num1Text.setText(number1 + "");
                sw_point_num1+=1;
            }
        }
        else if(ope > 0) {
            if (sw_point_num2 == 0) {
                //number1 = number1 * 10 + 0;
                number2 = number2.multiply(BigDecimal.valueOf(10));
                number2= number2.add(BigDecimal.valueOf(7));
                num2Text.setText(number2 + "");
            } else {
                number2 = number2.add(new BigDecimal("7").multiply(new BigDecimal(0.1).pow(sw_point_num2)));
                number2 = number2.setScale(sw_point_num2, BigDecimal.ROUND_HALF_DOWN);
                num2Text.setText(number2 + "");
                sw_point_num2 += 1;
            }
        }
        else
        {
            initilize();
            number1 = number1.multiply(BigDecimal.valueOf(10));
            number1= number1.add(BigDecimal.valueOf(7));
            num1Text.setText(number1 + "");
        }
    }

    public void bt8(View v)
    {
        if(ope == 0) {


            if(sw_point_num1 == 0) {
                //number1 = number1 * 10 + 0;
                number1 = number1.multiply(BigDecimal.valueOf(10));
                number1= number1.add(BigDecimal.valueOf(8));
                num1Text.setText(number1 + "");
            }
            else{
                number1 = number1.add(new BigDecimal("8").multiply(new BigDecimal(0.1).pow(sw_point_num1)));
                number1 = number1.setScale(sw_point_num1, BigDecimal.ROUND_HALF_DOWN);
                num1Text.setText(number1 + "");
                sw_point_num1+=1;
            }
        }
        else if(ope > 0) {
            if (sw_point_num2 == 0) {
                //number1 = number1 * 10 + 0;
                number2 = number2.multiply(BigDecimal.valueOf(10));
                number2= number2.add(BigDecimal.valueOf(8));
                num2Text.setText(number2 + "");
            } else {
                number2 = number2.add(new BigDecimal("8").multiply(new BigDecimal(0.1).pow(sw_point_num2)));
                number2 = number2.setScale(sw_point_num2, BigDecimal.ROUND_HALF_DOWN);
                num2Text.setText(number2 + "");
                sw_point_num2 += 1;
            }
        }
        else
        {
            initilize();
            number1 = number1.multiply(BigDecimal.valueOf(10));
            number1= number1.add(BigDecimal.valueOf(8));
            num1Text.setText(number1 + "");
        }
    }

    public void bt9(View v)
    {
        if(ope == 0) {


            if(sw_point_num1 == 0) {
                //number1 = number1 * 10 + 0;
                number1 = number1.multiply(BigDecimal.valueOf(10));
                number1= number1.add(BigDecimal.valueOf(9));
                num1Text.setText(number1 + "");
            }
            else{
                number1 = number1.add(new BigDecimal("9").multiply(new BigDecimal(0.1).pow(sw_point_num1)));
                number1 = number1.setScale(sw_point_num1, BigDecimal.ROUND_HALF_DOWN);
                num1Text.setText(number1 + "");
                sw_point_num1+=1;
            }
        }
        else if(ope > 0) {
            if (sw_point_num2 == 0) {
                //number1 = number1 * 10 + 0;
                number2 = number2.multiply(BigDecimal.valueOf(10));
                number2= number2.add(BigDecimal.valueOf(9));
                num2Text.setText(number2 + "");
            } else {
                number2 = number2.add(new BigDecimal("9").multiply(new BigDecimal(0.1).pow(sw_point_num2)));
                number2 = number2.setScale(sw_point_num2, BigDecimal.ROUND_HALF_DOWN);
                num2Text.setText(number2 + "");
                sw_point_num2 += 1;
            }
        }
        else
        {
            initilize();
            number1 = number1.multiply(BigDecimal.valueOf(10));
            number1= number1.add(BigDecimal.valueOf(9));
            num1Text.setText(number1 + "");
        }
    }


    //初期化
    public void initilize()
    {
        /*
        number1 =0;
        number2 =0;
        answer = 0;
        ope = 0;
        */
        number1 = new BigDecimal("0");
        number2 = new BigDecimal("0");
        answer = new BigDecimal("0");
        ope=0;
        sw_point_num1=0;
        sw_point_num2=0;

        //ラベルの状態を初期状態に
        num1Text.setText("0");
        opeText.setText("");
        num2Text.setText("");
        answerText.setText("");

        zero = "";
        zero2 ="";
    }

    //初期化
    public void opeinitilize()
    {
        num2Text.setText("");
        answerText.setText("");
        answer = new BigDecimal("0");
        number2 = new BigDecimal("0");
        sw_point_num2=0;
        zero2 ="";
    }
}
