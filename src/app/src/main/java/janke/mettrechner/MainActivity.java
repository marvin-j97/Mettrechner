package janke.mettrechner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextWatcher;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView input;
    TextView input2;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        input2 = findViewById(R.id.input2);
        result = findViewById(R.id.result);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                input.removeTextChangedListener(this);
                calculateMett();
                input.addTextChangedListener(this);
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        input2.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                input2.removeTextChangedListener(this);
                calculateMett();
                input2.addTextChangedListener(this);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void calculateMett() {
        // Sanitize input
        String cleanInput = input.getText().toString();
        String cleanInput2 = input2.getText().toString();
        System.out.println(input2);
        System.out.println(cleanInput2);

        if (cleanInput.length() >= 4) {
            cleanInput = "1000";
            input.setText("1000");
        }
        if (cleanInput2.length() >= 3) {
            cleanInput2 = "100";
            input2.setText("100");
        }

        if (cleanInput.equals(""))
            cleanInput = "0";

        if (cleanInput2.equals(""))
            cleanInput2 = "0";

        int broetchenHalfs = Integer.valueOf(cleanInput);
        int mettPerHalf = Integer.valueOf(cleanInput2);

        if (broetchenHalfs > 1000) {
            broetchenHalfs = 1000;
            input.setText(Integer.toString(broetchenHalfs));
        }

        if (mettPerHalf > 100) {
            mettPerHalf = 100;
            input2.setText(Integer.toString(mettPerHalf));
        }

        // Calculate
        int rawValue = 2 * broetchenHalfs * mettPerHalf; // 2, weil Brötchenhälften
        int roundNumber = roundUp(rawValue, 50);

        // Format and display
        String newText = Integer.toString(roundNumber)+"g";
        result.setText(newText);
    }

    int roundUp(int numToRound, int multiple)
    {
        if (multiple == 0)
            return numToRound;

        int remainder = numToRound % multiple;
        if (remainder == 0)
            return numToRound;

        return numToRound + multiple - remainder;
    }
}
