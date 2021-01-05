package dev.alimansour.standardcalculator;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import dev.alimansour.standardcalculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private Double input1 = Double.NaN;
    private Double input2;
    private final char UNDEFINED = 'U';
    private final char ADDING = 'A';
    private final char MULTIPLICATION = 'M';
    private final char DIVISION = 'D';
    private final char SUBTRACT = 'S';
    private char operation = UNDEFINED;
    private boolean isPercentage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.button1.setOnClickListener(v -> addValue((Button) v));
        binding.button2.setOnClickListener(v -> addValue((Button) v));
        binding.button3.setOnClickListener(v -> addValue((Button) v));
        binding.button4.setOnClickListener(v -> addValue((Button) v));
        binding.button5.setOnClickListener(v -> addValue((Button) v));
        binding.button6.setOnClickListener(v -> addValue((Button) v));
        binding.button7.setOnClickListener(v -> addValue((Button) v));
        binding.button8.setOnClickListener(v -> addValue((Button) v));
        binding.button9.setOnClickListener(v -> addValue((Button) v));
        binding.buttonZero.setOnClickListener(v -> addValue((Button) v));
        binding.buttonPoint.setOnClickListener(v -> addValue((Button) v));
        binding.buttonPlus.setOnClickListener(v -> {
            calculate();
            operation = ADDING;
            binding.displayTextView.setText(null);
        });
        binding.buttonMinus.setOnClickListener(v -> {
            calculate();
            operation = SUBTRACT;
            binding.displayTextView.setText(null);
        });
        binding.buttonMultiply.setOnClickListener(v -> {
            calculate();
            operation = MULTIPLICATION;
            binding.displayTextView.setText(null);
        });
        binding.buttonDivide.setOnClickListener(v -> {
            calculate();
            operation = DIVISION;
            binding.displayTextView.setText(null);
        });
        binding.buttonPercentage.setOnClickListener(v -> isPercentage = true);
        binding.buttonClear.setOnClickListener(v -> {
            input1 = Double.NaN;
            input2 = Double.NaN;
            binding.displayTextView.setText(null);
            operation = UNDEFINED;
        });
        binding.buttonEqual.setOnClickListener(v -> {
            calculate();
            binding.displayTextView.setText(String.valueOf(input1));
            input1 = Double.NaN;
            operation = UNDEFINED;
            isPercentage = false;
        });
    }

    private void calculate() {
        if (Double.isNaN(input1)) {
            try {
                input1 = Double.parseDouble(binding.displayTextView.getText().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            input2 = Double.parseDouble(binding.displayTextView.getText().toString());
            binding.displayTextView.setText(null);
            double percent = 1;
            if (isPercentage) {
                percent = 0.01;
            }
            switch (operation) {
                case ADDING:
                    input1 = input1 + input2;
                    break;
                case SUBTRACT:
                    input1 = input1 - input2;
                    break;
                case MULTIPLICATION:
                    input1 = input1 * input2 * percent;
                    break;
                case DIVISION:
                    input1 = input1 / input2 * percent;
                    break;
            }
        }
    }

    private void addValue(Button button) {
        String value = binding.displayTextView.getText() + button.getText().toString();
        binding.displayTextView.setText(value);
    }
}