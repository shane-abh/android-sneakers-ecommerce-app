package com.example.project2.checkOutPage;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class ExpiryDateFormattingTextWatcher implements TextWatcher {

    private static final char SLASH_CHAR = '/';
    private static final int SLASH_POSITION = 2;

    private EditText editText;

    public ExpiryDateFormattingTextWatcher(EditText editText) {
        this.editText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        // Remove previous listeners to avoid infinite loop
        editText.removeTextChangedListener(this);

        // Get the current input without slashes
        String input = editable.toString().replaceAll("/", "");

        // Format the expiry date with a slash
        StringBuilder formattedInput = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            formattedInput.append(input.charAt(i));
            if ((i + 1) == SLASH_POSITION && (i + 1) != input.length()) {
                formattedInput.append(SLASH_CHAR);
            }
        }

        // Update the EditText with the formatted text
        editText.setText(formattedInput.toString());
        editText.setSelection(formattedInput.length());

        // Restore the TextWatcher
        editText.addTextChangedListener(this);
    }
}
