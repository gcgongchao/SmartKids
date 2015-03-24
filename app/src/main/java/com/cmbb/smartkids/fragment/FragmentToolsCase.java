package com.cmbb.smartkids.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.BaseFragment;

/**
 * Created by N.Sun
 */
public class FragmentToolsCase extends BaseFragment {

    private static final String TAG = FragmentToolsCase.class.getSimpleName();

    RadioButton btn_first_aid, btn_common_disease, btn_pain, btn_cold, btn_fever, btn_diarrhea,
            btn_anemia, btn_breath, btn_allergy, btn_infectious, btn_heatstroke;
    TextView btn_comfirm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tools_case, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        btn_first_aid = (RadioButton) rootView.findViewById(R.id.radio_first_aid);
        btn_first_aid.setOnClickListener(this);
        btn_common_disease = (RadioButton) rootView.findViewById(R.id.radio_common_disease);
        btn_common_disease.setOnClickListener(this);
        btn_pain = (RadioButton) rootView.findViewById(R.id.radio_pain);
        btn_pain.setOnClickListener(this);
        btn_cold = (RadioButton) rootView.findViewById(R.id.radio_cold);
        btn_cold.setOnClickListener(this);
        btn_fever = (RadioButton) rootView.findViewById(R.id.radio_fever);
        btn_fever.setOnClickListener(this);
        btn_diarrhea = (RadioButton) rootView.findViewById(R.id.radio_diarrhea);
        btn_diarrhea.setOnClickListener(this);
        btn_anemia = (RadioButton) rootView.findViewById(R.id.radio_anemia);
        btn_anemia.setOnClickListener(this);
        btn_breath = (RadioButton) rootView.findViewById(R.id.radio_breath);
        btn_breath.setOnClickListener(this);
        btn_allergy = (RadioButton) rootView.findViewById(R.id.radio_allergy);
        btn_allergy.setOnClickListener(this);
        btn_infectious = (RadioButton) rootView.findViewById(R.id.radio_infectious);
        btn_infectious.setOnClickListener(this);
        btn_heatstroke = (RadioButton) rootView.findViewById(R.id.radio_heatstroke);
        btn_heatstroke.setOnClickListener(this);
        btn_comfirm = (TextView) rootView.findViewById(R.id.btn_comfirm);
        btn_comfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.radio_first_aid:
                btn_first_aid.requestFocus();
                break;
            case R.id.radio_common_disease:
                btn_common_disease.requestFocus();
                break;
            case R.id.radio_pain:
                btn_pain.requestFocus();
                break;
            case R.id.radio_cold:
                btn_cold.requestFocus();
                break;
            case R.id.radio_fever:
                btn_fever.requestFocus();
                break;
            case R.id.radio_diarrhea:
                btn_diarrhea.requestFocus();
                break;
            case R.id.radio_anemia:
                btn_anemia.requestFocus();
                break;
            case R.id.radio_breath:
                btn_breath.requestFocus();
                break;
            case R.id.radio_allergy:
                btn_allergy.requestFocus();
                break;
            case R.id.radio_infectious:
                btn_infectious.requestFocus();
                break;
            case R.id.radio_heatstroke:
                btn_heatstroke.requestFocus();
                break;
            case R.id.btn_comfirm:
                btn_comfirm.requestFocus();
                break;
        }
    }
}
