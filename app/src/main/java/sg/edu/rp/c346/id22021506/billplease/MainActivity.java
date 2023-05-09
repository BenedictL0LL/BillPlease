package sg.edu.rp.c346.id22021506.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    TextView tvDisplay;
    TextView tvDisplay2;
    TextView tvDisplays1;
    TextView tvDisplays2;
    Button btnSplit;
    Button reset;
    EditText etInput;
    EditText etInput2;
    EditText etInput3;
    ToggleButton tglButton;
    ToggleButton tglButton2;
    RadioGroup rgPayment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvDisplay = findViewById(R.id.textView);
        tvDisplay2 = findViewById(R.id.textView2);
        tvDisplays1 = findViewById(R.id.textViewDisplay1);
        tvDisplays2= findViewById(R.id.textViewDisplay2);
        tglButton = findViewById(R.id.toggleButtonSVS);
        tglButton2 = findViewById(R.id.toggleButtonGST);
        etInput = findViewById(R.id.editTextInput);
        etInput2 = findViewById(R.id.editTextInput2);
        etInput3 = findViewById(R.id.editTextInput3);
        btnSplit =findViewById(R.id.button3);
        reset =findViewById(R.id.button4);
        rgPayment =findViewById(R.id.radioGroupPay);

        btnSplit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        if(etInput.getText().toString().trim().length()!=0 && etInput2.getText().toString().trim().length()!=0) {
            double total = 0.0;
            if (!tglButton.isChecked() && !tglButton2.isChecked()) {
                total = Double.parseDouble(etInput.getText().toString());
            } else if (tglButton.isChecked() && !tglButton2.isChecked()) {
                total = Double.parseDouble(etInput.getText().toString()) * 1.1;
            } else if (!tglButton.isChecked() && tglButton2.isChecked()) {
                total = Double.parseDouble(etInput.getText().toString()) * 1.07;
            } else {
                total = Double.parseDouble(etInput.getText().toString()) * 1.17;
            }

            if (etInput3.getText().toString().trim().length() != 0) {
                total *= 1 - Double.parseDouble(etInput3.getText().toString()) / 100;
            }
            tvDisplays1.setText("Total Bill: $" + String.format("%.2f", total));

            int numPerson = Integer.parseInt(etInput2.getText().toString());
            int checkedRadioId = rgPayment.getCheckedRadioButtonId();
            if (numPerson != 1 && checkedRadioId == R.id.radioButton) {
                tvDisplays2.setText("Each Pays: $" + String.format("%.2f", total / numPerson));
            } else if ((numPerson != 1 && checkedRadioId == R.id.radioButton2)) {
                tvDisplays2.setText("Each Pays: $" + String.format("%.2f %s", total / numPerson, "via Paynow to 91234567"));
            } else if ((numPerson == 1 && checkedRadioId == R.id.radioButton2)) {
                tvDisplays2.setText("Each Pays: $" + String.format("%.2f %s", total, "via Paynow to 91234567"));
            } else {
                tvDisplays2.setText("Each Pays: $" + total);
            }
        }

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etInput.setText("");
                etInput2.setText("");
                tglButton.setChecked(false);
                tglButton2.setChecked(false);
                etInput3.setText("");

            }
        });


    }
});
    }
}