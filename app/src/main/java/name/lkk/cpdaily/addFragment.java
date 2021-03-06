package name.lkk.cpdaily;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.Calendar;

import name.lkk.cpdaily.databinding.FragmentAddBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link addFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class addFragment extends Fragment {
    static Bundle bundle= new Bundle();;
    MainViewModel mainViewModel;
    FragmentAddBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public addFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment addFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static addFragment newInstance(String param1, String param2) {
        addFragment fragment = new addFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mainViewModel = new ViewModelProvider(this, new SavedStateViewModelFactory(getActivity().getApplication(), this))
                .get(MainViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = FragmentAddBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String days = "";
        String months = "";
        String hours = "";
        String minutes = "";
        String defTime = "";
        String overTime = "";
        String spTime1 = "";
        String spTime2 = "";
        String spTime3 = "";

        Calendar calendar = Calendar.getInstance();
        //???
        int month = calendar.get(Calendar.MONTH) + 1;
        //???
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        //??????
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        //??????
        int minute = calendar.get(Calendar.MINUTE);
        if (day < 10) {
            days = "0" + Integer.toString(day);
        } else {
            days = Integer.toString(day);
        }
        if (month < 10) {
            months = "0" + Integer.toString(month);
        } else {
            months = Integer.toString(month);
        }
        if (hour < 10) {
            hours = "0" + Integer.toString(hour);
        } else {
            hours = Integer.toString(hour);
        }
        if (minute < 10) {
            minutes = "0" + Integer.toString(minute);
        } else {
            minutes = Integer.toString(minute);
        }

        defTime = months + "-" + days + " " + hours + ":" + minutes;
        if ((hour - 1) < 0) {
            hours = Integer.toString(Math.abs(hour + 23));
        }else if ((hour - 1) < 10) {
            hours = "0" + Integer.toString(hour - 1);
        }
         else {
            hours = Integer.toString(hour - 1);
        }
        spTime1 = months + "-" + days + " " + hours + ":" + "04";
        spTime2 = months + "-" + days + " " + hours + ":" + "26";
        spTime3 = months + "-" + days + " " + hours + ":" + "49";

        if ((hour + 2) < 10) {
            hours = "0" + Integer.toString(hour + 2);
        } else if ((hour + 2) > 24) {
            hours = "0" + Integer.toString(Math.abs(hour - 24));
        } else {
            hours = Integer.toString(hour + 2);
        }
        overTime = months + "-" + days + " " + hours + ":" + minutes;
        //??????????????????
        binding.editTextTextPersonName3.setText(String.valueOf(mainViewModel.getData()));
        //?????????????????????
        binding.editTextTextPersonName4.setText(String.valueOf(mainViewModel.getNum()));
        //????????????????????????
        binding.editTextTextPersonName5.setText(String.valueOf(mainViewModel.getReason()));
        //????????????????????????
        binding.editTextTextPersonName6.setText(String.valueOf(mainViewModel.getLocal()));
        //?????????????????????
        binding.editTextTextPersonName7.setText(String.valueOf(mainViewModel.getFdy()));
        //????????????????????????
        binding.editTextTextPersonName.setText(defTime);
        //????????????????????????
        binding.editTextTextPersonName2.setText(overTime);
        //??????????????????????????????
        binding.editTextDate.setText(spTime1);
        //??????????????????????????????
        binding.editTextDate2.setText(spTime2);
        //??????????????????????????????
        binding.editTextDate3.setText(spTime3);
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewModel.save(binding);

                //?????????
                bundle.putString("name", String.valueOf(binding.editTextTextPersonName3.getText()).trim());
                //????????????
                bundle.putString("text2", String.valueOf(binding.editTextTextPersonName.getText()));
                //????????????
                bundle.putString("text3", String.valueOf(binding.editTextTextPersonName2.getText()));
                //???????????????
                bundle.putString("text4", String.valueOf(binding.editTextTextPersonName4.getText()).trim());
                //????????????
                bundle.putString("text5", String.valueOf(binding.editTextTextPersonName5.getText()).trim());
                //????????????
                bundle.putString("text10", String.valueOf(binding.editTextTextPersonName6.getText()).trim());
                //????????????
                bundle.putString("text6", String.valueOf(binding.editTextTextPersonName8.getText()));
                //??????????????????
                bundle.putString("text7", String.valueOf(binding.editTextDate.getText()));
                //??????????????????
                bundle.putString("text8", String.valueOf(binding.editTextDate2.getText()));
                //??????????????????
                bundle.putString("text9", String.valueOf(binding.editTextDate3.getText()));
                //?????????
                bundle.putString("text11", String.valueOf(binding.editTextTextPersonName7.getText()));
                NavController navController = Navigation.findNavController(view);
                ConApp.bundle = bundle;
                navController.navigate(R.id.action_addFragment_to_detailFragment, bundle);
            }
        });

    }


}