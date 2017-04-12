package com.foo.digitalcampus;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReportFragment extends Fragment {

    private Spinner spSchools;
    private Spinner spCases;
    private String[] schools = {"明伦校区","金明校区","龙子湖校区"};
    private String[] cases = {"校园环境","公共设施","教学设备","办公设备","寝室","园林绿化","紧急突发事件","其他类型"};
    private ArrayAdapter<String> schoolAdapter;
    private ArrayAdapter<String> caseAdapter;

    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report,container,false);
        spCases = (Spinner) view.findViewById(R.id.spCases);
        spSchools = (Spinner) view.findViewById(R.id.SpSchools);
        schoolAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,schools);
        spSchools.setAdapter(schoolAdapter);
        caseAdapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,cases);
        spCases.setAdapter(caseAdapter);
        return view;
    }

}
