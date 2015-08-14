package com.expou.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;

import com.expou.R;
import com.expou.adapter.ExpoAdapter;
import com.expou.item.ExpoItem;

import java.util.ArrayList;

/**
 * Created by Kim on 2015-07-02.
 */
public class CtExpo extends Fragment {


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
        Fragment fragment = new CtExpo();
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
        addData();

        //GridView
        gridview = (GridView)rootView.findViewById(R.id.gv_expo);

        //Adapter 생성
        adapter = new ExpoAdapter(this.getActivity(), R.layout.row_expo, arr_list);

        //Adapter와 GirdView를 연결
        gridview.setAdapter(adapter);


        adapter.notifyDataSetChanged();

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

                //클릭된 아이템의 위치를 이용하여 데이터인 문자열을 Toast로 출력
                Toast.makeText(rootView.getContext(), arr_list.get(position).getTxtExplain(), Toast.LENGTH_SHORT).show();


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


    public void addData(){
//        Bitmap bmp = BitmapFactory.decodeResource(this.getResources(), R.drawable.poster);


        arr_list.add(new ExpoItem("poster","test1"));
        arr_list.add(new ExpoItem("poster1","test2"));
        arr_list.add(new ExpoItem("poster2","test3"));
        arr_list.add(new ExpoItem("poster3","test4"));
        arr_list.add(new ExpoItem("poster3","test5"));
        arr_list.add(new ExpoItem("poster2","test6"));

    }

}
