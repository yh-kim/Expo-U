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
import com.expou.adapter.BoothAdapter;
import com.expou.item.BoothItem;

import java.util.ArrayList;

/**
 * Created by Kim on 2015-07-02.
 */
public class CtBooth extends Fragment {
    View rootView;

    //ArrayList 생성
    ArrayList<BoothItem> arr_list;

    //Adapter 생성
    BoothAdapter adapter;

    GridView gridview;
    Spinner spin_booth_category, spin_booth_sort;

    public static Fragment newInstance() {
        Fragment fragment = new CtBooth();
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
        rootView = inflater.inflate(R.layout.fragment_booth,container,false);

        //스피너
        initSpinner();

        //리스트
        arr_list = new ArrayList<BoothItem>();
        addData();

        //GridView
        gridview = (GridView)rootView.findViewById(R.id.gv_booth);


        //Adapter 생성
        adapter = new BoothAdapter(this.getActivity(), R.layout.row_booth, arr_list);

        //Adapter와 GirdView를 연결
        gridview.setAdapter(adapter);


        adapter.notifyDataSetChanged();

        //GridView의 아이템 클릭 리스너
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //(GridView객체, 클릭된 아이템 뷰, 클릭된 아이템의 위치, 클릭된 아이템의 아이디 - 특별한 설정이 없으면 position과 같은값)

                //클릭된 아이템의 위치를 이용하여 데이터인 문자열을 Toast로 출력
                Toast.makeText(rootView.getContext(), arr_list.get(position).getTxtTitle(), Toast.LENGTH_SHORT).show();


            }
        });

        return rootView;
    }

    private void initSpinner(){

        //스피너
        spin_booth_category = (Spinner)rootView.findViewById(R.id.spin_booth_category);
        spin_booth_sort = (Spinner)rootView.findViewById(R.id.spin_booth_sort);

        //스피너 어댑터
        ArrayAdapter category_adapter = ArrayAdapter.createFromResource(rootView.getContext(), R.array.expo_cate, R.layout.spinner_item);
        ArrayAdapter sort_adapter = ArrayAdapter.createFromResource(rootView.getContext(), R.array.expo_sort, R.layout.spinner_item);


        //스피너와 어댑터 연결
        spin_booth_category.setAdapter(category_adapter);
        spin_booth_sort.setAdapter(sort_adapter);
    }

    private void addData(){
        arr_list.add(new BoothItem("poster","슈에무라","슈에무라는 1967년 우에무라 슈가 설립한 화장품 전문 회사이자 브랜드이다. 2004년 로레알 그룹에 인수되어 현재슈에무라는 1967년 우에무라 슈가 설립한 화장품 전문 회사이자 브랜드이다."));
        arr_list.add(new BoothItem("poster","넥슨","넥슨은 1994년 대한민국 서울에 설립된 이후 다수의 온라인 게임을 개발 및 서비스하고 있는 글로벌 게임 기업이다. 넥"));
        arr_list.add(new BoothItem("poster","슈에무라","슈에무라는 1967년 우에무라 슈가 설립한 화장품 전문 회사이자 브랜드이다. 2004년 로레알 그룹에 인수되어 현재"));
        arr_list.add(new BoothItem("poster","넥슨","넥슨은 1994년 대한민국 서울에 설립된 이후 다수의 온라인 게임을 개발 및 서비스하고 있는 글로벌 게임 기업이다. 넥"));
        arr_list.add(new BoothItem("poster","슈에무라","슈에무라는 1967년 우에무라 슈가 설립한 화장품 전문 회사이자 브랜드이다. 2004년 로레알 그룹에 인수되어 현재"));
        arr_list.add(new BoothItem("poster","넥슨","넥슨은 1994년 대한민국 서울에 설립된 이후 다수의 온라인 게임을 개발 및 서비스하고 있는 글로벌 게임 기업이다. 넥"));
        arr_list.add(new BoothItem("poster","슈에무라","슈에무라는 1967년 우에무라 슈가 설립한 화장품 전문 회사이자 브랜드이다. 2004년 로레알 그룹에 인수되어 현재"));
        arr_list.add(new BoothItem("poster","넥슨","넥슨은 1994년 대한민국 서울에 설립된"));


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
