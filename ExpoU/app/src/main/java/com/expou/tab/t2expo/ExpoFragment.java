package com.expou.tab.t2expo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import com.expou.R;
import com.expou.exception.ServiceException;
import com.expou.serverconnect.dao.ServiceDAOImpl;

import java.util.ArrayList;

/**
 * Created by Kim on 2015-07-02.
 */
public class ExpoFragment extends Fragment {


    int row_cnt = 6;
    int count = 0;
    int offset = 0;
    boolean is_scroll = true;

    //ArrayList 생성
    ArrayList<ExpoItem> arr_list;

    //Adapter 생성
    ExpoAdapter adapter;

    GridView gridview;

    View rootView;

    Spinner spin_expo_category, spin_expo_sort;

    public static Fragment newInstance() {
        Fragment fragment = new ExpoFragment();
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
        rootView = inflater.inflate(R.layout.fragment_expo,container,false);

        //스피너
        initSpinner();

        //리스트
        arr_list = new ArrayList<ExpoItem>();



        //GridView
        gridview = (GridView)rootView.findViewById(R.id.gv_expo);


        //Adapter 생성
        adapter = new ExpoAdapter(this.getActivity(), R.layout.row_expo, arr_list);


            //서버로부터 데이터를 받아와 arr_list에 담음
        try {
            arr_list = new ServiceDAOImpl().getExpo();
        } catch (ServiceException e) {
            e.printStackTrace();
        }


        adapter.notifyDataSetChanged();

        //Adapter와 GirdView를 연결
        gridview.setAdapter(adapter);


        /*
        //List를 scroll 했을때 끝을 알려주어 다음 data를 받아오는 method
        gridview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
                    //받아온 List개수를 count
                    //지금까지 받아온 개수를 offset
                    if (count != 0 && offset % row_cnt == 0) {
                        if (is_scroll) {
                            //스크롤 멈추게 하는거
                            is_scroll = false;
                            showData(offset);
                        }
                    }
                }
            }
        });
        */


        //GridView의 아이템 클릭 리스너
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //(GridView객체, 클릭된 아이템 뷰, 클릭된 아이템의 위치, 클릭된 아이템의 아이디 - 특별한 설정이 없으면 position과 같은값)

                //페이지 보여주기
                Intent intent = new Intent(rootView.getContext(), ExpoDetailActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);


            }
        });


        return rootView;


    }


    private void initSpinner(){

        //스피너
        spin_expo_category = (Spinner)rootView.findViewById(R.id.spin_expo_category);
        spin_expo_sort = (Spinner)rootView.findViewById(R.id.spin_expo_sort);

        //스피너 어댑터
        ArrayAdapter category_adapter = ArrayAdapter.createFromResource(rootView.getContext(), R.array.expo_cate, R.layout.spinner_item);
        ArrayAdapter sort_adapter = ArrayAdapter.createFromResource(rootView.getContext(), R.array.expo_sort, R.layout.spinner_item);


        //스피너와 어댑터 연결
        spin_expo_category.setAdapter(category_adapter);
        spin_expo_sort.setAdapter(sort_adapter);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
