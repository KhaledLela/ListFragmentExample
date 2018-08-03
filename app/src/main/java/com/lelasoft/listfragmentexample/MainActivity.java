package com.lelasoft.listfragmentexample;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        updateContainerFrame(new LearnFragment());
    }

    public void updateContainerFrame(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .addToBackStack(fragment.getClass().getSimpleName())
                .replace(R.id.container_frame, fragment)
                .commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getBackStackEntryCount() > 1){
            fragmentManager.popBackStackImmediate();
        }else {
            super.onBackPressed();
        }
    }

    public static class LearnFragment extends ListFragment {
        private static final String TAG = "My_Logger";
        int mCurCheckPosition = 0;

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            Log.d(TAG,"onActivityCreated Called");
            if (getActivity() instanceof MainActivity){
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,
                        getResources().getStringArray(R.array.titles));
                setListAdapter(adapter);
                Log.d(TAG,"onActivityCreated Called : Activity Is Not Null");
            }
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);
            outState.putInt("curChoice", mCurCheckPosition);
        }

        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {
            showDetails(position);
        }

        void showDetails(int index) {
            mCurCheckPosition = index;
            if (getActivity() instanceof MainActivity)
                ((MainActivity) getActivity()).updateContainerFrame(TrickFragment.newInstance(index));
        }
    }


    public static class TrickFragment extends Fragment {

        public TrickFragment() {
            // Required empty public constructor
        }

        public static TrickFragment newInstance(int index) {
            TrickFragment fragment = new TrickFragment();
            Bundle args = new Bundle();
            args.putInt("index", index);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View view =inflater.inflate(R.layout.fragment_trick, container, false);
            return view;
        }

    }
}
