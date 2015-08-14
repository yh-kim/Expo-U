package com.expou.tab.t4content;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;

import com.expou.R;
import com.expou.exception.ServiceException;
import com.expou.serverconnect.dao.ServiceDAOImpl;

import java.util.ArrayList;

/**
 * Created by Kim on 2015-07-02.
 */
public class ContentFragment extends Fragment {

    //ArrayList 생성
    ArrayList<ContentItem> arr_list;

    //Adapter 생성
    ContentAdapter adapter;

    GridView gridview;

    View rootView;

    Button btnTest;
    Spinner spin_content_category, spin_content_sort;


    public static Fragment newInstance() {
        Fragment fragment = new ContentFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_content,container,false);

        //스피너
        initSpinner();


        try {
            //서버로부터 데이터를 받아와 arr_list에 담음
            arr_list = new ServiceDAOImpl().getContent();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
            //GridView
            gridview = (GridView)rootView.findViewById(R.id.gv_content);
            //Adapter 생성
            adapter = new ContentAdapter(this.getActivity(), R.layout.row_content, arr_list);

            //Adapter와 GirdView를 연결
            gridview.setAdapter(adapter);

            adapter.notifyDataSetChanged();



        //GridView의 아이템 클릭 리스너
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //(GridView객체, 클릭된 아이템 뷰, 클릭된 아이템의 위치, 클릭된 아이템의 아이디 - 특별한 설정이 없으면 position과 같은값)

                //클릭된 아이템의 위치를 이용하여 데이터인 문자열을 Toast로 출력
//                Toast.makeText(rootView.getContext(), arr_list.get(position).getText(), Toast.LENGTH_SHORT).show();

                //페이지 보여주기
                FragmentManager fm = getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                ContentDetailFragment c = new ContentDetailFragment();
                transaction.add(R.id.content_frame, ContentDetailFragment.newInstance(position));
                transaction.commit();

            }
        });
        return rootView;
    }

    private void initSpinner(){

        //스피너
        spin_content_category = (Spinner)rootView.findViewById(R.id.spin_content_category);
        spin_content_sort = (Spinner)rootView.findViewById(R.id.spin_content_sort);

        //스피너 어댑터
        ArrayAdapter category_adapter = ArrayAdapter.createFromResource(rootView.getContext(), R.array.expo_cate, R.layout.spinner_item);
        ArrayAdapter sort_adapter = ArrayAdapter.createFromResource(rootView.getContext(), R.array.expo_sort, R.layout.spinner_item);


        //스피너와 어댑터 연결
        spin_content_category.setAdapter(category_adapter);
        spin_content_sort.setAdapter(sort_adapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
