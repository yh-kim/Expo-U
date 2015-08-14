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
import com.expou.adapter.MarketAdapter;
import com.expou.item.MarketItem;

import java.util.ArrayList;

/**
 * Created by Kim on 2015-07-02.
 */
public class CtMarket extends Fragment {
    View rootView;

    //ArrayList 생성
    ArrayList<MarketItem> arr_list;

    //Adapter 생성
    MarketAdapter adapter;

    GridView gridview;

    Spinner spin_market_category, spin_market_sort;

    public static Fragment newInstance() {
        Fragment fragment = new CtMarket();
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
        rootView = inflater.inflate(R.layout.fragment_market,container,false);


        //스피너
        initSpinner();

//        버튼누르면 뷰페이저 바꾸기
//        btnTest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.market_frame, CtExpo.newInstance());
//
//                transaction.commit();
//            }
//        });

        //리스트
        arr_list = new ArrayList<MarketItem>();
        addData();


        //GridView
        gridview = (GridView)rootView.findViewById(R.id.gv_market);


        //Adapter 생성
        adapter = new MarketAdapter(this.getActivity(), R.layout.row_market, arr_list);

        //Adapter와 GirdView를 연결
        gridview.setAdapter(adapter);


    adapter.notifyDataSetChanged();

    //GridView의 아이템 클릭 리스너
    gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //(GridView객체, 클릭된 아이템 뷰, 클릭된 아이템의 위치, 클릭된 아이템의 아이디 - 특별한 설정이 없으면 position과 같은값)

            //클릭된 아이템의 위치를 이용하여 데이터인 문자열을 Toast로 출력
            Toast.makeText(rootView.getContext(), "선택"+position, Toast.LENGTH_SHORT).show();
        }
    });


    return rootView;
}

    private void initSpinner(){

        //스피너
        spin_market_category = (Spinner)rootView.findViewById(R.id.spin_market_category);
        spin_market_sort = (Spinner)rootView.findViewById(R.id.spin_market_sort);

        //스피너 어댑터
        ArrayAdapter category_adapter = ArrayAdapter.createFromResource(rootView.getContext(), R.array.expo_cate, R.layout.spinner_item);
        ArrayAdapter sort_adapter = ArrayAdapter.createFromResource(rootView.getContext(), R.array.expo_sort, R.layout.spinner_item);


        //스피너와 어댑터 연결
        spin_market_category.setAdapter(category_adapter);
        spin_market_sort.setAdapter(sort_adapter);
    }

    private void addData(){
        arr_list.add(new MarketItem());
        arr_list.add(new MarketItem());
        arr_list.add(new MarketItem());
        arr_list.add(new MarketItem());
        arr_list.add(new MarketItem());


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
