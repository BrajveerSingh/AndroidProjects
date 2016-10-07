package in.co.thingsdata.calculator;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private AlertDialog alertDialog;
    private final static String PLUS = "+";
    private final static String MINUS = "-";
    private final static String MULTIPLY = "*";
    private final static String DIVIDE = "/";
    private final static String EQUAL_TO = "=";
    private volatile boolean evaluated;
    private class Expression {
        int num1;
        String operator;
        int num2;
        public int getNum1() {
            return num1;
        }
        public void setNum1(int num1) {
            this.num1 = num1;
        }
        public String getOperator() {
            return operator;
        }
        public void setOperator(String operator) {
            this.operator = operator;
        }
        public int getNum2() {
            return num2;
        }
        public void setNum2(int num2) {
            this.num2 = num2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
            new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            }
        );
        final Expression expression = new Expression();
        final EditText num1 = (EditText) findViewById(R.id.editText1);
        final EditText num2 = (EditText) findViewById(R.id.editText2);
        final TextView answer = (TextView) findViewById(R.id.textView);

        final Button button0 = (Button) findViewById(R.id.button0);
        addListener(button0, num1, num2, answer,expression);
        final Button button1 = (Button) findViewById(R.id.button1);
        addListener(button1, num1, num2, answer,expression);
        final Button button2 = (Button) findViewById(R.id.button2);
        addListener(button2, num1, num2, answer,expression);
        final Button button3 = (Button) findViewById(R.id.button3);
        addListener(button3, num1, num2, answer,expression);
        final Button button4 = (Button) findViewById(R.id.button4);
        addListener(button4, num1, num2, answer,expression);
        final Button button5 = (Button) findViewById(R.id.button5);
        addListener(button5, num1, num2, answer,expression);
        final Button button6 = (Button) findViewById(R.id.button6);
        addListener(button6, num1, num2, answer,expression);
        final Button button7 = (Button) findViewById(R.id.button7);
        addListener(button7, num1, num2, answer,expression);
        final Button button8 = (Button) findViewById(R.id.button8);
        addListener(button8, num1, num2, answer,expression);
        final Button button9 = (Button) findViewById(R.id.button9);
        addListener(button9, num1, num2, answer,expression);
        final Button buttonC = (Button) findViewById(R.id.buttonC);
        addCommandListener(buttonC, num1, num2, answer, expression);
        final Button buttonPlus = (Button) findViewById(R.id.buttonPlus);
        addCommandListener(buttonPlus, num1, num2, answer, expression);
        final Button buttonMinus = (Button) findViewById(R.id.buttonMinus);
        addCommandListener(buttonMinus, num1, num2, answer, expression);
        final Button buttonMultiply = (Button) findViewById(R.id.buttonMultiply);
        addCommandListener(buttonMultiply, num1, num2, answer, expression);
        final Button buttonDivide = (Button) findViewById(R.id.buttonDevide);
        addCommandListener(buttonDivide, num1, num2, answer, expression);
        final Button buttonEqualTo = (Button) findViewById(R.id.buttonEqualTo);
        addCommandListener(buttonEqualTo, num1, num2, answer, expression);
    }
    private void setData(EditText num1, EditText num2, String value, TextView answer, Expression expression) throws Exception {
        if (evaluated) {
            alertDialog.setMessage("Click C to clear previous data.");
            alertDialog.show();
        }
       if (null == expression.getOperator()) {
           num1.setText(num1.getText().toString()+value);
           expression.setNum1(Integer.valueOf(num1.getText().toString()));
           answer.setText(answer.getText().toString() + value);
       } else {
           num2.setText(num2.getText().toString()+value);
           expression.setNum2(Integer.valueOf(num2.getText().toString()));
           answer.setText(answer.getText().toString() + value);
       }
    }
    private void addListener(final Button button, final EditText num1, final EditText num2, final TextView answer, final Expression expression) {

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            try {
            setData(num1, num2, button.getText().toString(), answer, expression);
            } catch (Exception e) {
                e.printStackTrace();
            }
            }
        });
    }
    private void addCommandListener(final Button button, final EditText num1, final EditText num2, final TextView answer, final Expression expression) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String value = button.getText().toString();
                    if (value.equals("C")) {
                        String empty = "";
                        answer.setText(empty);
                        num1.setText(empty);
                        num2.setText(empty);
                        expression.setOperator(null);
                        expression.setNum1(0);
                        expression.setNum2(0);
                        evaluated = false;
                        return;
                    }
                    processUserCommand(num1.getText().toString(), value, answer, expression);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void processUserCommand(String num1, String value, TextView answer, Expression expression) throws Exception {
        if (evaluated) {
            alertDialog.setMessage("Click C to clear previous data.");
            alertDialog.show();
        }
        switch (value) {
            case PLUS:
                answer.setText(num1 + PLUS);
                expression.setNum1(Integer.valueOf(num1));
                expression.setOperator(PLUS);
                break;
            case MINUS:
                answer.setText(num1 + MINUS);
                expression.setNum1(Integer.valueOf(num1));
                expression.setOperator(MINUS);
                break;
            case DIVIDE:
                answer.setText(num1 + DIVIDE);
                expression.setNum1(Integer.valueOf(num1));
                expression.setOperator(DIVIDE);
                break;
            case MULTIPLY:
                answer.setText(num1 + MULTIPLY);
                expression.setNum1(Integer.valueOf(num1));
                expression.setOperator(MULTIPLY);
                break;
            case EQUAL_TO:
                evaluateExpression(answer, expression);
                break;
        }
    }
    private void evaluateExpression (TextView answer, Expression expression) throws Exception {
        StringBuilder expressionStr = new StringBuilder(answer.getText().toString());
        String result = "";
        if (expressionStr.indexOf(PLUS) > -1) {
            result = String.valueOf(expression.getNum1() + expression.getNum2());
        } else if (expressionStr.indexOf(MINUS) > -1) {
            result = String.valueOf(expression.getNum1() - expression.getNum2());
        } else if (expressionStr.indexOf(MULTIPLY) > -1) {
            result = String.valueOf(expression.getNum1() * expression.getNum2());
        } else if (expressionStr.indexOf(DIVIDE) > -1) {
            if (expression.getNum2() == 0) {
                result = String.valueOf(Float.POSITIVE_INFINITY);
            } else {
                result = String.valueOf((float)expression.getNum1() / expression.getNum2());
            }
        } else {
            result = String.valueOf(expression.getNum1());
        }
        expressionStr.append(EQUAL_TO);
        expressionStr.append("\n"+result);
        answer.setText(expressionStr.toString());
        evaluated = true;
    }
    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
