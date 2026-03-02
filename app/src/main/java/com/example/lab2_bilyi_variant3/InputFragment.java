package com.example.lab2_bilyi_variant3;

import android.os.Bundle;
import android.view.*;
import android.widget.*;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class InputFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_input, container, false);
        DataViewModel model = new ViewModelProvider(requireActivity()).get(DataViewModel.class);

        EditText edit = v.findViewById(R.id.inputEditText);
        RadioGroup group = v.findViewById(R.id.fontRadioGroup);
        Button ok = v.findViewById(R.id.btnOk);

        ok.setOnClickListener(view -> {
            String text = edit.getText().toString();
            int checked = group.getCheckedRadioButtonId();
            if (text.isEmpty() || checked == -1) {
                Toast.makeText(getActivity(), "Заповніть все!", Toast.LENGTH_SHORT).show();
                return;
            }
            model.inputText.setValue(text);
            model.selectedFont.setValue(checked);
        });

        model.clearFormSignal.observe(getViewLifecycleOwner(), clear -> {
            if (clear) { edit.setText(""); group.clearCheck(); }
        });
        return v;
    }
}