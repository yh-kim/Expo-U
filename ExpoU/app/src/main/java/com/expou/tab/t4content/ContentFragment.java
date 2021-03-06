package com.expou.tab.t4content;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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
    static boolean serverConnect = true;

    int row_cnt = 7;
    static int count = 0;
    static int offset = 0;
    static boolean is_scroll = true;
    //ArrayList 생성
    static public ArrayList<ContentItem> arr_list = new ArrayList<ContentItem>();

    //Adapter 생성
    static public ContentAdapter adapter;

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
        setRetainInstance(true);
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

        //GridView
        gridview = (GridView)rootView.findViewById(R.id.gv_content);

        //Adapter 생성
        adapter = new ContentAdapter(rootView.getContext(), R.layout.row_content, arr_list);

        //Adapter와 GirdView를 연결
        gridview.setAdapter(adapter);

        new NetworkGetContent().execute("");


        gridview.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                if ((firstVisibleItem + visibleItemCount) == totalItemCount) {
                    //서버로부터 받아온 List개수를 count
                    //지금까지 받아온 개수를 offset
                    if (count != 0 && offset % row_cnt == 0) {
                        if (is_scroll) {
                            //스크롤 멈추게 하는거
                            is_scroll = false;
                            serverConnect = true;
                            new NetworkGetContent().execute("");
                        }
                    }
                }
            }
            });

            //GridView의 아이템 클릭 리스너
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //(GridView객체, 클릭된 아이템 뷰, 클릭된 아이템의 위치, 클릭된 아이템의 아이디 - 특별한 설정이 없으면 position과 같은값)

                    try {
                        new ServiceDAOImpl().getDetailContent(arr_list.get(position).getObjectId());

                    } catch (ServiceException e) {
                        e.printStackTrace();
                    }

                    //페이지 보여주기
                    Intent intent = new Intent(rootView.getContext(), ContentDetailActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

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


    private class NetworkGetContent extends AsyncTask<String,String,Integer> {

        private ProgressDialog dialog;
        @Override
        protected Integer doInBackground(String... params) {
            return processing();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // 기다리라는 dialog 추가
            dialog = ProgressDialog
                    .show(rootView.getContext(), "", "잠시만 기다려주세요", true);
        }



        private Integer processing(){
            try {
                if(serverConnect){
                arr_list = new ServiceDAOImpl().getContent(offset);
                serverConnect = false;

                while(offset != offset+ServiceDAOImpl.count2){
                }
            }
            else{
                arr_list = ServiceDAOImpl.contentItems;
            }

            } catch (ServiceException e) {
                e.printStackTrace();
            }

            return 0;
        }


        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            dialog.cancel();

            // 서버에서 받아온 리스트 개수
            count = ServiceDAOImpl.count2;
            // 서버에서 받아온 전체 리스트 개수
            offset = offset + count;
            // scroll 할 수 있게함
            is_scroll = true;

            return;
        }
    }

}
