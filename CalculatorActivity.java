package com.example.scribesupport.home;

import static java.security.AccessController.getContext;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.GestureDetectorCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.scribesupport.R;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;

import android.speech.tts.TextToSpeech;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.kuldeep.calculator.R;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import net.objecthunter.exp4j.ValidationResult;

import java.util.ArrayList;
import java.util.Locale;


public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener, GestureDetector.OnGestureListener {

    TextView textview;

    ImageView imgbtn_microphone;
    ImageView imgbtn_modulo;
    ImageView imgbtn_minus;
    ImageView imgbtn_multiply;
    ImageView imgbtn_add;
    ImageView imgbtn_delete;
    ImageView imgbtn_divide;
    ImageView imgbtn_equalsto;
    ImageView imgbtn_squareroot;
    Button btn_0;
    Button btn_1;
    Button btn_2;
    Button btn_3;
    Button btn_4;
    Button btn_5;
    Button btn_6;
    Button btn_7;
    Button btn_8;
    Button btn_9;
    Button btn_point;
    Button btn_ac;
    Button btn_power;
    Button btn_sin;
    Button btn_cos;
    Button btn_tan;
    Button btn_ln;
    Button btn_leftparenthesis;
    Button btn_rightparenthesis;

    private final int REQ_CODE_SPEECH_INPUT = 100;

    private TextToSpeech textToSpeech;
    private GestureDetectorCompat gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        imgbtn_microphone = findViewById(R.id.imgbtn_microphone);
        imgbtn_minus = findViewById(R.id.imgbtn_minus);
        imgbtn_add = findViewById(R.id.imgbtn_add);
        imgbtn_multiply = findViewById(R.id.imgbtn_multiply);
        imgbtn_modulo = findViewById(R.id.imgbtn_modulo);
        imgbtn_delete = findViewById(R.id.imgbtn_delete);
        imgbtn_equalsto = findViewById(R.id.imgbtn_equalsto);
        imgbtn_divide = findViewById(R.id.imgbtn_divide);
        imgbtn_squareroot = findViewById(R.id.imgbtn_squareroot);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        btn_6 = findViewById(R.id.btn_6);
        btn_7 = findViewById(R.id.btn_7);
        btn_8 = findViewById(R.id.btn_8);
        btn_9 = findViewById(R.id.btn_9);
        btn_0 = findViewById(R.id.btn_0);
        btn_ac = findViewById(R.id.btn_ac);
        btn_point = findViewById(R.id.btn_point);
        btn_sin = findViewById(R.id.btn_sin);
        btn_cos = findViewById(R.id.btn_cos);
        btn_tan = findViewById(R.id.btn_tan);
        btn_power = findViewById(R.id.btn_power);
        btn_ln = findViewById(R.id.btn_ln);
        btn_leftparenthesis = findViewById(R.id.btn_leftparenthesis);
        btn_rightparenthesis = findViewById(R.id.btn_rightparenthesis);

        textview = findViewById(R.id.txtScreen);

        btn_1.setOnClickListener(this);
        btn_2.setOnClickListener(this);
        btn_3.setOnClickListener(this);
        btn_4.setOnClickListener(this);
        btn_5.setOnClickListener(this);
        btn_6.setOnClickListener(this);
        btn_7.setOnClickListener(this);
        btn_8.setOnClickListener(this);
        btn_9.setOnClickListener(this);
        btn_0.setOnClickListener(this);
        btn_power.setOnClickListener(this);
        btn_point.setOnClickListener(this);
        btn_sin.setOnClickListener(this);
        btn_cos.setOnClickListener(this);
        btn_tan.setOnClickListener(this);
        btn_ln.setOnClickListener(this);
        btn_leftparenthesis.setOnClickListener(this);
        btn_rightparenthesis.setOnClickListener(this);
        btn_ac.setOnClickListener(this);
        imgbtn_squareroot.setOnClickListener(this);
        imgbtn_minus.setOnClickListener(this);
        imgbtn_add.setOnClickListener(this);
        imgbtn_multiply.setOnClickListener(this);
        imgbtn_divide.setOnClickListener(this);
        imgbtn_modulo.setOnClickListener(this);
        imgbtn_equalsto.setOnClickListener(this);
        imgbtn_delete.setOnClickListener(this);
        imgbtn_microphone.setOnClickListener(this);


        textToSpeech=new TextToSpeech(CalculatorActivity.this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                textToSpeech.setLanguage(Locale.US);
                textToSpeech.speak("You'r in calculator activity,tap on the top to speak",TextToSpeech.QUEUE_ADD,null,null);
            }
        });

        gestureDetector=new GestureDetectorCompat(this,this);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    public void onClick(View v) {
        if (v.getId() == R.id.imgbtn_microphone) {
            imgbtn_microphone.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            promptSpeechInput();
        } else if (v.getId() == R.id.imgbtn_multiply) {
            imgbtn_multiply.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append("*");
        } else if (v.getId() == R.id.imgbtn_divide) {
            imgbtn_divide.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append("/");
        } else if (v.getId() == R.id.imgbtn_add) {
            imgbtn_add.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append("+");
        } else if (v.getId() == R.id.imgbtn_minus) {
            imgbtn_minus.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append("-");
        } else if (v.getId() == R.id.imgbtn_modulo) {
            imgbtn_modulo.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append("%");
        } else if (v.getId() == R.id.imgbtn_squareroot) {
            imgbtn_squareroot.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append("sqrt(");
        } else if (v.getId() == R.id.imgbtn_delete) {
            imgbtn_delete.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            if (textview.length() > 0) {
                String tempString = textview.getText().toString();
                StringBuilder tempStringBuilder = new StringBuilder(tempString);
                tempStringBuilder.deleteCharAt(tempString.length() - 1);
                textview.setText(tempStringBuilder);
            }
        } else if (v.getId() == R.id.imgbtn_equalsto) {
            imgbtn_equalsto.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            try {
                solve(textview.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (v.getId() == R.id.btn_1) {
            btn_1.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append(btn_1.getText());
        } else if (v.getId() == R.id.btn_2) {
            btn_2.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append(btn_2.getText());
        } else if (v.getId() == R.id.btn_3) {
            btn_3.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append(btn_3.getText());
        } else if (v.getId() == R.id.btn_4) {
            btn_4.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append(btn_4.getText());
        } else if (v.getId() == R.id.btn_5) {
            btn_5.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append(btn_5.getText());
        } else if (v.getId() == R.id.btn_6) {
            btn_6.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append(btn_6.getText());
        } else if (v.getId() == R.id.btn_7) {
            btn_7.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append(btn_7.getText());
        } else if (v.getId() == R.id.btn_8) {
            btn_8.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append(btn_8.getText());
        } else if (v.getId() == R.id.btn_9) {
            btn_9.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append(btn_9.getText());
        } else if (v.getId() == R.id.btn_0) {
            btn_0.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append(btn_0.getText());
        } else if (v.getId() == R.id.btn_ac) {
            btn_ac.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.setText("");
        } else if (v.getId() == R.id.btn_point) {
            btn_point.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append(btn_point.getText());
        } else if (v.getId() == R.id.btn_power) {
            btn_power.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append(btn_power.getText());
        } else if (v.getId() == R.id.btn_sin) {
            btn_sin.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append("sin(");
        } else if (v.getId() == R.id.btn_cos) {
            btn_cos.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append("cos(");
        } else if (v.getId() == R.id.btn_tan) {
            btn_tan.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append("tan(");
        } else if (v.getId() == R.id.btn_leftparenthesis) {
            btn_leftparenthesis.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append("(");
        } else if (v.getId() == R.id.btn_rightparenthesis) {
            btn_tan.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append(")");
        } else if (v.getId() == R.id.btn_ln) {
            btn_ln.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_alpha));
            textview.append("log(");
        }
    }


    private void solve(String s) throws Exception {
        try {
            Expression e = new ExpressionBuilder(s).build();
            ValidationResult vr = e.validate();
            if (vr.isValid()) {
                double result = e.evaluate();
                String answer = Double.toString(result);
                textview.setText(answer);
                textToSpeech.speak("The result is"+answer,TextToSpeech.QUEUE_ADD,null,null);

            }
        }
        catch(Exception excep)
        {
            textview.setText("Syntax Error");
        }
    }


    /**
     * Showing google speech input dialog
     * */
    private void promptSpeechInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                getString(R.string.speech_prompt));
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    getString(R.string.speech_not_supported),
                    Toast.LENGTH_SHORT).show();
        }
    }
    /**
     * Receiving speech input
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    String change=result.get(0);
                    change=change.replace("one","1");
                    change=change.replace("two","2");
                    change=change.replace("three","3");
                    change=change.replace("four","4");
                    change=change.replace("five","5");
                    change=change.replace("six","6");
                    change=change.replace("seven","7");
                    change=change.replace("eight","8");
                    change=change.replace("nine","9");
                    change=change.replace("ten","10");
                    change=change.replace("zero","0");
                    change=change.replace("X","*");
                    change=change.replace("add","+");
                    change=change.replace("subtracted by","-");
                    change=change.replace("to","2");
                    change=change.replace(" plus ","+");
                    change=change.replace(" minus ","-");
                    change=change.replace(" times ","*");
                    change=change.replace(" into ","*");
                    change=change.replace(" in2 ","*");
                    change=change.replace(" multiply by ","*");
                    change=change.replace(" divide by ","/");
                    change=change.replace("divide","/");
                    change=change.replace("percent of","*0.01");
                    change=change.replace("% of","*0.01*");
                    change=change.replace("is equal to","=");
                    change=change.replace("is equal 2","=");
                    change=change.replace("is equal","=");
                    change=change.replace("is","=");
                    change=change.replace("equals","=");
                    change=change.replace("equal","=");
                    change=change.replace("equals to","=");

                    if(change.contains("=")){
                        change=change.replace("=","");
                        textview.setText(change);
                        textToSpeech.speak("Your expression is,"+ change,TextToSpeech.QUEUE_ADD,null,null);
                        try {
                            solve(change);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }else{
                        textview.setText(change);
                    }
                }
                break;
            }

        }
    }

    @Override
    public boolean onDown(@NonNull MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(@NonNull MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(@NonNull MotionEvent e) {
        promptSpeechInput();
        return true;
    }

    @Override
    public boolean onScroll(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(@NonNull MotionEvent e) {

    }

    @Override
    public boolean onFling(@Nullable MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}