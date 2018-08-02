package com.lelasoft.listfragmentexample;

import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    }

    public void updateContainerFrame(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container_frame, fragment)
                .commit();
    }

    public static class LearnFragment extends ListFragment {
        int mCurCheckPosition = 0;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    inflater.getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Titles));
            setListAdapter(adapter);
            return super.onCreateView(inflater, container, savedInstanceState);
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
